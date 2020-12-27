package com.xuzhihao.design.mode.hand;

import java.util.ArrayList;
import java.util.List;

public class Client {

	public static void main(String[] args) {
		List<Handler> l = new ArrayList<Handler>();
		l.add(new BikeHandler());
		l.add(new CarHandler());
		l.add(new AirHandler());
		for (Handler handler : l)
			handler.chain();
	}

}
