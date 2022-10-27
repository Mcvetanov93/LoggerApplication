package com.example.Project.Controller;

import com.example.Project.Model.Client;
import com.example.Project.Model.LogInModel;
import com.example.Project.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class ClientController {
    private final ClientRepository clientRepository;
    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping("/Register")
    public ResponseEntity<String> Register(@RequestBody Client client){
        if (!clientRepository.checkEmailUsernameAndPassword(client)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email must be valid\n" +
                    "username at least 3 characters\n" +
                    "password at least 8 characters and one letter and one number");
        }
        if (!clientRepository.checkForDuplicateUsername(client)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("username already exists\n" +
                    "email already exists");
        }

        clientRepository.registerClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }

    @PostMapping("/Login")
    public ResponseEntity<String> Login(@RequestBody LogInModel logInModel){
        if(!clientRepository.clientLogIn(logInModel)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email/Username or password incorrect");
        }

        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.returnToken(logInModel));
    }



    @GetMapping("/d")
    public List<Client> getall(){
        return clientRepository.getallclients();
    }

}
