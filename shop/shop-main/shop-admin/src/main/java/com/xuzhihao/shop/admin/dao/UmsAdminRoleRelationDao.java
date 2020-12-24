package com.xuzhihao.shop.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuzhihao.shop.mbg.model.UmsAdminRoleRelation;
import com.xuzhihao.shop.mbg.model.UmsPermission;
import com.xuzhihao.shop.mbg.model.UmsResource;
import com.xuzhihao.shop.mbg.model.UmsRole;

/**
 * 自定义后台用户与角色管理Dao
 */
public interface UmsAdminRoleRelationDao {
    /**
     * 批量插入用户角色关系
     */
    int insertList(@Param("list") List<UmsAdminRoleRelation> adminRoleRelationList);

    /**
     * 获取用于所有角色
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有角色权限
     */
    List<UmsPermission> getRolePermissionList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有可访问资源
     */
    List<UmsResource> getResourceList(@Param("adminId") Long adminId);

    /**
     * 获取资源相关用户ID列表
     */
    List<Long> getAdminIdList(@Param("resourceId") Long resourceId);
}
