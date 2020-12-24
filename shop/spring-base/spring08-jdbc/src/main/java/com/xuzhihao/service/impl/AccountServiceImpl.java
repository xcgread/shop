package com.xuzhihao.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuzhihao.dao.AccountDao;
import com.xuzhihao.domain.Account;
import com.xuzhihao.event.MyApplicationEvent;
import com.xuzhihao.service.AccountService;

/**
 * 账户操作实现类
 */
@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public void transfer(String sourceName, String targetName, Double money) {

		try {
			// 1.根据名称查询转出账户
			Account source = accountDao.findByName(sourceName);
			// 2.根据名称查询转入账户
			Account target = accountDao.findByName(targetName);
			// 3.转出账户减钱
			source.setMoney(source.getMoney() - money);
			// 4.转入账户加钱
			target.setMoney(target.getMoney() + money);
			// 5.更新转出账户
			accountDao.update(source);
			// 模拟转账异常
//			int i = 1 / 0;
			// 6.更新转入账户
			accountDao.update(target);

			// 发布事件（转账完成了）
		} finally {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("sourceName", sourceName);
			map.put("targetName", targetName);
			map.put("money", money);
			applicationEventPublisher.publishEvent(new MyApplicationEvent(map));
		}
	}

}
