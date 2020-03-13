package com.vandemos.emailservice.model;

public class Mail {

    private String email;
    private String username;
    private String firstName;
    private String hash;

    public Mail(){}

    public Mail(String email, String username, String firstName, String hash) {
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.hash = hash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
