package com.example.Project.Model;


import java.time.LocalDate;
import java.util.UUID;


public class Log {
    public Log( String message, LogType logType) {
        this.id = UUID.randomUUID();
        this.message = message;
        this.logType = logType;
        this.date = LocalDate.now();
    }

    public Log() {
    }


    private UUID id;
    private String message;
    private LogType logType;
    private LocalDate date;

    public UUID getId() {
        return id;
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

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", logType=" + logType +
                ", date=" + date +
                '}';
    }
}
