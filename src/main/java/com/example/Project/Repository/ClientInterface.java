package com.example.Project.Repository;

import com.example.Project.Model.Client;
import com.example.Project.Model.LogInModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientInterface {
    void registerClient(Client client);
    boolean clientLogIn (LogInModel logInModel);
    boolean checkForDuplicateUsername(Client client);
    List<Client>getallclients();
    boolean checkEmailUsernameAndPassword(Client client);
    String returnToken(LogInModel logInModel);

}
