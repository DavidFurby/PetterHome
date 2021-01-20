package com.backend.backend.Service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;

import com.backend.backend.DAO.Pets.*;
import com.backend.backend.Model.Pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PetService {
    @Autowired
    private PetDAO petDAO;

    public Collection<Pet> getPets() {
        return petDAO.getPets();
    }

	public Pet createPet(Pet pet) {
		return petDAO.createPet(pet);
    }
    public Optional<Pet> getPetById(BigInteger id) {
		return petDAO.getPetById(id);
	}
}
