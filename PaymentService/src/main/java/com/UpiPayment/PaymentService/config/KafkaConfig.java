package com.UpiPayment.PaymentService.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import com.UpiPayment.PaymentService.constant.KafkaConstant;

@Configuration
public class KafkaConfig {
	
	
	public NewTopic topic() {
		return TopicBuilder
				.name(KafkaConstant.TOPIC)
				.partitions(1)
				.replicas(1)
				.build();
	}

}
