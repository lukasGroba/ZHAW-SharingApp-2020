package ch.zhaw.mas.sharingApp.clientSite.domain;

import lombok.Data;

@Data
public class User {
    private String username;
    private String mail; // evtl. = username?
//    private Location location;
// Items? Picture? Bewertung?

    public String getMail() {
        System.out.println("own used");
        return mail;
    }
}
