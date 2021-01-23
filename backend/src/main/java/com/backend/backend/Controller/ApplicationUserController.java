package com.backend.backend.Controller;

import java.util.Collection;
import java.util.Optional;

import com.backend.backend.Auth.ApplicationUser;
import com.backend.backend.Auth.ApplicationUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/applicationUsers")
public class ApplicationUserController {

    @Autowired
    private ApplicationUserService applicationUserService;

    @GetMapping
    public Collection<ApplicationUser> getUsers() {
        return applicationUserService.getUsers();
    }

    @PostMapping(value = "/register")
    public ApplicationUser register(@RequestBody ApplicationUser applicationUser) {
        return applicationUserService.register(applicationUser);
    }
    
    @GetMapping(value = "/current")
    public Optional<ApplicationUser> getCurrentUser() {
        return applicationUserService.getCurrentUser(); 
    }
 
}
