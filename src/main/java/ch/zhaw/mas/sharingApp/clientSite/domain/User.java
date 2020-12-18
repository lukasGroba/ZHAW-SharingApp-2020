package ch.zhaw.mas.sharingApp.clientSite.domain;

import ch.zhaw.mas.sharingApp.clientSite.persistence.Userpersistence;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class User {

//    private Userpersistence userpersistence = new Userpersistence();

    private String username;
    private String mail; // evtl. = username?
//    private String password;
//    private Location location;
// Items? Picture? Bewertung?

    private Long id;
    private String firstName;
    private String lastName;

//    public User(String username, String mail, Long id, String firstName, String lastName) {
//        this.username = username;
//        this.mail = mail;
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }

    //    public void saveNewUser(){}
    public User getUserById(int id) throws JsonProcessingException {
        Userpersistence userpersistence = new Userpersistence();
        User user = userpersistence.getUser(id);
        return user;
    }
//


    public void saveNewUser(){
        Userpersistence userpersistence = new Userpersistence();
        userpersistence.saveNewUser(this);
    }

    public User login(String username, String password){
        Userpersistence userpersistence = new Userpersistence();
        return userpersistence.loginUser(username, password);
    }


}
