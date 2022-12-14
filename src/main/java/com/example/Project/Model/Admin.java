package com.example.Project.Model;


import java.util.UUID;

public class Admin {

    private String name;
    private String password;
    private UUID uuid;

    public Admin() {
    }

    public Admin(String name, String password,UUID uuid) {
        this.name = name;
        this.password = password;
        this.uuid = uuid;
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

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
