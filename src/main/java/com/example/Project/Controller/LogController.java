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
import java.util.UUID;

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

    public ResponseEntity<?> getLogs(@RequestParam(value="message",required = false) String message,
                                     @RequestParam(value = "logType", required = false) Integer logType,
                                     @RequestParam(value = "firstDate", required = false) String firstDate,
                                     @RequestParam(value = "secondDate", required = false) String secondDate,
                                     @RequestHeader String authorization
    ) throws ParseException {
        UUID logToken = logRepository.getId(authorization);
        if (logToken==null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect token");
        }
        if (firstDate!=null && secondDate!=null &&  logRepository.checkDate(firstDate, secondDate))
        { return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid dates");  }
        if (logType!=null && logType!=1 && logType!=2 && logType!=3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid logType");
        }
        return ResponseEntity.status(HttpStatus.OK).body(logRepository.getLogs(message, logType, firstDate, secondDate, logToken));}

}
