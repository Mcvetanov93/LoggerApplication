package com.example.Project.Model;

public class Token {
    private String token;

    @Override
    public String toString() {
        return  token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Token() {
    }

    public Token(String token) {
        this.token = token;
    }
}
