package com.UpiPayment.PaymentService.services;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.UpiPayment.PaymentService.constant.KafkaConstant;
import com.UpiPayment.PaymentService.dto.PaymentRequestDto;
import com.UpiPayment.PaymentService.entities.Payment;
import com.UpiPayment.PaymentService.exception.PaymentIdAlreadyCancelledException;
import com.UpiPayment.PaymentService.exception.PaymentIdNotFoundException;
import com.UpiPayment.PaymentService.external.services.BankAccountService;
import com.UpiPayment.PaymentService.repository.PaymentRepository;
import com.UpiPayment.PaymentService.validations.PaymentProcessing;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class PaymentService {
	
	public final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);
	
	
	
	@PostConstruct
	public void init() {
		LOGGER.info("Initialising PaymentService");
	}
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	BankAccountService bankAccountService;
	
	@Autowired
	PaymentProcessing paymentProcessing;
	
	 @Autowired
	  KafkaTemplate<String, String> kafkaTemplate;

	
	public Payment initiatePayment(PaymentRequestDto paymentRequestDto ) {
		Payment payment = new Payment();
		//payment.setPaymentId(UUID.randomUUID().toString());
		payment.setPayerId(paymentRequestDto.getPayerId());
		payment.setPayeeId(paymentRequestDto.getPayeeId());
		payment.setAmount(paymentRequestDto.getAmount());
		payment.setCurrency(paymentRequestDto.getCurrency());
		payment.setPaymentMethod(paymentRequestDto.getPaymentMethod());
		payment.setPayerBankAccountNumber(paymentRequestDto.getPayerBankAccountNumber());
		payment.setPaymentStatus("PENDING");
		
		String currDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
		
		payment.setCreatedAt(currDate);
		
		this.kafkaTemplate.send(KafkaConstant.TOPIC,"Payment Initiated by user : "+payment.getPayerId());
		String result = paymentProcessing.processPayment(payment.getPayerBankAccountNumber(), payment.getPayeeId(), payment.getAmount());
		
		if(!result.equals("Payment Successful")) {
			payment.setPaymentStatus("FAILED");
		}
		else {
			payment.setPaymentStatus("SUCCESSFUL");
		}
		
		paymentRepository.save(payment);
		
		return payment;
	}
	
	public Payment getPaymentDetailThroughId(String paymentId) {
		return paymentRepository.findById(paymentId).orElseThrow(() ->  new PaymentIdNotFoundException("Payment id : "+paymentId+" is not present"));
	} 

	public List<Payment> getAllPaymentDetails(){
		return paymentRepository.findAll();
	}
	
	public Payment cancelPayment(String paymentId) {
		Payment canceledPayment = getPaymentDetailThroughId(paymentId);
		
		if(canceledPayment.getPaymentStatus().equals("CANCELLED"))
		{
			throw new PaymentIdAlreadyCancelledException("Payment Id "+paymentId+" is already cancelled");
		}
		
		canceledPayment.setPaymentStatus("CANCELLED");
		paymentRepository.save(canceledPayment);
		return  canceledPayment;
	}
	
	@PreDestroy
	public void destroy() {
		LOGGER.info("Destroying PaymentService");
	}
}
