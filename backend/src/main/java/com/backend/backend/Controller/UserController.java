package com.backend.backend.Controller;

import java.util.Collection;

import com.backend.backend.Entity.User;
import com.backend.backend.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}
