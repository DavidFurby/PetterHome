package com.backend.backend.Controller;

import java.util.Collection;
import java.util.Optional;

import com.backend.backend.Model.User.User;

import com.backend.backend.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management/api/v1/users")
public class UserManagementController {
    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
    public Collection<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(value = "/register")
    @PreAuthorize("hasAuthority('user:pet')")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping(value = "/current")
    @PreAuthorize("hasAuthority('user:pet')")
    public Optional<User> getCurrentUser() {
        return userService.getCurrentUser();
    }
    @DeleteMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('user:pet')")
    public void deleteStudent(@PathVariable("studentId") String id) {
        System.out.println("deleteUser");
        System.out.println(id);
    }

    @PutMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('user:pet')")
    public void updateStudent(@PathVariable("studentId") String id, @RequestBody User user) {
        System.out.println("updateUser");
        System.out.println(String.format("%s %s", id, user));
    }
}
