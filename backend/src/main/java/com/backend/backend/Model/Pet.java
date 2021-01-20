package com.backend.backend.Model;

import java.lang.reflect.Array;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Pets")
public class Pet {

    @Id
    private String id;
    private String petName;
    private String animal;
    private String breed; 
    private Integer age;
    private Gender gender;
    private Array needs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed; 
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    enum Gender {
        Male, Female;
    }

    public Array getNeeds() {
        return needs;
    }

    public void setNeeds(Array needs) {
        this.needs = needs;
    }
}
