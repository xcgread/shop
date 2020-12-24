package com.xuzhihao.shop.redis.controller;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 分布式锁 实现方式：RedissonClient
 * 
 * @author Administrator
 *
 */
@RestController
@Slf4j
public class LockController {
	@Autowired
	private RedissonClient redissonClient;

	/**
	 * 同步可重入锁定
	 * 
	 * @return
	 */
	@RequestMapping({ "/reentrantLock" })
	public String reentrantLock() {
		// 获取锁对象
		RLock reentrantLock = redissonClient.getLock("order:A0001");
		// 加锁，并且设置锁过期时间，防止死锁的产生
		reentrantLock.lock(10, TimeUnit.SECONDS);
		// 加锁成功
		log.info(Thread.currentThread().getId() + " 修改...");
		// todo
		// （解锁）
		reentrantLock.unlock();
		return Thread.currentThread().getId() + " 操作成功.";
	}

	@RequestMapping({ "/reentrantLock2" })
	public String reentrantLock2() {
		// 获取锁对象
		RLock reentrantLock = redissonClient.getLock("order:A0001");
		// 加锁，并且设置锁过期时间，防止死锁的产生
		reentrantLock.lock(10, TimeUnit.SECONDS);
		// 加锁成功
		log.info(Thread.currentThread().getId() + " 修改...");
		// todo
		// （解锁）
		reentrantLock.unlock();
		return Thread.currentThread().getId() + " 操作成功.";
	}

	/**
	 * 读锁readWriteLock
	 */
	@RequestMapping({ "/readLock" })
	public String readWriteLock() {
		RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("order:A0002");
		try {
			readWriteLock.readLock().lock(10, TimeUnit.SECONDS);
			Thread.sleep(new Random().nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			readWriteLock.readLock().unlock();
		}
		return Thread.currentThread().getId() + " 读成功.";
	}

	/**
	 * 写锁readWriteLock
	 */
	@RequestMapping({ "/writeLock" })
	public String writeLock() {
		RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("order:A0002");

		try {
			readWriteLock.writeLock().lock(10, TimeUnit.SECONDS);
			Thread.sleep(new Random().nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			readWriteLock.writeLock().unlock();
		}
		return Thread.currentThread().getId() + " 写成功.";
	}

}
