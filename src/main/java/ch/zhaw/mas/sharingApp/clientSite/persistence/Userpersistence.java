package ch.zhaw.mas.sharingApp.clientSite.persistence;

import ch.zhaw.mas.sharingApp.clientSite.domain.ItemToShare;
import ch.zhaw.mas.sharingApp.clientSite.domain.User;
import ch.zhaw.mas.sharingApp.clientSite.domain.UserWithPassword;
import ch.zhaw.mas.sharingApp.clientSite.persistence.generic.BackendError;
import ch.zhaw.mas.sharingApp.clientSite.persistence.generic.Persistence;
import ch.zhaw.mas.sharingApp.clientSite.persistence.generic.RequestBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is for persisting Users to/from the server.
 *
 * @author Noemi Kälin
 */
@Service
public class Userpersistence extends Persistence {
    public Userpersistence() {
        super("users");
    }

    // TODO: 31.12.2020 not working (json processing)
    public List<User> getAllUsers() throws JsonProcessingException, BackendError {
        Map<String, String> params = new HashMap<>();
        RequestBuilder requestBuilder = new RequestBuilder();
        HttpEntity<String> response = requestBuilder.httpGetRequest(params, this.getUrl());
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> user = objectMapper.readValue(response.getBody(), new TypeReference<List<User>>(){});
        return user;
    }

    // TODO: 11.12.2020 sollte static sein?
    public User getUserbyMail(String mail) throws JsonProcessingException, BackendError {
        Map<String, String> params = new HashMap<>();
        params.put("user", mail);
        RequestBuilder requestBuilder = new RequestBuilder();
        HttpEntity<String> response = requestBuilder.httpGetRequest(params, this.getUrl() + "/user");
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(response.getBody(), User.class);
        return user;
    }


    public void saveNewUser(UserWithPassword user) throws BackendError {
        RequestBuilder requestBuilder = new RequestBuilder();
        HttpEntity<String> response = requestBuilder.httpPostRequest(new HashMap<>(), this.getUrl(), user);
    }

    public void loginUser(UserWithPassword user) throws BackendError {
        RequestBuilder requestBuilder = new RequestBuilder();
        HttpEntity<String> response = requestBuilder.httpPostRequest(new HashMap<>(), this.getUrl() + "/login", user);
    }

    public void deleteUserByMail(String mail) throws BackendError {
        Map<String, String> params = new HashMap<>();
        params.put("user", mail);
        RequestBuilder requestBuilder = new RequestBuilder();
        HttpEntity<String> response = requestBuilder.httpDeleteRequest(params, this.getUrl());
    }

//    public List<ItemToShare> getItemsOfUser(String username) {
//        return new ArrayList<>();
//    }
}
