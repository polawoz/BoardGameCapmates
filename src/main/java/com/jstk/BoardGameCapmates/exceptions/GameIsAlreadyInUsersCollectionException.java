package com.jstk.BoardGameCapmates.exceptions;

public class GameIsAlreadyInUsersCollectionException extends Exception {
	
	
	public GameIsAlreadyInUsersCollectionException(){
		super("This users game collection already contains game type with that name!");
		
		
	}
	
	public GameIsAlreadyInUsersCollectionException(String message){
		super(message);
	}
	

}
