package com.example.catalogue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.catalogue.domain.User;
import com.example.catalogue.exceptions.UserNotFoundException;
import com.example.catalogue.repository.UserRepository;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Environment env;

	@Override
	public boolean findUser(User user) throws UserNotFoundException {
		User userBody;
		 if(userRepository.existsById(user.getEmail()) ) {
			 userBody = userRepository.getOne(user.getEmail());
			 if(!validate(userBody,user)) {
				 throw new UserNotFoundException(env.getProperty("password.error"));
			 }
			 else {
				 return true;
			 }
		 }
		 else {
			 throw new UserNotFoundException(env.getProperty("no.user.with.email.error"));
		 }
		
	}
	
	
	private boolean validate(User user1,User user2) {
		if(!user1.getPassword().equals(user2.getPassword())) {
			return false;
		}
		return true;
	}

}
