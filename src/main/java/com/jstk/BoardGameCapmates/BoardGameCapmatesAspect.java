package com.jstk.BoardGameCapmates;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BoardGameCapmatesAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(BoardGameCapmatesAspect.class);

//	@Before("execution(* com.jstk.BoardGameCapmates.repository.*.*(..))")
//	public void beforeFindOneUserEntity() {
//		LOGGER.info ("Method findOneUserEntity was called as Before Aspect");
//		
//	}


	@Around("execution(* com.jstk.BoardGameCapmates.services.PlayabilityManager.createListOf*(..))")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		LOGGER.info("Going to call the method.");
		Object output = pjp.proceed();
		LOGGER.info("Method execution completed.");
		long elapsedTime = System.currentTimeMillis() - start;
		LOGGER.info("Method execution time: " + elapsedTime + " milliseconds.");
		return output;
	}

}
