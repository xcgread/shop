package com.xuzhihao.spi.my;

public class ServiceImplDefault implements SpiService {

	public void exec(String dd) {
		System.out.println("Default---"+dd);
	}

}
