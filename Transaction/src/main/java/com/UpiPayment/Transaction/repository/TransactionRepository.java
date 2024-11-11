package com.UpiPayment.Transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.UpiPayment.Transaction.enitities.Transaction;

@RepositoryRestResource(collectionResourceRel = "transaction", path = "transaction")
public interface TransactionRepository extends JpaRepository<Transaction, String>{

}
