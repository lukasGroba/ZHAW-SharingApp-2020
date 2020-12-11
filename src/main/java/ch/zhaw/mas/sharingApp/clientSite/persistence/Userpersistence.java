package ch.zhaw.mas.sharingApp.clientSite.persistence;

import ch.zhaw.mas.sharingApp.clientSite.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Userpersistence {

    @Value("${SERVER.URL}")
    private String serverUrl;

    public User getUser(int id){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = serverUrl + "user" + id;
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
        return new User();
    }


}
