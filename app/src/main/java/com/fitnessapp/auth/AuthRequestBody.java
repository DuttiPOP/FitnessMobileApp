package com.fitnessapp.auth;

public class AuthRequestBody {
    private final String email;
    private final String password;

    public AuthRequestBody(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
