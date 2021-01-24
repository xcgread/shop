package com.xuzhihao.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class OldSocket {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(8090);
		System.out.println("step1: new ServerSocket 8090");
		Socket client = server.accept();
		System.out.println("step1: client\t" + client.getPort());
		InputStream in = client.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		System.out.println(reader.readLine());
		for (;;) {
		}
	}
}
