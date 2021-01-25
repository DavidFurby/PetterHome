package com.backend.backend.Payload.request;

import com.backend.backend.Model.Animal;
import org.checkerframework.checker.units.qual.A;

public class AddPetRequest {

    private String username;
    private String password;
    private String petName;

    private Integer petAge;
    private Animal animal;

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Integer getPetAge() {
        return petAge;
    }

    public void setPetAge(Integer petAge) {
        this.petAge = petAge;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
