package com.backend.backend.Service;

import java.util.Collection;

import com.backend.backend.DAO.Users.*;
import com.backend.backend.Entity.User;

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
}
