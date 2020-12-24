package com.xuzhihao.shop.redis.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 集成缓存
 * 
 * @author Administrator
 *
 */
@RestController
public class CacheController {
	/**
	 * 默认组-1无期限
	 * 
	 * @return
	 */
	@RequestMapping({ "/" })
	@Cacheable("-DEFAULT")
	public ArrayList<Object> page() {
		ArrayList<Object> a = new ArrayList<Object>();
		a.add("xcg");
		a.add(1);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("aaaaa", "strinw我gddd");
		map.put("bbbb", 1111);
		System.out.println("1111111111");
		a.add(map);
		return a;
	}

	/**
	 * 30分钟组 #id为key
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/test1")
	@Cacheable(value = "GROUP30M", key = "#id", unless = "#result==null")
	public String test1(@RequestParam String id) {
		return System.currentTimeMillis() + "-----30分钟组 #id为key";
	}

	/**
	 * 1分钟组 #id为key
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/test2")
	@Cacheable(value = "GROUP1M", key = "#id")
	public String test2(@RequestParam String id) {
		return System.currentTimeMillis() + "1分钟组 #id为key";
	}

	/**
	 * 修改
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/test3")
	@CachePut(value = "GROUP30M", key = "#id")
	public String test3(@RequestParam String id) {
		return System.currentTimeMillis() + "-----修改成功";
	}

	/**
	 * 删除 beforeInvocation = false 逻辑执行成功以后再清空缓存
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/test4")
	@CacheEvict(value = "GROUP30M", key = "#id", beforeInvocation = false)
	public String test4(@RequestParam String id) {
		return System.currentTimeMillis() + "-----删除成功";
	}
}
