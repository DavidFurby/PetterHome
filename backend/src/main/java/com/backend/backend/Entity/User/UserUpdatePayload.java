package com.backend.backend.Entity.User;

import java.lang.reflect.Array;

public class UserUpdatePayload {

    private String username;

    public String getUsername() {
        return username;
    }

    private String password;

    public String getPassword() {
        return password;
    }
    
    private Array pets;

    public Array getPets() {
        return pets;
    }
}