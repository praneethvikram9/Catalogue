package com.example.catalogue.service;

import java.util.List;

import com.example.catalogue.domain.User;
import com.example.catalogue.exceptions.InvalidDetailsException;
import com.example.catalogue.exceptions.UserNameExistsException;
import com.example.catalogue.exceptions.UserNotFoundException;

public interface RegisterService {

	public void saveUser(User user) throws UserNameExistsException, InvalidDetailsException;

	public List<User> getAllUsers() throws UserNotFoundException;

}
