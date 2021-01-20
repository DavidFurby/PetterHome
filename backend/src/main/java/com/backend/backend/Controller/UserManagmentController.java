package com.backend.backend.Controller;

import java.util.Collection;
import java.util.Optional;

import com.backend.backend.Model.User.User;

import com.backend.backend.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management/api/v1/users")
public class UserManagmentController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Collection<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(value = "/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping(value = "/current")
    public Optional<User> getCurrentUser() {
        return userService.getCurrentUser();
    }

}
