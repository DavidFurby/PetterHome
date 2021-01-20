package com.backend.backend.DAO.Users;

import java.util.Collection;
import java.util.Optional;

import com.backend.backend.Model.User.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAO {

    @Autowired
    private UserRepository repository;

    public Collection<User> getUsers() {
        return repository.findAll();
    }

    public User register(User user) {
        return repository.insert(user);
    }

    public User login(User user) {
        return repository.insert(user);
    }



	public Optional<User> getCurrentUser() {
		return null;
	}
}
