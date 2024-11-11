package com.payment.MerchantService.dto;

import java.util.Date;

import com.payment.MerchantService.entities.Merchant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantReponseDto {
	
	private long errorCode;
	private String errorDescription;
	private Merchant merchant; 
	
}
