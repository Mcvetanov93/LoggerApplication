package com.example.Project.Model;


import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;


public class Log {

    private String message;
    private LogType logType;
    private LocalDate date;
    private String clientId;

    public Log(String message, LogType logType,  String clientId) {
        this.message = message;
        this.logType = logType;
        this.date=LocalDate.now();
        this.clientId = clientId;
    }

    public Log() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
