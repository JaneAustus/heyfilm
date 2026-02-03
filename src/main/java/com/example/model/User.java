package com.example.model;

import java.io.Serializable;
import java.math.BigInteger;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    private String name;
    private BigInteger mobile;
    private String email;
    private String userName;
    private String password;

    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigInteger getMobile() { return mobile; }
    public void setMobile(BigInteger mobile) { this.mobile = mobile; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public User() {
        System.out.println("User object called");
    }

    public User(int userID, String name, BigInteger mobile, String email, String userName, String password) {
        this.userID = userID;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }
}

