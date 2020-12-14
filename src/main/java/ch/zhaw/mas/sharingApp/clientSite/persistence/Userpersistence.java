package ch.zhaw.mas.sharingApp.clientSite.persistence;

import ch.zhaw.mas.sharingApp.clientSite.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class Userpersistence extends Persistence{
    public Userpersistence() {
        super("books/test"); // TODO: 11.12.2020 anpassen (users anstatt books)
    }



    // TODO: 11.12.2020 sollte static sein?
    public User getUser(Integer id) throws JsonProcessingException {
        Map<String, String> params = new HashMap<>();
        params.put("id", id.toString());
        RequestBuilder requestBuilder = new RequestBuilder();
        HttpEntity<String> response = requestBuilder.getHttpRequest(params, this.getUrl(), HttpMethod.GET);
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(response.getBody(), User.class);


//        UriComponentsBuilder componentBuilder = this.getComponentsBuilder().queryParam("id", id);
//        User user = this.getRestTemplate().exchange(componentBuilder.toUriString()).getForObject(this.getUrl() + "?id=2", User.class);
//        User user = this.getRestTemplate().getForObject(this.getUrl() + "?id=2", User.class);
        return user;
    }


    public void saveNewUser(User user){

    }

    public User loginUser(String username, String password) {
        return new User();
    }
}
