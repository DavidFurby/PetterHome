package com.backend.backend.Controller;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;

import com.backend.backend.Model.User.*;
import com.backend.backend.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/users")
public class UserController {

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
    @DeleteMapping(value = "/deleteAccount/{id}")
    public Optional<User> deleteUserById(@PathVariable("id") BigInteger id) {
        return userService.deleteUserById(id);
    }

    @PutMapping(value = "/updatePassword/{id}")
    public Optional<User> updatePasswordById(@PathVariable("id") BigInteger id,
            @RequestBody UserUpdatePayload userUpdatePayload) {
        return userService.updatePasswordById(id, userUpdatePayload);
    }

    @PostMapping(value = "/addPet/{id}")
    public Optional<User> addPetToUser(@PathVariable("id") BigInteger id,
            @RequestBody UserUpdatePayload userUpdatePayload) {
        return userService.addPetToUser(id, userUpdatePayload);
    }
}
