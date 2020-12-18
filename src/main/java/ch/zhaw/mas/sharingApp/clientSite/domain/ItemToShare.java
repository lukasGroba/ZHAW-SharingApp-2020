package ch.zhaw.mas.sharingApp.clientSite.domain;


import ch.zhaw.mas.sharingApp.clientSite.persistence.ItemPersistence;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private ItemPersistence itemPersistence = new ItemPersistence();

    public void saveNewItem(){
        itemPersistence.saveNewItem(this);
    }

    public void deleteItem(int id){
        itemPersistence.deleteItem(id);
    }

    public void updateItem(){
        itemPersistence.updateItem(this);
    }

}
