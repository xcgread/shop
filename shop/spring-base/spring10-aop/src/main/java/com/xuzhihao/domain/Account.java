package com.xuzhihao.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * 账户的实体类
 */
@Data
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7009401783939331569L;
	private Integer id;
	private String name;
	private Double money;

}
