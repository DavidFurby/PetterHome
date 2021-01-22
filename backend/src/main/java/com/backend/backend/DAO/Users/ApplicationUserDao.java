package com.backend.backend.DAO.Users;

import java.util.Optional;

import com.backend.backend.Auth.ApplicationUser;

import org.springframework.stereotype.Component;

@Component
public interface ApplicationUserDao {
     Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
