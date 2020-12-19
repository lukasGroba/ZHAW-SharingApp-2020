package ch.zhaw.mas.sharingApp.clientSite.domain.services;

import ch.zhaw.mas.sharingApp.clientSite.domain.ItemToShare;
import ch.zhaw.mas.sharingApp.clientSite.domain.User;
import ch.zhaw.mas.sharingApp.clientSite.persistence.Userpersistence;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private Userpersistence userpersistence = new Userpersistence();


    public User login(String username, String password){
        return userpersistence.loginUser(username, password);
    }


    public User getUserByMail(String mail) throws JsonProcessingException {
        return userpersistence.getUser(mail);
    }

    public void saveNewUser(User user){
        userpersistence.saveNewUser(user);
    }

    public List<ItemToShare> getItemsOfUser(User user){
        return userpersistence.getItemsOfUser(user.getMail());
    }

}