package com.xuzhihao.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;

/**
 * 事务控制
 */
@Component
public class TransactionManager {

	@Autowired
	private Connection connection;

	/**
	 * 开启事务
	 */
	public void begin() {
		try {
			connection.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 提交事务
	 */
	public void commit() {
		try {
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 回滚事务
	 */
	public void rollback() {
		try {
			connection.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 释放资源
	 */
	public void close() {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
