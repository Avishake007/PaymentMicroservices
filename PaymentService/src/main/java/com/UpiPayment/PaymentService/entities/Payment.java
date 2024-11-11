package com.UpiPayment.PaymentService.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.UpiPayment.PaymentService.enums.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("userPayment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
	
	@Id
	private String paymentId;
	private String payerId;
	private String payerBankAccountNumber;
	private String payeeId;
	private long amount;
	private String currency;
	private PaymentMethod paymentMethod;
	private String paymentStatus;
	private String transactionReference;
	private String paymentGatewayResponse;
	private String completedAt;
	private String createdAt;

}
