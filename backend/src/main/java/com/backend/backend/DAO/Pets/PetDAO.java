package com.backend.backend.DAO.Pets;

import java.util.Collection;

import com.backend.backend.Entity.Pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PetDAO {

    @Autowired
    private PetRepository repository;

    public Collection<Pet> getPets() {
        return repository.findAll();
    }

	public Pet createPet(Pet pet) {
		return repository.insert(pet);
	}
}
