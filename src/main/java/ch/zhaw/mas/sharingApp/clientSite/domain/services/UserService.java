package ch.zhaw.mas.sharingApp.clientSite.domain.services;

import ch.zhaw.mas.sharingApp.clientSite.domain.ItemToShare;
import ch.zhaw.mas.sharingApp.clientSite.domain.User;
import ch.zhaw.mas.sharingApp.clientSite.domain.UserWithPassword;
import ch.zhaw.mas.sharingApp.clientSite.persistence.Userpersistence;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Noemi Kälin
 */
@Service
public class UserService {
    private Userpersistence userpersistence = new Userpersistence();


    public void login(String userMail, String password){
        UserWithPassword user = new UserWithPassword(null, userMail, password);
        userpersistence.loginUser(user);
    }


    public User getUserByMail(String mail) throws JsonProcessingException {
        return userpersistence.getUserbyMail(mail);
    }

    public void saveNewUser(UserWithPassword user){
        userpersistence.saveNewUser(user);
    }

    public List<User> getAllUsers() throws JsonProcessingException {
        return userpersistence.getAllUsers();
    }

    public void deleteUserByMail(String mail){
        userpersistence.deleteUserByMail(mail);
    }

//    public List<ItemToShare> getItemsOfUser(User user){
//        return userpersistence.getItemsOfUser(user.getMail());
//    }

}
