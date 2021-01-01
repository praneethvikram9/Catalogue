package com.example.catalogue.service;

import com.example.catalogue.domain.User;
import com.example.catalogue.exceptions.UserNotFoundException;

public interface LoginService {

	public boolean findUser(User user) throws UserNotFoundException;

}
