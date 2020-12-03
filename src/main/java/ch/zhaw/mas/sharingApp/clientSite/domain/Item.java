package ch.zhaw.mas.sharingApp.clientSite.domain;

import javafx.beans.property.*;

public class Item {

    private final StringProperty itemName;
    private final IntegerProperty itemID;
    private final StringProperty itemOwner;
    private final BooleanProperty itemAvailable;


    /**
     * Default constructor.
     */
    public Item() {
        this(null, null);
    }

    /**
     * Constructor with initial data.
     */
    public Item(String itemName, String itemOwner) {
        this.itemName = new SimpleStringProperty(itemName);
        this.itemID = new SimpleIntegerProperty(0);
        this.itemOwner = new SimpleStringProperty(itemOwner);
        this.itemAvailable = new SimpleBooleanProperty(true);
    }

    public String getItemName(){return this.itemName.get();}
    public void setItemName(String itemName){
        this.itemName.set(itemName);
    }
    public StringProperty itemNameProperty(){
        return this.itemName;
    }

    public int getItemID(){return this.itemID.get();}
    public void setItemID(int itemID){
        this.itemID.set(itemID);
    }

    public String getItemOwner(){return this.itemOwner.get();}
    public void setItemOwner(String itemOwner){
        this.itemOwner.set(itemOwner);
    }
    public StringProperty itemOwnerProperty(){
        return this.itemOwner;
    }

    public boolean getItemAvailable(){return this.itemAvailable.get();}
    public void setItemAvailavle(boolean itemAvailavle){
        this.itemAvailable.set(itemAvailavle);
    }
    public String getItemAvailableString(){
        if(getItemAvailable()){
            return "true";
        }
        return "false";
    }

}
