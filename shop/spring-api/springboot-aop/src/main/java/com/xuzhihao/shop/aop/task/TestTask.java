package com.xuzhihao.shop.aop.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.hutool.core.date.DateTime;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestTask {

	/**
	 * cron表达式
	 */
	@Scheduled(cron = "*/5 * * * * ?")
	public void task1() {
		log.info("task1 正在执行，现在时间：{}", DateTime.now());
	}

	/**
	 * 上一次开始执行时间点之后5秒再执行
	 */
	@Scheduled(fixedRate = 5000)
	public void task2() {
		log.info("task2 正在执行，现在时间：{}", DateTime.now());
	}

	/**
	 * 上一次执行完毕时间点之后5秒再执行
	 */
	@Scheduled(fixedDelay = 5000)
	public void task3() {
		log.info("task3 正在执行，现在时间：{}", DateTime.now());
	}

	/**
	 * 第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
	 */
	@Scheduled(initialDelay = 1000, fixedRate = 5000)
	public void task4() {
		log.info("task4 正在执行，现在时间：{}", DateTime.now());
	}
}
