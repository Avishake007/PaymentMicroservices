package com.payment.MerchantService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.MerchantService.dto.MerchantReponseDto;
import com.payment.MerchantService.dto.MerchantRequestDto;
import com.payment.MerchantService.entities.Merchant;
import com.payment.MerchantService.service.MerchantService;

@RestController
@RequestMapping("/merchant")
public class MechantController {
	
	@Autowired
	MerchantService merchantService;
	
	@PostMapping
	public ResponseEntity<MerchantReponseDto> addMerchant(@RequestBody MerchantRequestDto merchantRequestDto){
		
		Merchant merchant = merchantService.addMerchant(merchantRequestDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(new MerchantReponseDto(0,"Success",merchant));
	}
}
