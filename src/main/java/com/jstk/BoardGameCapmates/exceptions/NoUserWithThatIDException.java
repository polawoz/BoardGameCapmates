package com.jstk.BoardGameCapmates.exceptions;

public class NoUserWithThatIDException extends Exception {
	
	
	
	public NoUserWithThatIDException(){
		super("User with that ID does not exist!");
	}
	
	
	
	public NoUserWithThatIDException(String message){
		super(message);
	}

}
