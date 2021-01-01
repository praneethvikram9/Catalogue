package com.example.catalogue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.catalogue.domain.User;
import com.example.catalogue.exceptions.UserNotFoundException;
import com.example.catalogue.service.LoginService;

@RestController
@RequestMapping("login/user")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private Environment env;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@PostMapping("/validate")
	public ResponseEntity<?> validateUser(@RequestBody User user) {
		ResponseEntity<?> response;
        try
        {
            loginService.findUser(user);
            response = new ResponseEntity<String>(env.getProperty("user.found"), HttpStatus.CREATED);
        } catch (UserNotFoundException ex){
            logger.error(ex.getMessage());
            response = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_IMPLEMENTED);
        }
		return response;
		
	}

}
