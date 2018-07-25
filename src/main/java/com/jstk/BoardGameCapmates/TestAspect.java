package com.jstk.BoardGameCapmates;

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
		LOGGER.info ("All books were found") ;
		}
	//@Around czas wykonywania metody wybranej

	
	
	
}
