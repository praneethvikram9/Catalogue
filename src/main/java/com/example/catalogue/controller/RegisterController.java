package com.example.catalogue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.catalogue.domain.User;
import com.example.catalogue.exceptions.UserNameExistsException;
import com.example.catalogue.exceptions.UserNotFoundException;
import com.example.catalogue.service.RegisterService;

@RestController
@RequestMapping(value="register/user")
public class RegisterController {

	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private Environment env;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
    @PostMapping("/add")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        ResponseEntity<?> responseEntity;
        String message = env.getProperty("user.controller.save.user");
        try
        {
            registerService.saveUser(user);
            responseEntity = new ResponseEntity<String>(message, HttpStatus.CREATED);
        } catch (UserNameExistsException ex){
            logger.error(ex.getMessage());
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_IMPLEMENTED);
        } catch (Exception ex){
            logger.error(ex.getMessage());
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_IMPLEMENTED);

        }
        return responseEntity;
    }
    
    @GetMapping("/allUsers")
    public ResponseEntity<?> getUsers(){
    	ResponseEntity<?> responseEntity;
    	
    	try {
    		responseEntity = new ResponseEntity<List<User>>(registerService.getAllUsers(),HttpStatus.OK);
    	} catch (UserNotFoundException ex){
            logger.error(ex.getMessage());
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_IMPLEMENTED);
        } catch(Exception ex) {
    		logger.error(ex.getMessage());
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_IMPLEMENTED);
    	}
    	return responseEntity;
    }
	
}
