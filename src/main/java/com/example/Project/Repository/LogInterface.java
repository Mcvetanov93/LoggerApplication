package com.example.Project.Repository;

import com.example.Project.Model.Log;
import com.example.Project.Model.Token;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LogInterface {
    void createLog(Log log, Token token);
    boolean checkToken(Token token);
     List<Log> getLogs(String message, String logType, String firstDate, String secondDate,String token);
}
