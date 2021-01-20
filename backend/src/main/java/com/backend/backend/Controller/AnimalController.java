package com.backend.backend.Controller;

import java.util.Collection;

import com.backend.backend.Model.Animal;
import com.backend.backend.Service.AnimalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping
    public Collection<Animal> getAnimals() {
        return animalService.getAnimals();
    }

    @PostMapping
    public Animal postAnimal(@RequestBody Animal animal) {
        return animalService.createAnimal(animal);
    }
}
