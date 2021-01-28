package com.xuzhihao.shop.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 客户端注册
 * 
 * @author Administrator
 *
 */
public class NettySocketHolder {
	private static final Map<Long, NioSocketChannel> MAP = new ConcurrentHashMap<>(16);

	public static void put(Long id, NioSocketChannel socketChannel) {
		MAP.put(id, socketChannel);
	}

	public static NioSocketChannel get(Long id) {
		return MAP.get(id);
	}

	public static Map<Long, NioSocketChannel> getMAP() {
		return MAP;
	}

	public static void remove(NioSocketChannel nioSocketChannel) {
		MAP.entrySet().stream().filter(entry -> entry.getValue() == nioSocketChannel)
				.forEach(entry -> MAP.remove(entry.getKey()));
	}
}