package com.xuzhihao.shop.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuzhihao.shop.common.api.CommonResult;

/**
 * 全局异常处理
 * 
 * @author Administrator
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@ExceptionHandler(value = ApiException.class)
	public CommonResult handle(ApiException e) {
		if (e.getErrorCode() != null) {
			return CommonResult.failed(e.getErrorCode());
		}
		return CommonResult.failed(e.getMessage());
	}
}
