package com.example.Project.Repository;

import com.example.Project.Model.Client;
import com.example.Project.Model.LogInModel;
import com.example.Project.Model.Token;

import java.util.List;

public interface AdminInterface {
    List<Client> getAllUsers();
    boolean checkToken(Token token );

    String adminLogIn(LogInModel logInModel);

    void adminChangeClientPassword( String Id,String newPassword);
}
