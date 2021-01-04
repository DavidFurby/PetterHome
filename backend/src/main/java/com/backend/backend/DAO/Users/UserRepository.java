package com.backend.backend.DAO.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.backend.backend.Entity.User.*;
@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

    
}
