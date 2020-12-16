package com.backend.backend.Entity;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Animals")
public class Animal {
    
    @Id
    private BigInteger _id;
    private String animalName;
    private String breed;

    public BigInteger getId() {
        return _id;
    }

    public void setId(BigInteger _id) {
        this._id = _id;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
