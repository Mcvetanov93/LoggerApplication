package com.example.Project.Controller;

import com.example.Project.Model.LogInModel;
import com.example.Project.Model.Token;
import com.example.Project.Repository.AdminRepository;
import org.hibernate.annotations.common.util.impl.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class AdminController {
    private final AdminRepository adminRepository;
    @Autowired
    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping(" /api/clients")
    public ResponseEntity<String> getAllClients(@RequestHeader Token token){
        if (adminRepository.checkToken(token)){
            adminRepository.getAllUsers();
           return ResponseEntity.status(HttpStatus.OK).body("OK");
        }

        Pattern uuid =
                Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
        Matcher matcher=uuid.matcher(token.getToken());
            if (matcher.matches()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correct token, but not admin");
            }
            else
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Incorrect token");
    }
    @PostMapping("/admin/login")
    public ResponseEntity<String> adminLogIn(@RequestBody LogInModel logInModel){
      return   ResponseEntity.status(HttpStatus.ACCEPTED).body(adminRepository.adminLogIn(logInModel));
    }
    @PatchMapping("/api/clients/{clientId}/reset-password")
    public ResponseEntity<String> changeClientPassword(@RequestBody String Id,String newPassword, @RequestHeader Token token){
        if (adminRepository.checkToken(token)){
            adminRepository.adminChangeClientPassword(Id,newPassword);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
        }

        Pattern uuid =
                Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
        Matcher matcher=uuid.matcher(token.getToken());
        if (matcher.matches()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correct token, but not admin");
        }
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Incorrect token");
    }
}
