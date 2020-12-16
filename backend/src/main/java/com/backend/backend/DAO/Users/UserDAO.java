package com.backend.backend.DAO.Users;

import java.util.Collection;

import com.backend.backend.Entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAO {

    @Autowired
    private UserRepository repository;

    public Collection<User> getUsers() {
        return repository.findAll();
    }

	public User createUser(User user) {
		return repository.insert(user);
	}
}
