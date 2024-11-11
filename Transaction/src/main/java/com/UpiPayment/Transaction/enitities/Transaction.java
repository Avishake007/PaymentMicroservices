package com.UpiPayment.Transaction.enitities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.UpiPayment.Transaction.enums.TransactionStatus;
import com.UpiPayment.Transaction.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String transactionId;
	@JsonIgnore
	private String userId;
	private String merchantId;
	private TransactionType transactionType;
	private double amount;
	private String currency;
	
	private TransactionStatus status;
	private String paymentId;
	private String transactionReference;
	@JsonIgnore
	@CreationTimestamp
	private Date initiatedAt;
	@JsonIgnore
	@UpdateTimestamp
	private Date completedAt;
	
}
