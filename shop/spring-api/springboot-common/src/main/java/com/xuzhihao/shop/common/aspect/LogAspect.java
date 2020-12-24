package com.xuzhihao.shop.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.xuzhihao.shop.common.annotation.ApiLogs;

/**
 * 备用日志切片
 * 
 * @author Administrator
 *
 */
@Aspect
@Component
public class LogAspect {
	@Around("@annotation(apiLogs)")
	public Object around(ProceedingJoinPoint joinPoint, ApiLogs apiLogs) throws Throwable {
		return joinPoint.proceed();
	}

}