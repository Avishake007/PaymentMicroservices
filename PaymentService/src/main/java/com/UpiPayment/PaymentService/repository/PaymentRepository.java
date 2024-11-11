package com.UpiPayment.PaymentService.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.UpiPayment.PaymentService.entities.Payment;

@Repository
@Lazy
public interface PaymentRepository extends MongoRepository<Payment, String>{

	
}
