package ch.zhaw.mas.sharingApp.clientSite.presentation;

import ch.zhaw.mas.sharingApp.clientSite.domain.*;
import javafx.beans.property.*;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/************************************************************************************************************
 * ItemFxView class
 *
 * This is the ItemFxView class. This class is for the JavaFX frontend. With this class everything of the
 * item can be made visible on the stages.
 *
 * @author  Lukas Grossenbacher
 * @since   2020.12.08
 * @version 0.1
 *
 ************************************************************************************************************/

public class ItemFxView {

    private final StringProperty itemName;
    private final IntegerProperty itemID;
    private final StringProperty itemOwner;
    private final StringProperty itemOwnerMail;
    private final ObjectProperty<LocalDate> itemCreateDate;
    private final BooleanProperty itemAvailable;
    private final DoubleProperty itemRating;
    private final StringProperty itemDescription;
    private final ObjectProperty<LocalDate> itemLentFrom;
    private final ObjectProperty<LocalDate> itemLentTill;

    /************************************************************************************************************
     * void ItemFxView() constructor
     *
     * Constructor of the class ItemFxView with initializing of the object.
     *
     * author  Lukas Grossenbacher
     * @since 2020.12.08
     * version 0.1
     * @param itemToShare
     * return
     *
     ************************************************************************************************************/

    public ItemFxView(ItemToShare itemToShare){
        this.itemName = new SimpleStringProperty(itemToShare.getName());
        this.itemID = new SimpleIntegerProperty(itemToShare.getId());
        this.itemOwner = new SimpleStringProperty(itemToShare.getOwner().getUsername());
        this.itemOwnerMail = new SimpleStringProperty(itemToShare.getOwner().getMail());
        this.itemCreateDate = new SimpleObjectProperty<LocalDate>(itemToShare.getDateCreated());
        this.itemAvailable = new SimpleBooleanProperty(itemToShare.isLent());
        this.itemRating = new SimpleDoubleProperty(itemToShare.getRating());
        this.itemDescription = new SimpleStringProperty(itemToShare.getDescription());
        this.itemLentFrom = new SimpleObjectProperty<LocalDate>(itemToShare.getLentFrom());
        this.itemLentTill = new SimpleObjectProperty<LocalDate>(itemToShare.getLentTill());
    }

    /************************************************************************************************************
     * void getter and setter() Methods
     *
     * this methods will be needed to get and set property information
     *
     * author  Lukas Grossenbacher
     * @since 2020.12.08
     * version 0.1
     * param
     * return
     *
     ************************************************************************************************************/
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

    public String getItemOwnerMail(){return this.itemOwnerMail.get();}
    public StringProperty itemOwnerMailProperty(){
        return this.itemOwnerMail;
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

    /************************************************************************************************************
     * ItemToShare convertItemFxViewToItemToShare(ItemFxView) Methods
     *
     * this methods will convert an ItemFxView to an ItemToShare
     *
     * author  Lukas Grossenbacher
     * @since 2020.12.21
     * version 0.1
     * param
     * return
     *
     ************************************************************************************************************/
    public ItemToShare convertItemFxViewToItemToShare(ItemFxView itemFxView, User user){
        ItemToShare itemToShare = new ItemToShare();

        itemToShare.setId(itemFxView.getItemID());
        itemToShare.setLent(itemFxView.getItemAvailable());
        itemToShare.setRating(itemFxView.getItemRating());
        itemToShare.setOwner(user);
        itemToShare.setDescription(itemFxView.getItemDescription());
        itemToShare.setDateCreated(itemFxView.getItemCreateDate());
        itemToShare.setLentFrom(itemFxView.getItemLentFrom());
        itemToShare.setLentTill(itemFxView.getItemLentTill());

        return itemToShare;
    }

}

