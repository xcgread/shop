package com.xuzhihao.shop.client;

import java.io.Serializable;

import lombok.Data;

@Data
public class CustomProtocol implements Serializable {

	private static final long serialVersionUID = 4671171056588401542L;
	private long id;
	private String content;

	public CustomProtocol(long id, String content) {
		this.id = id;
		this.content = content;
	}
}