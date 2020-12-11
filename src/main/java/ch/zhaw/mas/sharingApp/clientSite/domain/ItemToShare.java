package ch.zhaw.mas.sharingApp.clientSite.domain;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
public class ItemToShare {
    private int id;
    private String name;
    private LocalDate dateCreated;
    private String description;
    private boolean isLent;
    private Double rating;
    private User owner;
    private LocalDate lentFrom;
    private LocalDate lentTill;

//    private whatever picture;
//    private Location location; --> eher in owner?

}
