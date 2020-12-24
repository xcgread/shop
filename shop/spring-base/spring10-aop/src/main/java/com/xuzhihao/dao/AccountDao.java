package com.xuzhihao.dao;

import java.util.List;

import com.xuzhihao.domain.Account;

/**
 * 账户的持久层接口
 */
public interface AccountDao {

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
	 * 根据名称查询账户
	 * 
	 * @param name
	 * @return
	 */
	Account findByName(String name);
}
