package com.Payment.UserService.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.Payment.UserService.entities.User;

@Aspect
@Component
public class UserServiceAspect {
	
	private final Logger LOGGER = LoggerFactory.getLogger(UserServiceAspect.class);
	
	@Pointcut("execution(* com.Payment.UserService.service.UserService.*())")
	public void customAspect() {
		
	}
	
	@Before("customAspect()")
	public void beforeNoParametersAspect(JoinPoint joinpoint) {
		System.out.println("Retrying..");
		LOGGER.info("Service : Before entering method "+ joinpoint.getSignature());
	}
	
	@After("customAspect()")
	public void afterNoParametersAspect(JoinPoint joinpoint) {
		
		LOGGER.info("Service : After exiting calling method "+ joinpoint.getSignature());
	}
	
	@Before(value = "execution(* com.Payment.UserService.service.UserService.*(..)) and args(id)")
	public void beforesAspect(JoinPoint joinpoint, String id) {
		
		LOGGER.info("Service : Before entering method "+ joinpoint.getSignature());
	}

}
