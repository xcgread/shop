package com.xuzhihao.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 系统日志的实体类
 */
@Data
public class SystemLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1865325912289539485L;
	private String id; // 日志的主键
	private String method; // 当前执行的操作方法名称
	private String action; // 当前执行的操作方法说明
	private Date time; // 执行时间
	private String remoteIP;// 来访者IP

	@Override
	public String toString() {
		return "SystemLog{" + "id='" + id + '\'' + ", method='" + method + '\'' + ", action='" + action + '\''
				+ ", time=" + time + ", remoteIP='" + remoteIP + '\'' + '}';
	}
}
