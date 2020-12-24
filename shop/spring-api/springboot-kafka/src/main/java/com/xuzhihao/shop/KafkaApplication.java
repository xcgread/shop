package com.xuzhihao.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 整合KafkaTemplate<String, Object>
 * 
 * @author Administrator
 *
 */
@SpringBootApplication
public class KafkaApplication {
	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}
}
//docker run -d --name kafka -p 9092:9092 -e KAFKA_BROKER_ID=0 -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 --link zookeeper -e 	KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://172.17.17.80:9092 -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 -t wurstmeister/kafka
