package com.example.Project.Repository;

import com.example.Project.Model.Client;
import com.example.Project.Model.Log;
import com.example.Project.Model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
@Repository
public class LogRepository implements LogInterface{
    @Override
    public List<Log> getLogs(String message, Integer logType, String firstDate, String secondDate, UUID logToken) {
        String query = "";
        if (message==null) {query = "SELECT * FROM LOGS WHERE logType='"+logType+"' AND dateOfLog BETWEEN '"+firstDate+"' AND '"+secondDate+"' AND token='"+logToken+"'";}
        if (logType==null) {query = "SELECT * FROM LOGS WHERE message LIKE '%"+message+"%' AND dateOfLog BETWEEN '"+firstDate+"' AND '"+secondDate+"' AND token='"+logToken+"'";}
        if (firstDate==null && secondDate==null) {query = "SELECT * FROM LOGS WHERE message LIKE'%"+message+"%' AND logType='"+logType+"' AND token='"+logToken+"'";}
        if (message==null && logType==null) {query = "SELECT * FROM LOGS WHERE dateOfLog BETWEEN '"+firstDate+"' AND '"+secondDate+"' AND token='"+logToken+"'";}
        if (message==null && firstDate==null && secondDate==null) {query = "SELECT * FROM LOGS WHERE logType='"+logType+"' AND token='"+logToken+"'";}
        if (logType==null && firstDate==null && secondDate==null) {query = "SELECT * FROM LOGS WHERE message LIKE '%"+message+"%' AND token='"+logToken+"'";}
        if (message!=null && logType!=null && firstDate!=null && secondDate!=null) {query = "SELECT * FROM LOGS WHERE [message] like '%"+message+"%' AND logType='"+logType+"' AND dateOfLog BETWEEN '"+firstDate+"' AND '"+secondDate+"'AND token='"+logToken+"'";}
        if (message==null && logType==null && firstDate==null && secondDate==null) {query = "SELECT * FROM LOGS WHERE token='"+logToken+"'";}

        return jdbcTemplate.query(
                query,
                BeanPropertyRowMapper.newInstance(Log.class)
        );
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean checkToken(Token token) {
        String sql="select username as username, email as email, Id as uuid, password as password from Clients";
        List<Client> listOfClients=jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Client.class));
        for (var x:listOfClients){
            if (x.getUuid().toString().equalsIgnoreCase(token.getToken())){
                return true;
            }
        }

        return false;
    }

    @Override
    public void createLog(Log log, Token token) {
        log.setClientId(token.getToken());
        log.setDate(LocalDate.now());
        String sql="INSERT INTO Log (message,logType,date,clientId) VALUES ('"
                + log.getMessage() + "','" + log.getLogType() + "','" + log.getDate().toString() + "','" + log.getClientId()  + "')";

        jdbcTemplate.execute(sql);

    }
}
