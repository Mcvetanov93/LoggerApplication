package com.example.Project.Repository;

import com.example.Project.Model.Client;
import com.example.Project.Model.Log;
import com.example.Project.Model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public class LogRepository implements LogInterface{
    @Override
    public List<Log> getLogs(String message, String logType, String firstDate, String secondDate,String token) {
        String sql="SELECT * FROM Log WHERE logType='"+logType+"' AND message LIKE '%"+message+"%' AND date BETWEEN '"+firstDate+"' AND '"+secondDate+"' AND clientId='"+token+"';";
        if(message==null){
            sql="SELECT * FROM Log WHERE logType='"+logType+"' AND date BETWEEN '"+firstDate+"' AND '"+secondDate+"' AND clientId='"+token+"';";
        }
        if(logType==null){
            sql="SELECT * FROM Log WHERE message LIKE '%"+message+"%' AND date BETWEEN '"+firstDate+"' AND '"+secondDate+"' AND clientId='"+token+"';";
        }
        if (firstDate==null&&secondDate==null){
            sql="SELECT * FROM Log WHERE message LIKE '%"+message+"%' AND logType='"+logType+"' AND clientId='"+token+"';";
        }
        if (message==null&&logType==null){
            sql="SELECT * FROM Log WHERE date BETWEEN '"+firstDate+"' AND '"+secondDate+"' AND clientId='"+token+"';";
        }
        if (message==null&&firstDate==null&&secondDate==null){
            sql="SELECT * FROM Log WHERE  logType='"+logType+"' AND clientId='"+token+"';";
        }
        if (logType==null&&firstDate==null&&secondDate==null){
            sql="SELECT * FROM Log WHERE message LIKE '%"+message+"%' AND clientId='"+token+"';";
        }
        if (logType==null&&firstDate==null&&secondDate==null&&message==null){
            sql="SELECT * FROM Log WHERE clientId='"+token+"';";
        }

        return jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Log.class));
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
