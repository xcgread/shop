package com.xuzhihao.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.xuzhihao.system.model.RoleOperator;

@Mapper
public interface RoleOperatorMapper {
    int insert(RoleOperator roleOperator);

    Integer[] getOperatorsByRoleId(Integer roleId);

    int deleteByRoleId(@Param("roleId") Integer roleId);

    int insertRoleOperators(@Param("roleId") Integer roleId, @Param("operatorIds") Integer[] operatorIds);

    int deleteByOperatorId(@Param("operatorId") Integer operatorId);
}