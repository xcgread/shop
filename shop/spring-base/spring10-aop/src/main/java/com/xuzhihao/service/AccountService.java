package com.xuzhihao.service;

import java.util.List;

import com.xuzhihao.domain.Account;

/**
 * 账户的业务层接口
 */
public interface AccountService {

	/**
	 * 保存账户
	 * 
	 * @param account
	 */
	void save(Account account);

	/**
	 * 更新账户
	 * 
	 * @param account
	 */
	void update(Account account);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	Account findById(Integer id);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<Account> findAll();

	/**
	 * 转账
	 * 
	 * @param sourceName 转出账户名称
	 * @param targetName 转入账户名称
	 * @param money      转账金额
	 */
	void transfer(String sourceName, String targetName, Double money);
}
