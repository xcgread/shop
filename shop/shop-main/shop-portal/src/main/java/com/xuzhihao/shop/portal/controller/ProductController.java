package com.xuzhihao.shop.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuzhihao.shop.common.api.CommonResult;
import com.xuzhihao.shop.portal.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 前台商品管理Controller
 */
@Controller
@Api(tags = "PortalProductController", description = "前台商品管理")
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@ApiOperation(value = "综合搜索、筛选、排序")
	@ApiImplicitParam(name = "sort", value = "排序字段:0->按相关度；1->按新品；2->按销量；3->价格从低到高；4->价格从高到低", defaultValue = "0", allowableValues = "0,1,2,3,4", paramType = "query", dataType = "integer")
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<?> search(@RequestParam(required = false) String keyword,
			@RequestParam(required = false) Long brandId, @RequestParam(required = false) Long productCategoryId,
			@RequestParam(required = false, defaultValue = "0") Integer pageNum,
			@RequestParam(required = false, defaultValue = "5") Integer pageSize,
			@RequestParam(required = false, defaultValue = "0") Integer sort) {
		return productService.search(keyword, brandId, productCategoryId, pageNum, pageSize, sort);
	}
}
