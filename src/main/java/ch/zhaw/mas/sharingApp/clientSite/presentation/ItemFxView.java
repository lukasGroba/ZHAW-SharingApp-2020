package ch.zhaw.mas.sharingApp.clientSite.presentation;

import ch.zhaw.mas.sharingApp.clientSite.domain.ItemToShare;
import ch.zhaw.mas.sharingApp.clientSite.domain.LocalDateAdapter;
import javafx.beans.property.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

public class ItemFxView {

    private final StringProperty itemName;
    private final IntegerProperty itemID;
    private final StringProperty itemOwner;
    private final ObjectProperty<LocalDate> itemCreateDate;
    private final BooleanProperty itemAvailable;
    private final DoubleProperty itemRating;
    private final StringProperty itemDescription;
    private final ObjectProperty<LocalDate> itemLentFrom;
    private final ObjectProperty<LocalDate> itemLentTill;


    /**
     * Constructor for testing the item.
     */
//    public ItemFxView(String itemName, String itemOwner) {
//        this.itemName = new SimpleStringProperty(itemName);
//        this.itemID = new SimpleIntegerProperty(0);
//        this.itemOwner = new SimpleStringProperty(itemOwner);
//        this.itemAvailable = new SimpleBooleanProperty(true);
//    }

    /**
     * Constructor with Class ItemToShare.
     */
    public ItemFxView(ItemToShare itemToShare){
        this.itemName = new SimpleStringProperty(itemToShare.getName());
        this.itemID = new SimpleIntegerProperty(itemToShare.getId());
        this.itemOwner = new SimpleStringProperty(itemToShare.getOwner().getUsername());
        this.itemCreateDate = new SimpleObjectProperty<LocalDate>(itemToShare.getDateCreated());
        this.itemAvailable = new SimpleBooleanProperty(itemToShare.isLent());
        this.itemRating = new SimpleDoubleProperty(itemToShare.getRating());
        this.itemDescription = new SimpleStringProperty(itemToShare.getDescription());
        this.itemLentFrom = new SimpleObjectProperty<LocalDate>(itemToShare.getLentFrom());
        this.itemLentTill = new SimpleObjectProperty<LocalDate>(itemToShare.getLentTill());
    }

    public String getItemName(){return this.itemName.get();}
    public void setItemName(String name){this.itemName.set(name);}
    public StringProperty itemNameProperty(){
        return this.itemName;
    }

    public int getItemID(){return this.itemID.get();}

    public String getItemOwner(){return this.itemOwner.get();}
    public StringProperty itemOwnerProperty(){
        return this.itemOwner;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getItemCreateDate(){return this.itemCreateDate.get();}
    public void setItemCreateDate(LocalDate date){this.itemCreateDate.set(date);}
    public ObjectProperty<LocalDate> itemCreateDateProperty() {
        return itemCreateDate;
    }


    public boolean getItemAvailable(){return this.itemAvailable.get();}
    public void setIsItemAvailable(boolean isLent){this.itemAvailable.set(isLent);}
    public String getItemAvailableString(){
        if(getItemAvailable()){
            return "true";
        }
        return "false";
    }

    public Double getItemRating(){return this.itemRating.get();}
    public void setItemRating(Double rating){this.itemRating.set(rating);}
    public DoubleProperty getItemRatingProperty(){return this.itemRating;}

    public String getItemDescription(){return this.itemDescription.get();}
    public void setItemDescription(String description){this.itemDescription.set(description);}
    public StringProperty getItemDescriptionProperty(){return this.itemDescription;}


    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getItemLentFrom(){return this.itemLentFrom.get();}
    public void setItemLentFrom(LocalDate date){this.itemLentFrom.set(date);}
    public ObjectProperty<LocalDate> itemLentFromProperty() {
        return itemLentFrom;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getItemLentTill(){return this.itemLentTill.get();}
    public void setItemLentTill(LocalDate date){this.itemLentTill.set(date);}
    public ObjectProperty<LocalDate> itemLentTillProperty() {
        return itemLentTill;
    }

}
