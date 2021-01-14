package ch.zhaw.mas.sharingApp.clientSite.domain;

import lombok.Data;

/**
 * Model for a User containing the password of the User. Used for initial saving of a new user to the server and for Login (Persistence).
 *
 * @author Noemi Kälin
 */
@Data
public class UserWithPassword extends User{
    private String password;

    public UserWithPassword(String username, String mail, String password) {
        super(username, mail);
        this.password = password;
    }
}
