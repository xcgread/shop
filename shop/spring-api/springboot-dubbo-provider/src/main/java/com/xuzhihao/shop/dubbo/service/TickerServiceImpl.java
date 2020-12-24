package com.xuzhihao.shop.dubbo.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

/**\
 * 
 * @author Administrator
 *
 */
@Component
@DubboService
public class TickerServiceImpl implements TicketService {
	@Override
	public String getTicket() {
		return "<厉害了，我的国》";
	}
}
