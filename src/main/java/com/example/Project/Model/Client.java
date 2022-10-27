package com.example.Project.Model;


import java.util.UUID;

public class Client {

    private UUID uuid;
    private String username;
    private String email;
    private String password;

    public Client( UUID uuid,String username, String email, String password) {
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Client() {
    }

    public UUID getUuid() {
        return uuid;
    }



    public String getusername() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
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

    @Override
    public String toString() {
        return "Client{" +
                "uuid=" + uuid +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
