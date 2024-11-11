package com.UpiPayment.PaymentService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.UpiPayment.PaymentService.dto.AllPaymentResponseDto;
import com.UpiPayment.PaymentService.dto.PaymentRequestDto;
import com.UpiPayment.PaymentService.dto.PaymentResponseDto;
import com.UpiPayment.PaymentService.dto.SinglePaymentResponseDto;
import com.UpiPayment.PaymentService.entities.Payment;
import com.UpiPayment.PaymentService.services.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	
	@PostMapping
	public ResponseEntity<PaymentResponseDto> createPayment(@RequestBody PaymentRequestDto paymentRequestDto){
		Payment payment=paymentService.initiatePayment(paymentRequestDto);
		
		PaymentResponseDto paymentResponseDto = new PaymentResponseDto(0,"SUCCESS","Payment Successful",payment);
		
		Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PaymentController.class)
	            .getSinglePayment(payment.getPaymentId())).withSelfRel();
		paymentResponseDto.add(selfLink);
	
	
	Link deleteLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PaymentController.class)
            .cancelPayment(payment.getPaymentId())).withRel("cancel");
	paymentResponseDto.add(deleteLink);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(paymentResponseDto);
	}
	
	@GetMapping("/{paymentId}")
	public ResponseEntity<SinglePaymentResponseDto> getSinglePayment(@PathVariable("paymentId") String paymentId){
		Payment payment = paymentService.getPaymentDetailThroughId(paymentId);
		
		SinglePaymentResponseDto singlePaymentResponseDto = new SinglePaymentResponseDto(0,"SUCCESS",payment);
		
		Link getAllPaymentsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PaymentController.class)
	            .getAllPayments()).withRel("cancel");
		singlePaymentResponseDto.add(getAllPaymentsLink);
		
		return ResponseEntity.status(HttpStatus.OK).body(singlePaymentResponseDto);
	}
	
	@GetMapping
	public ResponseEntity<AllPaymentResponseDto> getAllPayments(){
		List<Payment> paymentLi = paymentService.getAllPaymentDetails();
		
		return ResponseEntity.status(HttpStatus.OK).body(new AllPaymentResponseDto(0,"SUCCESS",paymentLi));
	}
	
	@PutMapping("/{paymentId}/cancel")
	public ResponseEntity<PaymentResponseDto> cancelPayment(@PathVariable("paymentId") String paymentId ){
		Payment cancelledPayment = paymentService.cancelPayment(paymentId);
		return ResponseEntity.status(HttpStatus.OK).body(new PaymentResponseDto(0,"SUCCESS","Payment Cancelled",cancelledPayment));
		
	}
}
