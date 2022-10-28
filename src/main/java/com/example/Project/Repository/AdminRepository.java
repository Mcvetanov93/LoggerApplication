package com.example.Project.Repository;

import com.example.Project.Model.Admin;
import com.example.Project.Model.Client;
import com.example.Project.Model.LogInModel;
import com.example.Project.Model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class AdminRepository implements AdminInterface {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Client> getAllUsers() {
        String sql="select username as username, email as email, Id as uuid, password as password from Clients";
        List<Client> list=jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Client.class));
        return list;
    }

    @Override
    public boolean checkToken( Token token) {
        String sql="select username as username, email as email, Id as uuid";
        List <Admin> admin=jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Admin.class));
        if(admin.get(0).getUuid().toString().equalsIgnoreCase(token.getToken())){
            return  true;
        }
        return false;
    }

    @Override
    public String adminLogIn(LogInModel logInModel) {
        String sql="select username as username, email as email, Id as uuid, ";
        List <Admin> admin=jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Admin.class));
        if (admin.get(0).getName().equalsIgnoreCase(logInModel.getEmailOrUsername())&&admin.get(0).getPassword().equalsIgnoreCase(logInModel.getPassword())){
            return admin.get(0).getUuid().toString();
        }
        return null;
    }

    @Override
    public void adminChangeClientPassword( String Id,String newPassword) {
        String sql="UPDATE Clients SET password = '"+newPassword+" ' WHERE Id = '"+Id +" ';";
        jdbcTemplate.execute(sql);

    }
}
