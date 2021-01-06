package ch.zhaw.mas.sharingApp.clientSite.presentation;

import ch.zhaw.mas.sharingApp.clientSite.SharingApp;
import ch.zhaw.mas.sharingApp.clientSite.domain.DateUtil;
import ch.zhaw.mas.sharingApp.clientSite.domain.ItemToShare;
import ch.zhaw.mas.sharingApp.clientSite.domain.services.ItemService;
import ch.zhaw.mas.sharingApp.clientSite.persistence.generic.BackendError;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.time.LocalDate;


/************************************************************************************************************
 * ItemListOverviewController class
 *
 * This is the ItemListOverviewController and manages all actions with the ItemList.
 *
 * @author  Lukas Grossenbacher
 * @since   2020.12.21
 * @version 0.3
 *
 ************************************************************************************************************/
public class ItemListOverviewController {

    @FXML
    private TableView<ItemFxView> itemTable;
    @FXML
    private TableColumn<ItemFxView, String> itemNameColumn;
    @FXML
    private TableColumn<ItemFxView, String> itemOwnerColumn;

    @FXML
    private Label itemNameLabel;
    @FXML
    private Label itemOwnerLabel;
    @FXML
    private Label itemOwnerMailLabel;
    @FXML
    private Label itemIDLabel;
    @FXML
    private Label itemCreateDate;
    @FXML
    private Label itemAvailableLabel;
    @FXML
    private Label itemRating;
    @FXML
    private Label itemDescription;
    @FXML
    private Label itemLentFrom;
    @FXML
    private Label itemLentTill;

    private Stage dialogStage;

    private ItemService itemService;

    /**
     * The constructor
     */
    public ItemListOverviewController() {
    }

    /************************************************************************************************************
     * void initialize() Method
     *
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     *
     * author  Lukas Grossenbacher
     * @since 2020.12.02
     * version 0.1
     * param
     * return
     *
     ************************************************************************************************************/
    @FXML
    private void initialize() {
        itemNameColumn.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        itemOwnerColumn.setCellValueFactory(cellData -> cellData.getValue().itemOwnerProperty());

        // Clear person details.
        showItemDetails(null);

        // Listen for selection changes and show the item details when changed.
        itemTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showItemDetails(newValue));
    }
    /************************************************************************************************************
     * void setItemService() Method
     *
     * Sets the ItemService() of this dialog to communicate with the server
     *
     * @author  Lukas Grossenbacher
     * @since   2020.12.21
     * version 0.1
     * @param   itemService
     * return
     *
     ************************************************************************************************************/
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    /************************************************************************************************************
     * void setSharingApp() Method
     *
     * Is called by the main application to give a reference back to itself.
     *
     * author  Lukas Grossenbacher
     * @since 2020.12.02
     * version 0.1
     * param   sharingApp
     * return
     *
     ************************************************************************************************************/
    // Reference to the main application
    private SharingApp sharingApp;

    public void setSharingApp(SharingApp sharingApp) {
        this.sharingApp = sharingApp;

        // Add observable list data to the table
        itemTable.setItems(sharingApp.getItemData());
    }

    /************************************************************************************************************
     * void showItemDetails() Method
     *
     * Is called by the main application and sets up the Item Details view.
     *
     * author  Lukas Grossenbacher
     * @since 2020.12.02
     * version 0.1
     * @param   item
     * return
     *
     ************************************************************************************************************/
    private void showItemDetails(ItemFxView item) {
        if (item != null) {
            itemNameLabel.setText(item.getItemName());
            itemOwnerLabel.setText(item.getItemOwner());
            itemOwnerMailLabel.setText(item.getItemOwnerMail());
            itemIDLabel.setText("" + item.getItemID());
            itemCreateDate.setText(DateUtil.format(item.getItemCreateDate()));
            itemAvailableLabel.setText(item.getItemAvailableString());
            itemRating.setText("" + item.getItemRating());
            itemDescription.setText(item.getItemDescription());
            itemLentFrom.setText(DateUtil.format(item.getItemLentFrom()));
            itemLentTill.setText(DateUtil.format(item.getItemLentTill()));
        } else {
            itemNameLabel.setText("");
            itemOwnerLabel.setText("");
            itemOwnerMailLabel.setText("");
            itemIDLabel.setText("");
            itemCreateDate.setText("");
            itemAvailableLabel.setText("");
            itemRating.setText("");
            itemDescription.setText("");
            itemLentFrom.setText("");
            itemLentTill.setText("");
        }
    }

    /************************************************************************************************************
     * void handleReloadList() Method
     *
     * This method will be called when the user clicks the "Reload List" button in GUI. This button will
     * reload the complete list from the server again.
     *
     * author  Lukas Grossenbacher
     * @since 2021.01.06
     * version 0.3
     * param
     * return
     *
     ************************************************************************************************************/
    @FXML
    private void handleReloadList(){
        try{
            sharingApp.loadCompleteListFromServer();    //Refresh the complete list in SharingAppApplication from server

        }catch(Exception exp){
            exp.printStackTrace();
            warningAlertMessage("Reload list from Server failed.", exp.getMessage() + "Please try again or startup server!");

        }
    }

    /************************************************************************************************************
     * void handleNew() Method
     *
     * This method will be called when the user clicks the new button in GUI. This method will open a dialog
     * to create a new item and add it the list on the server.
     *
     * author  Lukas Grossenbacher
     * @since 2021.01.05
     * version 0.3
     * param
     * return
     *
     ************************************************************************************************************/
    @FXML
    private void handleNew() {
        ItemToShare tempItemToShare = new ItemToShare();

        /*Create empty Item with user and create date*/
        tempItemToShare.setId(0);
        tempItemToShare.setLent(false);
        tempItemToShare.setRating(0.0);
        tempItemToShare.setDescription("");
        tempItemToShare.setOwner(sharingApp.getUserData());         //Set actual logged-in user from SharingApp
        tempItemToShare.setDateCreated(LocalDate.now());            //Set actual date
        tempItemToShare.setLentFrom(LocalDate.of(1999,01,01));
        tempItemToShare.setLentTill(LocalDate.of(1999,01,01));

        /*Create new ItemFxView and Show edit dialog*/
        ItemFxView tempItemFxView = new ItemFxView(tempItemToShare);
        boolean okClicked = sharingApp.showEditItemDialog(tempItemFxView);

        if (okClicked) {
            try{
                itemService.saveNewItem(tempItemFxView.convertItemFxViewToItemToShare(tempItemFxView, sharingApp.getUserData()));
                sharingApp.loadCompleteListFromServer();    //Refresh the complete list in SharingAppApplication from server

            }catch(BackendError exp){
                exp.printStackTrace();
                errorAlertMessage("Save new Item failed", exp.getMessage());
            }
        }
    }

    /************************************************************************************************************
     * void handleEdit() Method
     *
     * This method will be called when the user clicks the edit button in GUI. This method will open a dialog
     * to edit an item and store it in the list on the server.
     *
     * author  Lukas Grossenbacher
     * @since 2021.01.05
     * version 0.3
     * param
     * return
     *
     ************************************************************************************************************/
    @FXML
    private void handleEdit() {
        ItemFxView selectedItem = itemTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (sharingApp.getUserData().getMail().contentEquals(selectedItem.getItemOwnerMail())) {
                boolean okClicked = sharingApp.showEditItemDialog(selectedItem);
                if (okClicked) {

                    try {
                        /*todo fix GRL: Item will not be updated correctly on server*/
                        itemService.updateItem(selectedItem.convertItemFxViewToItemToShare(selectedItem, sharingApp.getUserData()));
                        sharingApp.loadCompleteListFromServer(); //Refresh the complete list in SharingAppApplication from server

                    } catch (Exception exp) {
                        exp.printStackTrace();
                        errorAlertMessage("Update item on server failed!",exp.getMessage() + "Please startup the Server for SharingAppApplication");
                    }
                }
            }else{
                errorAlertMessage("No modification allowed!","User is not owner of this Item. You can only edit your own items!");
            }

        } else {
            // Nothing selected.
            warningAlertMessage("No item Selected", "Please select an item in the table.");
        }

    }

    /************************************************************************************************************
     * void handleDelete() Method
     *
     * This method will be called when the user clicks the delete button in GUI. This method will delete an item
     * and remove it in the list on the server.
     *
     * author  Lukas Grossenbacher
     * @since 2021.01.05
     * version 0.3
     * param
     * return
     *
     ************************************************************************************************************/
    @FXML
    private void handleDelete() {
        int selectedIndex = itemTable.getSelectionModel().getSelectedIndex();
        ItemFxView itemFxView = itemTable.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            if (sharingApp.getUserData().getMail().contentEquals(itemFxView.getItemOwnerMail())) {
                try{
                    itemService.deleteItem(itemFxView.getItemID()); //Delete item on server
                    sharingApp.loadCompleteListFromServer(); //Refresh the complete list in SharingAppApplication from server

                }catch(Exception exp){
                    exp.printStackTrace();
                }
            }else{
                errorAlertMessage("No modification allowed!","User is not owner of this Item. You can only edit your own items!");
            }
        } else {
            // Nothing selected.
            warningAlertMessage("No item Selected", "Please select an item in the table.");
        }
    }
    /************************************************************************************************************
     * void errorAlertMessage(String setHeader, String errorMessage) Method
     *
     * This method creates an alert with given Message when login has failed.
     *
     * author  Lukas Grossenbacher
     * @since   2021.01.06
     * version 0.2
     * @param setHeader
     * @param errorMessage
     * return
     *
     ************************************************************************************************************/
    private void errorAlertMessage(String setHeader, String errorMessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Error");
        alert.setHeaderText(setHeader);
        alert.setContentText(errorMessage);

        alert.showAndWait();
    }

    /************************************************************************************************************
     * void warningAlertMessage(String errorMessage) Method
     *
     * This method creates an alert with given Message when login has failed.
     *
     * author  Lukas Grossenbacher
     * @since   2021.01.06
     * version 0.1
     * @param setHeader
     * @param errorMessage
     * return
     *
     ************************************************************************************************************/
    private void warningAlertMessage(String setHeader, String errorMessage){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(dialogStage);
        alert.setTitle("Warning");
        alert.setHeaderText(setHeader);
        alert.setContentText(errorMessage);

        alert.showAndWait();
    }
}
