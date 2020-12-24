package com.xuzhihao.jdbc.handler;

import java.sql.ResultSet;

/**
 * 结果集的处理器
 * 
 * @author admin
 */
public interface ResultSetHandler {

	/**
	 * 处理结果集
	 * 
	 * @param rs
	 * @return
	 */
	Object handle(ResultSet rs);
}
