package com.example.Project.Controller;

import com.example.Project.Model.AdminLogInModel;
import com.example.Project.Model.LogInModel;
import com.example.Project.Model.Token;
import com.example.Project.Repository.AdminRepository;
import org.aspectj.weaver.ast.Var;
import org.hibernate.annotations.common.util.impl.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class AdminController {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping("/api/clients")
    public ResponseEntity<List> getAllClients(@RequestHeader Token token) {
        if (adminRepository.checkIfTokenIsValid(token.getToken())) {
            if (adminRepository.checkToken(token)) {

                return ResponseEntity.status(HttpStatus.OK).body(adminRepository.getAllUsers());
            }
        } else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<String> adminLogIn(@RequestBody AdminLogInModel logInModel) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(adminRepository.adminLogIn(logInModel));
    }

    @PatchMapping("/api/clients/{clientId}/reset-password")
    public ResponseEntity<String> changeClientPassword(@PathVariable String clientId,@RequestBody String newPassword, @RequestHeader Token token) {
        if (adminRepository.checkIfTokenIsValid(token.getToken())) {
            if (adminRepository.checkToken(token)) {
                adminRepository.adminChangeClientPassword(clientId, newPassword);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Password updated.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Incorrect token");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is valid but not admin");
    }

}
