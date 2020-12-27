package com.xuzhihao.design.mode.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 使用单例模式创建JDBC工具类
 * 构造器私有化，自行创建实例，自行向系统提供这个实例
 */
//饿汉式：直接创建对象 不存在线程安全问题
//	 直接实例化
//	 枚举式
//	 静态代码块
//懒汉式：延迟创建对象
//	线程不安全 适用于单线程
//	线程安全 适用于多线程
//	静态内部类 适用于多线程
public final class JdbcUtilsSingleton {

	private static String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://debug-registry:3306/mall";
	private String username = "root";
	private String password = "root";

	// 懒汉式单例
	private static JdbcUtilsSingleton singleton;

	/**
	 * 构造器私用,防止直接创建对象, 通过反射或反序列化可以破解单例
	 */
	private JdbcUtilsSingleton() {

	}

	public static JdbcUtilsSingleton getInstance() {
		if (singleton == null) {
			// 加入有一个线程走到这里,然后又然给另一个线程执行完
			synchronized (JdbcUtilsSingleton.class) {
				if (singleton == null) {
					singleton = new JdbcUtilsSingleton();
				}
			}
		}
		return singleton;
	}

	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * 获取连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	/**
	 * 释放资源
	 */
	public void free(ResultSet rs, Statement st, Connection conn) {
		// 规范的关系连接的方式
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}