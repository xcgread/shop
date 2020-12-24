package com.xuzhihao.shop.admin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xuzhihao.shop.admin.service.UmsResourceService;
import com.xuzhihao.shop.common.constant.AuthConstant;
import com.xuzhihao.shop.mbg.mapper.UmsResourceMapper;
import com.xuzhihao.shop.mbg.mapper.UmsRoleMapper;
import com.xuzhihao.shop.mbg.mapper.UmsRoleResourceRelationMapper;
import com.xuzhihao.shop.mbg.model.UmsResource;
import com.xuzhihao.shop.mbg.model.UmsResourceExample;
import com.xuzhihao.shop.mbg.model.UmsRole;
import com.xuzhihao.shop.mbg.model.UmsRoleExample;
import com.xuzhihao.shop.mbg.model.UmsRoleResourceRelation;
import com.xuzhihao.shop.mbg.model.UmsRoleResourceRelationExample;

import cn.hutool.core.util.StrUtil;

/**
 * 后台资源管理Service实现类
 */
@Service
public class UmsResourceServiceImpl implements UmsResourceService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
    private UmsResourceMapper resourceMapper;
    @Autowired
    private UmsRoleMapper roleMapper;
    @Autowired
    private UmsRoleResourceRelationMapper roleResourceRelationMapper;
    
    @Value("${spring.application.name}")
    private String applicationName;
    @Override
    public int create(UmsResource umsResource) {
        umsResource.setCreateTime(new Date());
        int count = resourceMapper.insert(umsResource);
        initResourceRolesMap();
        return count;
    }

    @Override
    public int update(Long id, UmsResource umsResource) {
        umsResource.setId(id);
        int count = resourceMapper.updateByPrimaryKeySelective(umsResource);
        initResourceRolesMap();
        return count;
    }

    @Override
    public UmsResource getItem(Long id) {
        return resourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Long id) {
        int count = resourceMapper.deleteByPrimaryKey(id);
        initResourceRolesMap();
        return count;
    }

    @Override
    public List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        UmsResourceExample example = new UmsResourceExample();
        UmsResourceExample.Criteria criteria = example.createCriteria();
        if(categoryId!=null){
            criteria.andCategoryIdEqualTo(categoryId);
        }
        if(StrUtil.isNotEmpty(nameKeyword)){
            criteria.andNameLike('%'+nameKeyword+'%');
        }
        if(StrUtil.isNotEmpty(urlKeyword)){
            criteria.andUrlLike('%'+urlKeyword+'%');
        }
        return resourceMapper.selectByExample(example);
    }

    @Override
    public List<UmsResource> listAll() {
        return resourceMapper.selectByExample(new UmsResourceExample());
    }

    @Override
    public Map<String,List<String>> initResourceRolesMap() {
        Map<String,List<String>> resourceRoleMap = new TreeMap<>();
        List<UmsResource> resourceList = resourceMapper.selectByExample(new UmsResourceExample());
        List<UmsRole> roleList = roleMapper.selectByExample(new UmsRoleExample());
        List<UmsRoleResourceRelation> relationList = roleResourceRelationMapper.selectByExample(new UmsRoleResourceRelationExample());
        for (UmsResource resource : resourceList) {
            Set<Long> roleIds = relationList.stream().filter(item -> item.getResourceId().equals(resource.getId())).map(UmsRoleResourceRelation::getRoleId).collect(Collectors.toSet());
            List<String> roleNames = roleList.stream().filter(item -> roleIds.contains(item.getId())).map(item -> item.getId() + "_" + item.getName()).collect(Collectors.toList());
            resourceRoleMap.put("/"+applicationName+resource.getUrl(),roleNames);
        }
        redisTemplate.delete(AuthConstant.RESOURCE_ROLES_MAP_KEY);
		redisTemplate.opsForHash().putAll(AuthConstant.RESOURCE_ROLES_MAP_KEY, resourceRoleMap);
		
        return resourceRoleMap;

    }
}
