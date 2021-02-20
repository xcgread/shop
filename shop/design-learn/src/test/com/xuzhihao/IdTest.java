package com.xuzhihao;

import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

public class IdTest {

	public static void main(String[] args) {
		// 生成的UUID是带-的字符串，类似于：a5c8a5e8-df2b-4706-bea4-08d0939410e3
		String uuid = IdUtil.randomUUID();
		System.out.println(uuid);
		// 生成的是不带-的字符串，类似于：b17f24ff026d40949c85a24f4f375d42
		String simpleUUID = IdUtil.simpleUUID();
		System.out.println(simpleUUID);

		// 生成类似：5b9e306a4df4f8c54a39fb0c
		String id = ObjectId.next();
		System.out.println(id);
		// 方法2：从Hutool-4.1.14开始提供
		String id2 = IdUtil.objectId();
		System.out.println(id2);

		// 参数1为终端ID
		// 参数2为数据中心ID
		Snowflake snowflake = IdUtil.createSnowflake(0, 1);
		// 有两种返回值类型	
		long nextId = snowflake.nextId();
		String nextIdStr = snowflake.nextIdStr();
		System.out.println("nextId -------------> " + nextId);
        System.out.println("nextIdStr ----------> " + nextIdStr);
        
        
        Snowflake snowflake1 = IdUtil.createSnowflake(2, 2);
        long nextId1 = snowflake1.nextId();
        String nextIdStr1 = snowflake1.nextIdStr();
        System.out.println("nextId1 ------------> " + nextId1);
        System.out.println("nextIdStr1 ---------> " + nextIdStr1);

        
        
	}

}
