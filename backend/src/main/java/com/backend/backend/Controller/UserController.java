package com.backend.backend.Controller;

import java.util.Collection;
import java.util.Optional;

import com.backend.backend.Entity.User.*;
import com.backend.backend.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Collection<User> getUser() {
        return userService.getUsers();
    }

    @PostMapping
    public User postUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping(value = "/{id}")
    public Optional<User> getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @DeleteMapping(value = "/{id}")
    public Optional<User> deleteUserById(@PathVariable("id") int id) {
        return userService.deleteUserById(id);
    }

    @PutMapping(value = "/{id}")
    public Optional<User> deleteUserById(@PathVariable("id") int id, @RequestBody UserUpdatePayload userUpdatePayload) {
        return userService.updatePasswordById(id, userUpdatePayload);
    }
}
