package cn.com.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitmqApplication {
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqApplication.class, args);
	}
}

//延迟插件rabbitmq-plugins enable rabbitmq_delayed_message_exchange
//延时队列可实现不同间隔的延迟 彼此不受影响(死信队列不可)
//confirm 到达路由 returnedMessage到达队列
