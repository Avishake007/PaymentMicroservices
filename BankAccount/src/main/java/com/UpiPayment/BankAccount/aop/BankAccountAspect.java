package com.UpiPayment.BankAccount.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class BankAccountAspect {
	
	private final Logger LOGGER = LoggerFactory.getLogger(BankAccountAspect.class);

	@Pointcut("execution(* com.UpiPayment.BankAccount.service.BankAccountService.*())")
	public void customNoParameterAspect() {
		
	}
	
	@Before("customNoParameterAspect()")
	public void beforeExecutingMethod(JoinPoint joinPoint) {
		LOGGER.info("Service : Before entering method "+joinPoint.getSignature());
	}
	
	@After("customNoParameterAspect()")
	public void afterExecutingMethod(JoinPoint joinPoint) {
		LOGGER.info("Service : After exiting method "+joinPoint.getSignature());
	}
}
