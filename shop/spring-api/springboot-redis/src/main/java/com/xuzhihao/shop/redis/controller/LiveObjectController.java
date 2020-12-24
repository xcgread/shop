package com.xuzhihao.shop.redis.controller;

import java.util.Collection;

import org.redisson.api.RLiveObjectService;
import org.redisson.api.RedissonClient;
import org.redisson.api.condition.Condition;
import org.redisson.api.condition.Conditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xuzhihao.shop.redis.entity.MyLiveObject;

@RestController
public class LiveObjectController {

	@Autowired
	private RedissonClient redissonClient;

	/**
	 * @return
	 */
	@RequestMapping("/liveObject")
	public String reduceStock() {
		RLiveObjectService service = redissonClient.getLiveObjectService();
		// 通过ID获取分布式实时对象
		MyLiveObject myObject1 = service.get(MyLiveObject.class, "1");
		MyLiveObject myObject = new MyLiveObject("1");
		myObject.setValue("对象");
		if (myObject1 == null) {
			// 将myObject对象当前的状态持久化到Redis里并与之保持同步。
			myObject = service.persist(myObject);
		}
		String a = System.currentTimeMillis() + "";
		myObject.setValue(a);

		MyLiveObject myObject2 = new MyLiveObject("1");
		myObject2.setValue("抛弃以后的第一次状态");
		// 抛弃myObject对象当前的状态，并与Redis里的数据建立连接并保持同步。
		myObject2 = service.attach(myObject2);
		myObject2.setValue("抛弃以后的第2次状态");

		MyLiveObject myObject3 = new MyLiveObject();
		myObject3.setId("1");
		myObject3.setValue("第三次的合并");
		// 将myObject对象当前的状态与Redis里的数据合并之后与之保持同步。
		myObject3 = service.merge(myObject3);
		myObject3.setValue("第三次的合并2");
		myObject3.setName("测试多件查询");

		// 通过索引查找分布式实时对象
		Collection<MyLiveObject> myObjects = service.find(MyLiveObject.class,
				Conditions.in("value", "第三次的合并2", "另外一个无法匹配的条件"));
		Condition and = Conditions.in("value", "第三次的合并2", "1111");
		Condition eq = Conditions.eq("name", "测试多件查询");
		Collection<MyLiveObject> myObjects1111 = service.find(MyLiveObject.class, Conditions.and(and, eq));
		return myObjects.toString() + myObjects1111.toString();
	}

}
