package com.jstk.BoardGameCapmates.controllerAdvice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jstk.BoardGameCapmates.exceptions.GameIsAlreadyInUsersCollectionException;
import com.jstk.BoardGameCapmates.exceptions.NoGameToMeetConditionsException;
import com.jstk.BoardGameCapmates.exceptions.NoUserWithThatIDException;

@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {


  
    
    
    @ExceptionHandler({ GameIsAlreadyInUsersCollectionException.class, NoGameToMeetConditionsException.class, NoUserWithThatIDException.class })
    public ResponseEntity<Object> handleException(Exception ex, HttpServletRequest request) {
        return ResponseEntity.badRequest().body("Processing error type: " 
    + ex.getClass().getSimpleName() + "\n"+ "Message: "+ex.getMessage() + "\n"+ "Error from: " + request.getRequestURI());
    }
    

    
    
    
    
}
