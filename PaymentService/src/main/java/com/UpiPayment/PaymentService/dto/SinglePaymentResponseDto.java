package com.UpiPayment.PaymentService.dto;

import org.springframework.hateoas.RepresentationModel;

import com.UpiPayment.PaymentService.entities.Payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SinglePaymentResponseDto extends RepresentationModel<SinglePaymentResponseDto>{
	private long errorCode;
	private String errorDescription;
	private Payment payment;
	
}
