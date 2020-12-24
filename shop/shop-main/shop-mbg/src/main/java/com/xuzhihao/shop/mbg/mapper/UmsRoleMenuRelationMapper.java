package com.xuzhihao.shop.mbg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuzhihao.shop.mbg.model.UmsRoleMenuRelation;
import com.xuzhihao.shop.mbg.model.UmsRoleMenuRelationExample;

public interface UmsRoleMenuRelationMapper {
	long countByExample(UmsRoleMenuRelationExample example);

	int deleteByExample(UmsRoleMenuRelationExample example);

	int deleteByPrimaryKey(Long id);

	int insert(UmsRoleMenuRelation record);

	int insertSelective(UmsRoleMenuRelation record);

	List<UmsRoleMenuRelation> selectByExample(UmsRoleMenuRelationExample example);

	UmsRoleMenuRelation selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") UmsRoleMenuRelation record,
			@Param("example") UmsRoleMenuRelationExample example);

	int updateByExample(@Param("record") UmsRoleMenuRelation record,
			@Param("example") UmsRoleMenuRelationExample example);

	int updateByPrimaryKeySelective(UmsRoleMenuRelation record);

	int updateByPrimaryKey(UmsRoleMenuRelation record);
}