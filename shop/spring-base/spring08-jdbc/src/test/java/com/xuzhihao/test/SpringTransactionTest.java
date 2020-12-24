package com.xuzhihao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xuzhihao.service.AccountService;

import config.SpringConfiguration;

/**
 * 测试自定义jdbc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class SpringTransactionTest {

	@Autowired
	private AccountService accountService;

	@Test
	public void testTransfer() {
		accountService.transfer("aaa", "bbb", 100d);
	}
}
