package com.example.Project.Controller;

import com.example.Project.Model.Log;
import com.example.Project.Model.LogType;
import com.example.Project.Model.Token;
import com.example.Project.Repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@RestController
public class LogController {
    private LogRepository logRepository;

    @Autowired
    public LogController(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @PostMapping("/api/logs/create")
    public ResponseEntity<String> CreateLog(@RequestBody Log log, @RequestHeader Token token) {
        if (!logRepository.checkToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect token");
        }

        if (log.getMessage().length() > 1024) {
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("Message should be less than 1024");
        }


        logRepository.createLog(log, token);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }

    @GetMapping("/api/logs/search")
    public ResponseEntity<List> getLogs(@RequestParam(value="message",required = false) String message,
                                        @RequestParam(value = "logType", required = false) String logType,
                                        @RequestParam(value = "firstDate", required = false) String firstDate,
                                        @RequestParam(value = "secondDate", required = false) String secondDate,
                                        @RequestHeader Token token ){
        if (!logRepository.checkToken(token)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } else if (!LogType.values().equals(logType)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(logRepository.getLogs(message,logType,firstDate,secondDate, token.getToken()));
    }
}


