package com.xuzhihao.shop.portal.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xuzhihao.shop.common.api.CommonResult;

/**
 * 检索服务远程调用
 */
@FeignClient("shop-search")
public interface ProductService {

    @GetMapping(value = "/esProduct/search")
    CommonResult<?> search(@RequestParam String keyword,@RequestParam Long brandId,@RequestParam Long productCategoryId,@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam Integer sort);

}
