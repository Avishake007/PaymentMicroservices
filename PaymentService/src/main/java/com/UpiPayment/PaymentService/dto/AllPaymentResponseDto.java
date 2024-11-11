package com.UpiPayment.PaymentService.dto;

import java.util.List;

import com.UpiPayment.PaymentService.entities.Payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllPaymentResponseDto {
	private long errorCode;
	private String errorDescription;
	private List<Payment> payment;
}
