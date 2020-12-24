package com.xuzhihao.shop.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuzhihao.shop.mbg.model.UmsPermission;
import com.xuzhihao.shop.mbg.model.UmsRolePermissionRelation;

/**
 * 自定义角色权限关系管理Dao
 */
public interface UmsRolePermissionRelationDao {
	/**
	 * 批量插入角色和权限关系
	 */
	int insertList(@Param("list") List<UmsRolePermissionRelation> list);

	/**
	 * 根据角色获取权限
	 */
	List<UmsPermission> getPermissionList(@Param("roleId") Long roleId);
}
