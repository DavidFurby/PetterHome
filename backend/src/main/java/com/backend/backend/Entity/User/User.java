package com.backend.backend.Entity.User;

import java.lang.reflect.Array;
import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")

public class User {
    @Id
    private BigInteger id;
    private String username;
    private String password;
    private Array pet;

    public BigInteger getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
