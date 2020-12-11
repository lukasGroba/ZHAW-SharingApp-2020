package ch.zhaw.mas.sharingApp.clientSite.persistence;

import ch.zhaw.mas.sharingApp.clientSite.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class Userpersistence {

    private RestTemplateOwn restTemplate = new RestTemplateOwn();


    // TODO: 11.12.2020 from application properties!! 
    private String serverUrl = "http://localhost:8080/";

//    public User getUser(int id){
//        RestTemplate restTemplate = new RestTemplate();
//        String fooResourceUrl
//                = serverUrl + "user" + id;
//        ResponseEntity<String> response
//                = restTemplate.getForEntity(fooResourceUrl, String.class);
//        return new User();
//    }

    public User getUser(int id){
        final String url = serverUrl + "books/test";
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }

}
