package com.example.Project.Model;


import java.util.UUID;

public class Admin {

    private String name;
    private String password;
    private UUID uuid;

    public Admin() {
    }

    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
        this.uuid = UUID.randomUUID();
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

    public UUID getUuid() {
        return uuid;
    }



    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", uuid=" + uuid +
                '}';
    }
}
