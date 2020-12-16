package com.backend.backend.Service;

import java.util.Collection;

import com.backend.backend.DAO.Animals.AnimalDAO;
import com.backend.backend.Entity.Animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AnimalService {
    @Autowired
    private AnimalDAO animalDAO;

    public Collection<Animal> getAnimals() {
        return animalDAO.getAnimals();
    }

	public Animal createAnimal(Animal animal) {
		return animalDAO.createAnimal(animal);
	}
}
