package com.backend.backend.DAO.Users;

import java.math.BigInteger;
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

    public Optional<User> deleteUserById(BigInteger id) {
        Optional<User> user = repository.findById(id);
        user.ifPresent(b -> repository.delete(b));
        return user;
    }

    public Optional<User> updatePasswordById(BigInteger id, UserUpdatePayload userUpdatePayload) {
        Optional<User> user = repository.findById(id);
        user.ifPresent(b -> b.setPassword(userUpdatePayload.getPassword()));
        user.ifPresent(b -> repository.save(b));
        return user;
    }

    public Optional<User> addPetToUser(BigInteger id, UserUpdatePayload userUpdatePayload) {
        Optional<User> user = repository.findById(id);
        user.ifPresent(b -> b.setPet(userUpdatePayload.getPets()));
        user.ifPresent(b -> repository.save(b));
        return user;
    }

	public Optional<User> getCurrentUser() {
		return null;
	}
}
