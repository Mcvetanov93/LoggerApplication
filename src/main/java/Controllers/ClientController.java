package Controllers;

import Model.Client;
import Repository.ClientRepository;
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
    @PostMapping("/a")
    public ResponseEntity<Void> Register(@RequestBody Client client){
        if (clientRepository.checkEmailUsernameAndPassword(client)){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("dsa");
        } else if (clientRepository.checkForDuplicateUsername(client)) {
            ResponseEntity.status(HttpStatus.CONFLICT).body("asd");
        }
        clientRepository.registerClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
    @GetMapping("/d")
    public List<Client> getall(){
        return clientRepository.getallclients();
    }

}
