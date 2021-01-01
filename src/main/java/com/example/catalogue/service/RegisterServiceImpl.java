package com.example.catalogue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.catalogue.domain.User;
import com.example.catalogue.exceptions.InvalidDetailsException;
import com.example.catalogue.exceptions.UserNameExistsException;
import com.example.catalogue.exceptions.UserNotFoundException;
import com.example.catalogue.repository.UserRepository;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Environment env;

	@Override
	public void saveUser(User user) throws UserNameExistsException,InvalidDetailsException {
		if(userRepository.existsById(user.getEmail())) {
			throw new UserNameExistsException(env.getProperty("user.already.exists.error"));
		}
		else {
			if(user.getEmail().isEmpty() || user.getEmail() ==null) {
				throw new InvalidDetailsException(env.getProperty("email.required.error"));
			}
			else if (user.getName().isEmpty() || user.getName() == null) {
				throw new InvalidDetailsException(env.getProperty("name.required.error"));
			}
			else if(user.getPassword().isEmpty() || user.getPassword() == null) {
				throw new InvalidDetailsException(env.getProperty("password.required.error"));
			}
			else {
				userRepository.save(user);
			}
			
		}
		
	}

	@Override
	public List<User> getAllUsers() throws UserNotFoundException {
		List<User> userList = userRepository.findAll();
		if(userList.isEmpty()) {
			throw new UserNotFoundException(env.getProperty("no.users.error"));
		}
		return userList;
	}

}
