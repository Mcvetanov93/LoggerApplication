package com.example.Project.Repository;

import com.example.Project.Model.Client;
import com.example.Project.Model.LogInModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class ClientRepository implements ClientInterface {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean checkForDuplicateUsername(Client client) {
        String sql = "SELECT username as username FROM Clients";
        List<Client> listOfUsernames = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Client.class));
        System.out.println(listOfUsernames);
        System.out.println(client);
        boolean condition = true;
        for (var user : listOfUsernames) {
            if (user.getusername().equalsIgnoreCase(client.getusername())) {
                condition = false;
                break;
            }
        }


        return condition;
    }

    @Override
    public boolean checkEmailUsernameAndPassword(Client client) {
        boolean condition = true;
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern patter = Pattern.compile(regex);
        Matcher matcher = patter.matcher(client.getEmail());
        //Checking if email is valid
        if (!matcher.matches()) {
            condition = false;
        }
        //Checking if username is longer than 3 characters
        if (client.getusername().length() < 3) {
            condition = false;
        }

        String pass = "^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]+$";
        Pattern patter2 = Pattern.compile(pass);
        matcher = patter2.matcher(client.getPassword());
        //Checking if password is valid
        if (!matcher.matches()) {
            condition = false;
        }

        return condition;

    }

    @Override
    public void registerClient(Client client) {


        String sql = "Insert into Clients (username,email,Id,password) values ('"
                + client.getusername() + "','" + client.getEmail() + "','" + UUID.randomUUID() + "','" + client.getPassword() + "')";
        jdbcTemplate.execute(sql);


    }

    @Override
    public String returnToken(LogInModel logInModel) {
        String sql="SELECT * FROM Clients";
        List <Client> listOfClients=jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Client.class));
        for (var clients:listOfClients){
            if (logInModel.getEmailOrUsername().equalsIgnoreCase(clients.getEmail())||logInModel.getEmailOrUsername().equalsIgnoreCase(clients.getusername())){
                return clients.getUuid().toString();
            }
        }
        return null;
    }

    @Override
    public boolean clientLogIn(LogInModel logInModel) {
        String sql="SELECT * FROM Clients";
        List <Client> listOfClients=jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Client.class));
        boolean condition=false;
        //Checking if username or email matches database,if so checking for matching passwords
        for (var clients :listOfClients){
            if ((logInModel.getEmailOrUsername().equalsIgnoreCase(clients.getEmail())||logInModel.getEmailOrUsername().equalsIgnoreCase(clients.getusername()))&&logInModel.getPassword().equalsIgnoreCase(clients.getPassword())){
                condition=true;
                break;
            }
        }


       return condition;
    }

    @Override
    public List<Client> getallclients() {
        String sql = "select username as username, email as email, Id as Id, password as password from Clients";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Client.class));
    }
}
