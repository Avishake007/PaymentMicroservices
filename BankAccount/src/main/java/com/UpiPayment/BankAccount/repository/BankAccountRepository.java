package com.UpiPayment.BankAccount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.UpiPayment.BankAccount.entities.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String>{
	
	List<BankAccount> findByUserId(String userId);
	void deleteByUserId(String userId);
	
	@Query("select b from BankAccount b where b.isPrimary = \"True\" and b.userId = ?1")
	BankAccount getPrimaryBankAccount(String userId);

}
