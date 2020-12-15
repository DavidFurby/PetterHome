package com.backend.backend.Service;

import java.util.Collection;

import com.backend.backend.DAO.PetDAO;
import com.backend.backend.Entity.Pet;

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
}
