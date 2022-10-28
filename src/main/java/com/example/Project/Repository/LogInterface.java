package com.example.Project.Repository;

import com.example.Project.Model.Log;
import com.example.Project.Model.Token;
import org.springframework.stereotype.Repository;

@Repository
public interface LogInterface {
    void createLog(Log log, Token token);
    boolean checkToken(Token token);
}
