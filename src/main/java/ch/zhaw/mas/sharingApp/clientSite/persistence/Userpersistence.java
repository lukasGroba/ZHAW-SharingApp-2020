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
public class Userpersistence extends Persistence{
    public Userpersistence() {
        super("books/test"); // TODO: 11.12.2020 anpassen (users anstatt books)
    }

    public User getUser(int id){
        User user = this.getRestTemplate().getForObject(this.getUrl(), User.class);
        return user;
    }


    public void saveNewUser(User user){

    }

    public User loginUser(String username, String password) {
        return new User();
    }
}
