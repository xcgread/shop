package com.xuzhihao.shop.aop.aspect;

import cn.hutool.core.date.DateUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 定时任务切面
 */
@Aspect
@Component
public class TaskAspect {

	@Pointcut("@annotation(org.springframework.scheduling.annotation.Scheduled)")
	public void pointCut() {
	}

	@Before("pointCut()")
	public void beforeHandle(JoinPoint joinPoint) {
		String taskName = getTaskName(joinPoint);
		System.out.println(DateUtil.now() + " " + taskName + "方法开始----");
	}

	@After("pointCut()")
	public void afterHandle(JoinPoint joinPoint) {
		String taskName = getTaskName(joinPoint);
		System.out.println(DateUtil.now() + " " + taskName + "方法结束----");
	}

	/**
	 * 获取定时任务类加名字
	 */
	private String getTaskName(JoinPoint joinPoint) {
		return joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();
	}

}
