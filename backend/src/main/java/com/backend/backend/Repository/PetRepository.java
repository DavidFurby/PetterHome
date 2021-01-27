package com.backend.backend.Repository;

import com.backend.backend.Model.ERole;
import com.backend.backend.Model.Pet;
import com.backend.backend.Model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PetRepository extends MongoRepository<Pet, String> {
    Optional<Pet> findByPetName(Pet petName);

}
