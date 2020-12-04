package ch.zhaw.mas.sharingApp.clientSite.domain;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ItemToShare {
    private String Name;
    private LocalDate dateCreated;
    private String beschreibung;
    private boolean ausgeliehen;
    private Double bewertung;
//    private User owner;
//    private whatever picture;
//    private Location location; --> eher in owner?

}
