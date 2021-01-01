package ch.zhaw.mas.sharingApp.clientSite.domain;

import lombok.Data;

@Data
public class UserWithPassword extends User{
    private String password;

    public UserWithPassword(String username, String mail, String password) {
        super(username, mail);
        this.password = password;
    }
}
