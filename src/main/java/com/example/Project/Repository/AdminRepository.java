package com.example.Project.Repository;

import com.example.Project.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class AdminRepository implements AdminInterface, TokenValidator {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Client> getAllUsers() {
        String sql = "SELECT Clients.username as username,Clients.email as email, Clients.password as password,Clients.Id as uuid,  COUNT(Log.message) as count\n" +
                "FROM Clients \n" +
                "JOIN Log \n" +
                "ON Clients.Id=Log.clientId \n" +
                "GROUP BY Clients.username,Clients.email,Clients.password,Clients.Id";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Client.class));
    }

    @Override
    public boolean checkToken(Token token) {
        String sql = "select name as name, password as password, Id as uuid from Admin";
        List<Admin> admin = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Admin.class));
        if (admin.get(0).getUuid().toString().equalsIgnoreCase(token.getToken())) {
            return true;
        }
        return false;
    }

    @Override
    public String adminLogIn(AdminLogInModel logInModel) {
        String sql = "select name as name, password as password, Id as uuid from Admin  ";
        List<Admin> admin = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Admin.class));
        if (admin.get(0).getName().equalsIgnoreCase(logInModel.getName()) && admin.get(0).getPassword().equalsIgnoreCase(logInModel.getPassword())) {
            return admin.get(0).getUuid().toString();
        }
        return null;
    }

    @Override
    public void adminChangeClientPassword(String Id, String newPassword) {
        String sql = "UPDATE Clients SET password = '" + newPassword + " ' WHERE Id = '" + Id + " ';";
        jdbcTemplate.execute(sql);

    }

    @Override
    public boolean checkIfTokenIsValid(String Token) {
        Pattern pattern =
                Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
        Matcher matcher = pattern.matcher(Token);
        if (matcher.matches()) {
            return true;
        } else
            return false;
    }
}

