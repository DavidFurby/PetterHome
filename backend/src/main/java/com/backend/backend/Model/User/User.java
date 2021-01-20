package com.backend.backend.Model.User;

import java.lang.reflect.Array;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")

public class User {
    
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private Array pet;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Array getPet() {
        return pet;
    }

    public void setPet(Array pet) {
        this.pet = pet;
    }
}
