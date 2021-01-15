package com.backend.backend.DAO.Pets;

import java.math.BigInteger;

import com.backend.backend.Entity.Pet;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PetRepository extends MongoRepository<Pet, BigInteger>{

    
}
