package com.UpiPayment.PaymentService.dto;

import com.UpiPayment.PaymentService.enums.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {
	private String payerId;
	private String payerBankAccountNumber;
	private String payeeId;
	private long amount;
	private String currency;
	private PaymentMethod paymentMethod;
}
