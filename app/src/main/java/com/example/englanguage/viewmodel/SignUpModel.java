package com.example.englanguage.viewmodel;

public class SignUpModel {
    private String email;
    private String password;
    private String name;
    private String conformPassword;

    public SignUpModel(String email, String password, String name, String conformPassword) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.conformPassword = conformPassword;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConformPassword() {
        return conformPassword;
    }

    public void setConformPassword(String conformPassword) {
        this.conformPassword = conformPassword;
    }
}
