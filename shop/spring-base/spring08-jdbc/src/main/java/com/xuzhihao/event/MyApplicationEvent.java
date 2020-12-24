package com.xuzhihao.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义监听事件
 * 
 */
public class MyApplicationEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4711150880393705506L;
	private Object source;

	public MyApplicationEvent(Object source) {
		super(source);
		this.source = source;
	}

	@Override
	public Object getSource() {
		return source;
	}
}
