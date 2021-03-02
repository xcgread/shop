package com.xuzhihao.system.service;

import org.springframework.stereotype.Service;

import com.xuzhihao.system.mapper.RoleOperatorMapper;
import com.xuzhihao.system.model.RoleOperator;

import javax.annotation.Resource;

@Service
public class RoleOperatorService {

    @Resource
    private RoleOperatorMapper roleOperatorMapper;

    public int insert(RoleOperator roleOperator) {
        return roleOperatorMapper.insert(roleOperator);
    }

}
