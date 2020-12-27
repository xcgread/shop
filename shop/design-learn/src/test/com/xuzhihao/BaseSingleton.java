package com.xuzhihao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.xuzhihao.design.mode.singleton.JdbcUtilsSingleton;

/**
 * 测试类
 */
public class BaseSingleton {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// 首先需要获取实例,然后获取连接
		Connection conn = JdbcUtilsSingleton.getInstance().getConnection();
		// 创建语句
		Statement st = conn.createStatement();
		// 执行语句
		ResultSet rs = st.executeQuery("select * from ums_admin");
		// 处理结果集
		while (rs.next()) {
			System.out.println(
					rs.getObject(1) + "\t" + rs.getObject(2) + "\t" + rs.getObject(3) + "\t" + rs.getObject(4));
		}
	}
}