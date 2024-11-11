package com.payment.MerchantService.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantRequestDto {

	private String merchantName;
	private String merchantCategory;
	private String email;
	private String phoneNumber;
	private Address address;
	private String businessId;
}
