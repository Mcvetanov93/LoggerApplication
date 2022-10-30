package com.example.Project.Model;

public class AdminLogInModel {
    private String name;
    private String password;

    public AdminLogInModel(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public AdminLogInModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdminLogInModel{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
