package ch.zhaw.mas.sharingApp.clientSite.persistence;

import ch.zhaw.mas.sharingApp.clientSite.domain.ItemToShare;
import ch.zhaw.mas.sharingApp.clientSite.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        HttpEntity<String> response = requestBuilder.httpGetRequest(params, this.getUrl());
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(response.getBody(), User.class);
        return user;
    }


    public void saveNewUser(User user){
        RequestBuilder requestBuilder = new RequestBuilder();
        HttpEntity<String> response = requestBuilder.httpPostRequest(new HashMap<>(), this.getUrl(), user);
    }

    public User loginUser(String username, String password) {
        return new User();
    }

    public List<ItemToShare> getItemsOfUser(Long id) {
        return new ArrayList<>();
    }
}
