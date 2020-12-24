package com.xuzhihao.shop.portal.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xuzhihao.shop.common.api.CommonResult;

/**
 * 认证服务远程调用
 */
@FeignClient("shop-auth")
public interface AuthService {

    @PostMapping(value = "/oauth/token")
    CommonResult<?> getAccessToken(@RequestParam Map<String, String> parameters);

}
