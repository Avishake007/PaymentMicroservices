package com.UpiPayment.PaymentService.dto;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;


import com.UpiPayment.PaymentService.entities.Payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto extends RepresentationModel<PaymentResponseDto>{
	private long errorCode;
	private String erroDescription;
	private String paymentMessage;
	private Payment payment;

}
