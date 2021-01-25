package com.backend.backend.Controller;


import com.backend.backend.Model.User;
import com.backend.backend.Payload.request.UpdateUserRequest;
import com.backend.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/getCurrentUser")
    @PreAuthorize("hasRole('USER')")
    public Optional<User> getCurrentUser(@RequestParam String id) {
        return repository.findById(id);
    }
    @PutMapping("/addPetToUser")
    @PreAuthorize("hasRole('USER')")
    public Optional<User> addPetToUser(@RequestBody UpdateUserRequest updateUserRequest, @RequestParam String id) {
        Optional<User> user = repository.findById(id);
        user.ifPresent(b -> b.setPets(updateUserRequest.getPets()));
        user.ifPresent(b -> repository.save(b));
        return user;
    }


    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}