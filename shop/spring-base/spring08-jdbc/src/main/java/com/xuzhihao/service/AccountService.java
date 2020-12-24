package com.xuzhihao.service;

/**
 * 账户的业务层接口
 */
public interface AccountService {

	/**
	 * 转账
	 * 
	 * @param sourceName
	 * @param targetName
	 * @param money
	 */
	void transfer(String sourceName, String targetName, Double money);
}
