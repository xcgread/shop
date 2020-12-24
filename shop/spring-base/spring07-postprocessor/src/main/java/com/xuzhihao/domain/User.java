package com.xuzhihao.domain;

import lombok.Data;

/**
 * 用户
 * 
 * @author Administrator
 *
 */
@Data
public class User {

	private int id;
	private String name;

	public User(int id) {
		this.id = id;
	}
	
	public User() {
	}
}
