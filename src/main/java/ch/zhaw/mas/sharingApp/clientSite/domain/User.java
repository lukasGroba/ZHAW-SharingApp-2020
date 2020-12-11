package ch.zhaw.mas.sharingApp.clientSite.domain;

import ch.zhaw.mas.sharingApp.clientSite.persistence.Userpersistence;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {

    Userpersistence userpersistence = new Userpersistence();

//    private String username;
//    private String mail; // evtl. = username?
//    private String password;
//    private Location location;
// Items? Picture? Bewertung?

    private Long id;
    private String firstName;
    private String lastName;

//    public void saveNewUser(){}
    public User getUserServer(){
        return userpersistence.getUser(0);
    }
//


    public void saveNewUser(){
        userpersistence.saveNewUser(this);
    }

    public User login(String username, String password){
        return userpersistence.loginUser(username, password);
    }


}
