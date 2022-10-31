package com.example.Project.Model;


import java.util.UUID;

public class Client {

    private UUID uuid;
    private String username;
    private String email;
    private String password;

    private int count;

    public Client(UUID uuid, String username, String email, String password, int count) {
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.count = count;
    }





    public Client() {
    }

    public void setCount(int count) {
        this.count = count;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCount() {
        return count;
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
