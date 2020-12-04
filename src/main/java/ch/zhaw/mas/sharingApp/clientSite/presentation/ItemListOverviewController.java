package ch.zhaw.mas.sharingApp.clientSite.presentation;

import ch.zhaw.mas.sharingApp.clientSite.SharingApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


/************************************************************************************************************
 * ItemListOverviewController class
 *
 * This is the ItemListOverviewController and manages all actions with the ItemList.
 *
 * @author  Lukas Grossenbacher
 * @since   2020.12.03
 * @version 0.1
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
    private Label itemIDLabel;
    @FXML
    private Label itemAvailableLabel;

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
     * @since   2020.12.02
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
     * void setSharingApp() Method
     *
     * Is called by the main application to give a reference back to itself.
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.02
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

    private void showItemDetails(ItemFxView item) {
        if (item != null) {
            itemNameLabel.setText(item.getItemName());
            itemOwnerLabel.setText(item.getItemOwner());
            itemIDLabel.setText("" + item.getItemID());
            itemAvailableLabel.setText(item.getItemAvailableString());
        }else{
            itemNameLabel.setText("");
            itemOwnerLabel.setText("");
            itemIDLabel.setText("");
            itemAvailableLabel.setText("");
        }
    }

}
