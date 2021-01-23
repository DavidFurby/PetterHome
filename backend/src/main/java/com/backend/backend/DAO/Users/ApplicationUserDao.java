package com.backend.backend.DAO.Users;

import java.util.Collection;
import java.util.Optional;

import com.backend.backend.Auth.ApplicationUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationUserDao {

     @Autowired
     public ApplicationUserRepository repository; 

	 public Collection<ApplicationUser> getUsers() {
          return repository.findAll();
     };


	public ApplicationUser register(ApplicationUser applicationUser) {
          return repository.insert(applicationUser);
     }


	public Optional<ApplicationUser> getCurrentUser() {
		return null;
	};
}
