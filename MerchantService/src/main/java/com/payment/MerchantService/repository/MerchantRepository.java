package com.payment.MerchantService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.payment.MerchantService.entities.Merchant;


@Repository
public interface MerchantRepository extends MongoRepository<Merchant, String>{

}
