package com.example.Project.Repository;

import com.example.Project.Model.AdminLogInModel;
import com.example.Project.Model.Client;
import com.example.Project.Model.LogInModel;
import com.example.Project.Model.Token;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminInterface {

    List<Client> getAllUsers();
    boolean checkToken(Token token );

    String adminLogIn(AdminLogInModel adminLogInModel);

    void adminChangeClientPassword( String Id,String newPassword);
}
