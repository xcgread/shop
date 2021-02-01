package com.xuzhihao.spi.my;

public class ServiceImplA implements SpiService {

	public void exec(String aa) {
		System.out.println("ServiceImplA---"+aa);
	}

}
