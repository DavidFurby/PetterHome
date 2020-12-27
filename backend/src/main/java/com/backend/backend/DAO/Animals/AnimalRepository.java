package com.backend.backend.DAO.Animals;

import com.backend.backend.Entity.Animal;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends MongoRepository<Animal, Integer> {

}