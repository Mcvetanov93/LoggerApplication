package com.example.Project.Controller;

import com.example.Project.Model.Log;
import com.example.Project.Model.LogType;
import com.example.Project.Model.Token;
import com.example.Project.Repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    private LogRepository logRepository;
    @Autowired
    public LogController(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @PostMapping("/api/logs/create")
    public ResponseEntity<String> CreateLog(@RequestBody Log log , @RequestHeader Token token){
        if(!logRepository.checkToken(token)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect token");
        }

        if (log.getMessage().length()>1024){
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("Message should be less than 1024");
        }


        logRepository.createLog(log,token);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }

}
