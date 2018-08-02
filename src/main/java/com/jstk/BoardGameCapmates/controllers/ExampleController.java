package com.jstk.BoardGameCapmates.controllers;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@ResponseBody
@Controller
public class ExampleController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(){
		
		return "Welcome to BoardGameCapmates!";
	
	}
	
	
	
	
	
	
	
	
	
	
}
