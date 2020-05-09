package com.webcustomertracker.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	//set up logger
	private Logger myLogger=Logger.getLogger(getClass().getName());
	
	//set up point cut 
	@Pointcut("execution(* com.webcustomertracker.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	//do the same for dao and service
	@Pointcut("execution(* com.webcustomertracker.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.webcustomertracker.springdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	//combine pointcut
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	public void forAppFlow() {}
	
	//add @Before
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		//display method we are calling
		String theMethod=theJoinPoint.getSignature().toShortString();
		myLogger.info("====> in @Before: calling method: "+theMethod);
		//display method args
		Object[] args=theJoinPoint.getArgs();
		for(Object curr:args) {
			myLogger.info("====> argument: "+curr);
		}
	}
	
	//add @AfterReturning
	
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="theResult")
	public void afterReturning(JoinPoint theJoinPoint,Object theResult) {
		//display method we are returning from
		String theMethod=theJoinPoint.getSignature().toShortString();
		myLogger.info("====> in @AfterReturning:from method: "+theMethod);
		
		//display the returned data
		myLogger.info("====> result: "+theResult);
	}
}
