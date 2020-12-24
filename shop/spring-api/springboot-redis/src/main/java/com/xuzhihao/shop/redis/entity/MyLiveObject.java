package com.xuzhihao.shop.redis.entity;

import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;
import org.redisson.api.annotation.RIndex;

import lombok.Getter;
import lombok.Setter;

@REntity
@Getter
@Setter
public class MyLiveObject {
	@RId
	private String id;
	@RIndex
	private String value;
	@RIndex
	private String name;

	public MyLiveObject(String id) {
		this.id = id;
	}

	public MyLiveObject() {

	}
}