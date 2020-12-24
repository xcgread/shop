package cn.com.rabbitmq.service;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.rabbitmq.config.RabbitMQConfig;
import cn.com.rabbitmq.config.RabbitMQConfirmAndReturn;
import cn.com.rabbitmq.domain.OrderItem;
import cn.hutool.json.JSONUtil;

@Service
public class MqSender {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private RabbitMQConfirmAndReturn rabbitMQConfirmAndReturn;

	@PostConstruct
	public void initRabbitTemplate() {
		rabbitTemplate.setMandatory(true);
		rabbitTemplate.setConfirmCallback(rabbitMQConfirmAndReturn);
		rabbitTemplate.setReturnCallback(rabbitMQConfirmAndReturn);
	}

	public void send1(String orderId) {
		Message message = MessageBuilder.withBody(orderId.getBytes())
				.setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding("utf-8").setMessageId(orderId)
				.build();
		// 构建回调返回的数据
		CorrelationData correlationData = new CorrelationData(orderId);
		// 发送消息
		rabbitTemplate.convertAndSend(RabbitMQConfig.ITEM_TOPIC_EXCHANGE, "item.springboot-rabbitmq", message,
				correlationData);
	}

	public void send2(String orderId) {
		// 给延迟队列发送消息，彼此间隔不同无任何影响
		OrderItem order = new OrderItem();
		order.setId(Long.valueOf(orderId));
		order.setProductName("蓝牙耳机" + System.currentTimeMillis());
		
		rabbitTemplate.convertAndSend("delay_exchange", "delay_queue", JSONUtil.toJsonStr(order), message -> {
			// 配置消息的过期时间
			message.getMessageProperties().setDelay(3000);
			return message;
		}, new CorrelationData(orderId));
	}
	public void send3(String orderId) {
		// 给延迟TTL队列发送消息 同样步长的场景使用
		OrderItem order = new OrderItem();
		order.setId(Long.valueOf(orderId));
		order.setProductName("钻石头盔" + System.currentTimeMillis());
		rabbitTemplate.convertAndSend("user.order.delay_exchange", "user.order.delay_key", JSONUtil.toJsonStr(order), message -> {
            message.getMessageProperties().setExpiration("3000");
            return message;
        },new CorrelationData(orderId));
	}
}
