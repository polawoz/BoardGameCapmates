package com.jstk.BoardGameCapmates;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(TestAspect.class);
	
	@Before("execution(* com.jstk.BoardGameCapmates.repository.UserDao.*(..))") 
	public void beforeGetAllValues() {
		LOGGER.info ("Test logger") ;
		}


	
	@Around("createListOfPossibleChallenges()")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		LOGGER.info("Going to call method createListOfPossibleChallenges");
		Object output = pjp.proceed();
		LOGGER.info("Method createListOfPossibleChallenges execution completed");
		long elapsedTime = System.currentTimeMillis() - start;
		LOGGER.info("Method createListOfPossibleChallenges execution time: "+ elapsedTime + " milliseconds.");
		return output;
		
		
	}
	
	
}
