package com.backend.backend.Service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;

import com.backend.backend.DAO.Users.*;
import com.backend.backend.Entity.User.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDAO userDAO;

	public Collection<User> getUsers() {
		return userDAO.getUsers();
	}

	public User createUser(User user) {
		return userDAO.createUser(user);
	}

	public Optional<User> getUserById(BigInteger id) {
		return userDAO.getUserById(id);
	}

	public Optional<User> deleteUserById(BigInteger id) {
		return userDAO.deleteUserById(id);
	}

	public Optional<User> updatePasswordById(BigInteger id, UserUpdatePayload userUpdatePayload) {
		return userDAO.updatePasswordById(id, userUpdatePayload);
	}
	public Optional<User> addPetToUser(BigInteger id, UserUpdatePayload userUpdatePayload) {
		return userDAO.addPetToUser(id, userUpdatePayload);
	}
}
