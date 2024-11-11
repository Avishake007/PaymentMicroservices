package com.UpiPayment.PaymentGateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponseDto {
	private long erroCode;
	private String errorDescription;
	private String jwtToken;
	
}
