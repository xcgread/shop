package cn.com.rabbitmq.config;

import java.io.IOException;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
public class RListener {
	@RabbitListener(queues = "item_queue")
	public void process(Message message, @Headers Map<String, Object> headers, Channel channel) {
		String messageId = message.getMessageProperties().getMessageId();
		long tag = message.getMessageProperties().getDeliveryTag();
		try {
			if (messageId.endsWith("1")) {
				channel.basicAck(tag, false);// 手动签收消息,通知mq服务器端删除该消息
				System.err.println("成功消费消息了：" + message);
			} else if (messageId.endsWith("2")) {
				Thread.sleep(5000L);
				channel.basicNack(tag, false, true);// 重回队列 根据实际应用
				System.err.println("处理异常：重回队列" + message);
			} else {
				int i = 1 / 0;
				System.out.println(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				channel.basicNack(tag, false, false);// 丢弃该消息 配合手动处理和数据库日志
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.err.println("丢弃该消息：" + message);
		}
	}

	// 监听延时消息队列
	@RabbitListener(queues = "delay_queue")
	public void consumeMessage(Message message, @Headers Map<String, Object> headers, Channel channel)
			throws IOException {
		// 如果订单状态不是0 说明订单已经被其他消费队列改动过了 加一个状态用来判断集群状态的情况
		long tag = message.getMessageProperties().getDeliveryTag();
		channel.basicAck(tag, false);
		System.out.println(new String(message.getBody(), "utf-8"));
	}

	// 监听死信消息队列
	@RabbitListener(queues = "user.order.receive_queue")
	public void consumeMessage2(Message message, @Headers Map<String, Object> headers, Channel channel)
			throws IOException {
		// 如果订单状态不是0 说明订单已经被其他消费队列改动过了 加一个状态用来判断集群状态的情况
		long tag = message.getMessageProperties().getDeliveryTag();
		channel.basicAck(tag, false);
		System.out.println(new String(message.getBody(), "utf-8"));
	}
}