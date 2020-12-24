package com.xuzhihao.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisApplication {
	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}
}
//- redisTemplate实现消息发布订阅
//- 监听全局所有过期事件__keyevent@14__:expired 或者监听指定数据库失效时间
//- 基于@Cacheable注解 自定义redis缓存管理器配置不同失效时间
//- Redisson分布式锁
//- 分布式实时对象（Live Object）服务
//- 分布式远程服务（Remote Service）（待整合）
//
//``` java
//
//		RLiveObjectService service = redissonClient.getLiveObjectService();
//		// 通过ID获取分布式实时对象
//		MyLiveObject myObject1 = service.get(MyLiveObject.class, "1");
//		MyLiveObject myObject = new MyLiveObject("1");
//		myObject.setValue("对象");
//		if (myObject1==null) {
//			// 将myObject对象当前的状态持久化到Redis里并与之保持同步。
//			myObject = service.persist(myObject);
//		}
//		String a=System.currentTimeMillis()+"";
//		myObject.setValue(a);
//
//		MyLiveObject myObject2 = new MyLiveObject("1");
//		myObject2.setValue("抛弃以后的第一次状态");
//		// 抛弃myObject对象当前的状态，并与Redis里的数据建立连接并保持同步。
//		myObject2 = service.attach(myObject2);
//		myObject2.setValue("抛弃以后的第2次状态");
//	
//		MyLiveObject myObject3 = new MyLiveObject();
//		myObject3.setId("1");
//		myObject3.setValue("第三次的合并");
//		// 将myObject对象当前的状态与Redis里的数据合并之后与之保持同步。
//		myObject3 = service.merge(myObject3);
//		myObject3.setValue("第三次的合并2");
//		myObject3.setName("测试多件查询");
//		
//		// 通过索引查找分布式实时对象
//		Collection<MyLiveObject> myObjects = service.find(MyLiveObject.class,
//				Conditions.in("value", "第三次的合并2", "另外一个无法匹配的条件"));
//		Condition and= Conditions.in("value", "第三次的合并2","1111");
//		Condition eq= Conditions.eq("name", "测试多件查询");
//		Collection<MyLiveObject> myObjects1111 = service.find(MyLiveObject.class, Conditions.and(and,eq));
//		return myObjects.toString()+myObjects1111.toString();
//	
//```
//- 管道调用测试
