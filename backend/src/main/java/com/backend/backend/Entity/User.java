package com.backend.backend.Entity;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class User {
    @Id
    private BigInteger _id;
    private String userName;
    private String password;

    public BigInteger getId() {
        return _id;
    }

    public void setId(BigInteger _id) {
        this._id = _id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String password() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
