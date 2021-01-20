package com.backend.backend.Service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;

import com.backend.backend.DAO.Users.*;
import com.backend.backend.Model.User.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDAO userDAO;

	public Collection<User> getUsers() {
		return userDAO.getUsers();
	}

	public User register(User user) {
		return userDAO.register(user);
	}

	public User login(User user) {
		return userDAO.login(user);
	}


	public Optional<User> getCurrentUser() {
		return userDAO.getCurrentUser();
	}
}
