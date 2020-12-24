package com.xuzhihao.shop.admin.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xuzhihao.shop.admin.service.UmsResourceService;

/**
 * 资源与角色访问对应关系操作组件
 */
@Component
public class ResourceRoleRulesHolder {

	@Autowired
	private UmsResourceService resourceService;

	@PostConstruct
	public void initResourceRolesMap() {
		resourceService.initResourceRolesMap();
	}
}
