package com.xuzhihao.design.hand;

import java.util.ArrayList;
import java.util.List;

import com.xuzhihao.design.hand.AirHandler;
import com.xuzhihao.design.hand.BikeHandler;
import com.xuzhihao.design.hand.CarHandler;
import com.xuzhihao.design.hand.Handler;

public class HandlerClient {

	public static void main(String[] args) {
		List<Handler> l = new ArrayList<Handler>();
		l.add(new BikeHandler());
		l.add(new CarHandler());
		l.add(new AirHandler());
		for (Handler handler : l)
			handler.chain();
	}

}
