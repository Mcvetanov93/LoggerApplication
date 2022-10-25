package Repository;

import Model.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientInterface {
    void registerClient(Client client);
    String clientLogIn (String email,String password);
    boolean checkForDuplicateUsername(Client client);
    List<Client>getallclients();
    boolean checkEmailUsernameAndPassword(Client client);
}
