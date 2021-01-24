package com.xuzhihao.design.mode.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 使用单例模式创建JDBC工具类 构造器私有化，自行创建实例，自行向系统提供这个实例
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

	private static ThreadLocal<Connection> tl = new ThreadLocal<>();

	private static final String driver = "com.mysql.cj.jdbc.Driver";// mysql驱动
//	private static final String driver = "oracle.jdbc.driver.OracleDriver"; // oracle驱动
//	private static final String driver = "org.postgresql.Driver"; // postgresql驱动
//	private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // sqlserver驱动

	private static final String url = "jdbc:mysql://debug-registry:3306/mall";// mysql地址
//	private static final String url = "jdbc:oracle:thin:@172.17.17.37:1521:ORCL"; // oracle地址
//	private static final String url = "jdbc:postgresql://172.17.17.38:5432/VJSP10003182"; // postgresql地址
//	private static final String url = "jdbc:sqlserver://172.17.17.172:1433; DatabaseName=maptest"; // sqlserver地址

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
	public synchronized Connection getConnection() throws SQLException {
		Connection conn = tl.get();
		// 如果容器中没有连接，就从连接池获取一个连接存到ThreadLocal中
		if (conn == null) {
			conn = DriverManager.getConnection(url, username, password);
			tl.set(conn);
		}
		return conn;
	}

	/**
	 * 释放资源
	 */
	public static void free(AutoCloseable... ios) {
		for (AutoCloseable io : ios) {
			try {
				if (io != null) {
					io.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (io != null) {
						io.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (io != null) {
						try {
							io.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}