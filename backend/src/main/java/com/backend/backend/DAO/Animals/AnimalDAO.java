package com.backend.backend.DAO.Animals;

import java.util.Collection;

import com.backend.backend.Entity.Animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnimalDAO {

    @Autowired
    private AnimalRepository repository;

    public Collection<Animal> getAnimals() {
        return repository.findAll();
    }

	public Animal createAnimal(Animal animal) {
		return repository.insert(animal);
	}
}
