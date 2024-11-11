package com.payment.MerchantService.entities;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.payment.MerchantService.dto.Address;
import com.payment.MerchantService.enums.RegistrationStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Merchant {

	@Id
	private String merchant_id;
	private String merchantName;
	private String merchantCategory;
	private String email;
	private String phoneNumber;
	private Address address;
	private String businessId;
	private RegistrationStatus registrationStatus;
	private String createdAt;
	private String updatedAt;
}
