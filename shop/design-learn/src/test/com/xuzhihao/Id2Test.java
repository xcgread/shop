package com.xuzhihao;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;

/**
 * @author xx
 * @date xxxxxx
 **/
public class Id2Test {
	private static long workerId = 0;
	private long datacenterId = 1;
	private Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);

	public synchronized long snowflakeId() {
		return snowflake.nextId();
	}

	public synchronized long snowflakeId(long workerId, long datacenterId) {
		snowflake = IdUtil.createSnowflake(workerId, datacenterId);
		return snowflake.nextId();
	}

	public static void main(String[] args) {
		workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
		System.out.println("当前机器的workerId:" + workerId);
		System.out.println(new Id2Test().snowflakeId());
	}
}