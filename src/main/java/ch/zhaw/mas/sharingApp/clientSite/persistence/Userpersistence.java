package ch.zhaw.mas.sharingApp.clientSite.persistence;

import ch.zhaw.mas.sharingApp.clientSite.domain.User;
import ch.zhaw.mas.sharingApp.clientSite.domain.UserWithPassword;
import ch.zhaw.mas.sharingApp.clientSite.persistence.generic.BackendError;
import ch.zhaw.mas.sharingApp.clientSite.persistence.generic.Persistence;
import ch.zhaw.mas.sharingApp.clientSite.persistence.generic.RequestBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<User> getAllUsers() throws JsonProcessingException, BackendError {
        Map<String, String> params = new HashMap<>();
        RequestBuilder requestBuilder = new RequestBuilder();
        User[] user = (User[]) requestBuilder.httpGetRequest(params, this.getUrl(), User[].class);
        return Arrays.asList(user);
    }
//    public List<User> getAllUsers2(){
//        RestTemplateOwn restTemplate = new RestTemplateOwn();
//        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(this.getUrl(), User[].class);
//        User[] objects = responseEntity.getBody();
////        Class<Object[]> bla = User[].class;
//        return Arrays.asList(objects);
////        return objects;
////        MediaType contentType = responseEntity.getHeaders().getContentType();
////        HttpStatus statusCode = responseEntity.getStatusCode();
//    }


    public User getUserbyMail(String mail) throws JsonProcessingException, BackendError {
        Map<String, String> params = new HashMap<>();
        params.put("mail", mail);
        RequestBuilder requestBuilder = new RequestBuilder();
        User user = (User) requestBuilder.httpGetRequest(params, this.getUrl() + "/user", User.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        User user = objectMapper.readValue(response.getBody(), User.class);
        return user;
    }


    public void saveNewUser(UserWithPassword user) throws BackendError {
        RequestBuilder requestBuilder = new RequestBuilder();
        requestBuilder.httpPostRequest(new HashMap<>(), this.getUrl(), user);
    }

    public void loginUser(UserWithPassword user) throws BackendError {
        RequestBuilder requestBuilder = new RequestBuilder();
        requestBuilder.httpPostRequest(new HashMap<>(), this.getUrl() + "/login", user);
    }

    public void deleteUserByMail(String mail) throws BackendError {
        Map<String, String> params = new HashMap<>();
        params.put("mail", mail);
        RequestBuilder requestBuilder = new RequestBuilder();
        requestBuilder.httpDeleteRequest(params, this.getUrl());
    }

//    public List<ItemToShare> getItemsOfUser(String username) {
//        return new ArrayList<>();
//    }
}
