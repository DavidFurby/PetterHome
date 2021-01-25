package com.backend.backend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document(collection = "Pets")
public class Pet {

    @Id
    private String id;

    @NotBlank
    @Size(max = 20)
    private final String petName;

    @NotBlank
    private final Integer petAge;
    @NotBlank
    private final Gender gender;
    @NotBlank
    private final Animal animal;

    public Pet(String petName, Integer petAge, Gender gender, Animal animal) {
        this.petName = petName;
        this.petAge = petAge;
        this.gender = gender;
        this.animal = animal;
    }
    public String getId() {
        return id;
    }
    public String getPetName() {
        return petName;
    }
    public Integer getPetAge() {
        return petAge;
    }
    public Animal getAnimal() {
        return animal;
    }
    public Gender getGender() {
        return gender;
    }
}
