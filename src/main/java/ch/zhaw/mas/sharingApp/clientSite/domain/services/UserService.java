package ch.zhaw.mas.sharingApp.clientSite.domain.services;

import ch.zhaw.mas.sharingApp.clientSite.domain.User;
import ch.zhaw.mas.sharingApp.clientSite.domain.UserWithPassword;
import ch.zhaw.mas.sharingApp.clientSite.persistence.Userpersistence;
import ch.zhaw.mas.sharingApp.clientSite.persistence.generic.BackendError;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for connecting the presentation and the persistence layer of the User and the UserWithPassword.
 *
 * @author Noemi Kälin
 */
@Service
public class UserService {
    private Userpersistence userpersistence = new Userpersistence();
    private User userLoggedIn;

    public UserService(User userLoggedIn) {
        this.userLoggedIn = userLoggedIn;
    }

    public String getUserLoggedInMail() {
        return userLoggedIn.getMail();
    }

    public void login(String userMail, String password) throws BackendError {
        UserWithPassword user = new UserWithPassword(null, userMail, password);
        userpersistence.loginUser(user);
    }


    public User getUserByMail(String mail) throws JsonProcessingException, BackendError {
        return userpersistence.getUserbyMail(mail, this.getUserLoggedInMail());
    }

    public void saveNewUser(UserWithPassword user) throws BackendError {
        userpersistence.saveNewUser(user, this.getUserLoggedInMail());
    }

    public List<User> getAllUsers() throws JsonProcessingException, BackendError {
        return userpersistence.getAllUsers(this.getUserLoggedInMail());
    }

    public void deleteUserByMail(String mail) throws BackendError {
        userpersistence.deleteUserByMail(mail, this.getUserLoggedInMail());
    }

//    public List<ItemToShare> getItemsOfUser(User user){
//        return userpersistence.getItemsOfUser(user.getMail());
//    }

}
