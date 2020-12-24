package com.xuzhihao.dao;

import com.xuzhihao.domain.Account;

/**
 * 账户的持久层接口
 */
public interface AccountDao {

	/**
	 * 更新账户
	 * 
	 * @param account
	 */
	void update(Account account);

	/**
	 * 根据名称查询账户
	 * 
	 * @param name
	 * @return
	 */
	Account findByName(String name);
}
