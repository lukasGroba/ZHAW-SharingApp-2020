package ch.zhaw.mas.sharingApp.clientSite.domain;

import lombok.Data;

@Data
public class User {
    private String username;
    private String mail; // evtl. = username?
    private String password;
//    private Location location;
// Items? Picture? Bewertung?

}
