package com.jstk.BoardGameCapmates.exceptions;

public class NoGameToMeetConditionsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public NoGameToMeetConditionsException(){
		super("There is no game that meets specified condition");
		
	}
	
	public NoGameToMeetConditionsException(String message){
		super(message);
	}
	
	
	

	
	
	
	
}
