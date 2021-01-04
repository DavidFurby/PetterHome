package com.backend.backend.DAO.Users;

import java.lang.StackWalker.Option;
import java.util.Collection;
import java.util.Optional;

import com.backend.backend.Entity.User.*;

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

    public Optional<User> getUserById(int id) {
        return repository.findById(id);
    }

    public Optional<User> deleteUserById(int id) {
        Optional<User> user = repository.findById(id);
        user.ifPresent(b -> repository.delete(b));
        return user;
    }

    public Optional<User> updatePasswordById(int id, UserUpdatePayload userUpdatePayload) {
        Optional<User> user = repository.findById(id);
        user.ifPresent(b -> b.setPassword(userUpdatePayload.getPassword()));
        return user; 
    }
}
