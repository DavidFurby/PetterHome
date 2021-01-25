package com.backend.backend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document(collection = "Animals")
public class Animal {



    @Id
    private String id;

    @NotBlank
    @Size(max = 20)
    private final String breed;

    @NotBlank
    private final String animal;

    public Animal(String animal, String breed) {
        this.animal = animal;
        this.breed = breed;
    }
    public String getId() {
        return id;
    }
    public String getAnimal() {
        return animal;
    }
    public String getBreed() {
        return breed;
    }
}
