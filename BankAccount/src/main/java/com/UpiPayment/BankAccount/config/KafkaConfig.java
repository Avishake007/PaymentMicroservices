package com.UpiPayment.BankAccount.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.UpiPayment.BankAccount.constant.KafkaConstant;

@Configuration
public class KafkaConfig {
	@KafkaListener(topics = KafkaConstant.TOPIC,groupId = KafkaConstant.GROUP_ID)
	public void initiatePayment(String message) {
		System.out.println(message);
	}

}
