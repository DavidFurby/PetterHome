package com.backend.backend.Service;

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

	public Optional<User> getUserById(Integer id) {
		return userDAO.getUserById(id);
	}
	public Optional<User> deleteUserById(int id) {
		return userDAO.deleteUserById(id);
	}
	public Optional<User> updatePasswordById(int id, UserUpdatePayload userUpdatePayload) {
		return userDAO.updatePasswordById(id, userUpdatePayload); 
	}
}
