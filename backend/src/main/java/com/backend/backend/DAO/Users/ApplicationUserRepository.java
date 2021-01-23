package com.backend.backend.DAO.Users;

import java.math.BigInteger;

import com.backend.backend.Auth.ApplicationUser;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, BigInteger> {
}
