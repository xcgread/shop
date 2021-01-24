package com.xuzhihao.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ChannelSocket {
	public static void main(String[] args) throws IOException {
		ServerSocketChannel ss= ServerSocketChannel.open();
		ss.bind(new InetSocketAddress(8090));
		ss.configureBlocking(false);
		while(true) {
			SocketChannel client=ss.accept();
			if(client==null) {
				System.out.println("null......");
			}else {
				System.out.println("client...port"+client.socket().getPort());
			}
		}
	}
}
