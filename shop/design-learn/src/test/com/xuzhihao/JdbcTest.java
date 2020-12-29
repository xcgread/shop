package com.xuzhihao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.xuzhihao.design.mode.singleton.JdbcUtilsSingleton;

public class JdbcTest {
	public static void main(String[] args) throws SQLException {

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
		st.executeBatch();
		st.close();

		//批量插入
		String sql = "INSERT INTO ums_admin2 (username,password) VALUES(?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for (int i = 1; i <= 10; i++) {
			pstmt.setString(1, "username" + i);
			pstmt.setString(2, "password" + i);
			pstmt.addBatch();
		}
		pstmt.executeBatch();
		pstmt.close();
		conn.close();
		

	}

}
