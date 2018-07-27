package com.jstk.BoardGameCapmates.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jstk.BoardGameCapmates.data.GameType;
import com.jstk.BoardGameCapmates.data.User;
import com.jstk.BoardGameCapmates.mappers.GameCollectionMapper;
import com.jstk.BoardGameCapmates.repository.GameCollectionDao;
import com.jstk.BoardGameCapmates.repository.UserDao;
import com.jstk.BoardGameCapmates.services.GameCollectionManager;

@ResponseBody
@Controller
public class ExampleController {

	@RequestMapping(value="/example", method=RequestMethod.GET)
	public String usersGameCollection(){
		
		return "hello world";
	
	}
	
	
	
	
	
	
	
	
	
	
}
