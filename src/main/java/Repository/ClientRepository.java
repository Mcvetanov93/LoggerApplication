package Repository;

import Model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class ClientRepository implements ClientInterface {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean checkForDuplicateUsername(Client client) {
        String sql = "SELECT username FROM Clients";
        List<Client> listOfUsernames = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Client.class));
        boolean condition = true;
        for (var user : listOfUsernames) {
            if (user.getEmail().equalsIgnoreCase(client.getusername())) {
                condition = false;
                break;
            }
        }

        return condition;
    }

    @Override
    public boolean checkEmailUsernameAndPassword(Client client) {
        boolean condition = true;
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern patter = Pattern.compile(regex);
        Matcher matcher = patter.matcher(client.getEmail());
        //Checking if email is valid
        if (!matcher.matches()) {
            condition = false;
        }
        //Checking if username is longer than 3 characters
        if (client.getusername().length() < 3) {
            condition = false;
        }

        String pass = "^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]+$";
        Pattern patter2 = Pattern.compile(pass);
        matcher = patter2.matcher(client.getPassword());
        //Checking if password is valid
        if (!matcher.matches()) {
            condition = false;
        }
        return condition;
    }

    @Override
    public void registerClient(Client client) {

        String sql = "Insert into Clients (username,email,Id) values ('"
                + client.getusername() + "','" + client.getEmail() + "','" + client.getUuid() + "')";
        jdbcTemplate.execute(sql);

    }

    @Override
    public String clientLogIn(String email, String password) {
        return "";
    }

    @Override
    public List<Client> getallclients() {
        String sql = "select * from Clients";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Client.class));
    }
}
