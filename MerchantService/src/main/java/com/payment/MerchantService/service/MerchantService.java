package com.payment.MerchantService.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.MerchantService.dto.MerchantReponseDto;
import com.payment.MerchantService.dto.MerchantRequestDto;
import com.payment.MerchantService.entities.Merchant;
import com.payment.MerchantService.enums.RegistrationStatus;
import com.payment.MerchantService.repository.MerchantRepository;

@Service
public class MerchantService {
	
	@Autowired
	MerchantRepository merchantRepository;
	
	@Autowired
	Merchant merchant;
	
	public Merchant addMerchant(MerchantRequestDto merchantRequestDto) {
		
		merchant.setMerchantName(merchantRequestDto.getMerchantName());
		merchant.setMerchantCategory(merchantRequestDto.getMerchantCategory());
		merchant.setEmail(merchantRequestDto.getEmail());
		merchant.setPhoneNumber(merchantRequestDto.getPhoneNumber());
		merchant.setAddress(merchantRequestDto.getAddress());
		merchant.setRegistrationStatus(RegistrationStatus.REGISTERED);
		merchant.setBusinessId(merchantRequestDto.getBusinessId());
		
		String currDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
		
		merchant.setCreatedAt(currDate);
		merchant.setUpdatedAt(currDate);
		
		merchantRepository.save(merchant);
		
		return merchant;
		
		
	}
}
