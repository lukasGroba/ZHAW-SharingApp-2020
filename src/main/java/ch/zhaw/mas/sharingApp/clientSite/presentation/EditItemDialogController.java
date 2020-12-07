package ch.zhaw.mas.sharingApp.clientSite.presentation;

import ch.zhaw.mas.sharingApp.clientSite.domain.DateUtil;
import ch.zhaw.mas.sharingApp.clientSite.domain.ItemToShare;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Date;

/************************************************************************************************************
 * EditItemDialogController class
 *
 * This is the EditItemDialogController and manages all actions to edit the Item Properties.
 *
 * @author  Lukas Grossenbacher
 * @since   2020.12.07
 * @version 0.1
 *
 ************************************************************************************************************/

public class EditItemDialogController {

    @FXML
    private TextField itemNameField;
    @FXML
    private TextField dateCreatedField;
    @FXML
    private TextArea itemDescriptionField;
    @FXML
    private TextField isItemLentField;
    @FXML
    private TextField itemRatingField;
    @FXML
    private TextField itemLentFromField;
    @FXML
    private TextField itemLentTillField;


    private Stage dialogStage;
    private ItemFxView item ;
    private boolean okClicked = false;

    /************************************************************************************************************
     * void initialize() Method
     *
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.07
     * version 0.1
     * param
     * return
     *
     ************************************************************************************************************/
    @FXML
    private void initialize() {
    }

    /************************************************************************************************************
     * void setDialogStage() Method
     *
     * Sets the stage of this dialog.
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.07
     * version 0.1
     * @param dialogStage
     * return
     *
     ************************************************************************************************************/
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

/************************************************************************************************************
 * void setItem() Method
 *
 * Sets the item to be edited in the dialog.
 *
 * author  Lukas Grossenbacher
 * @since   2020.12.07
 * version 0.1
 * @param item
 * return
 *
 ************************************************************************************************************/

    public void setItem(ItemFxView item) {
        this.item = item;

        itemNameField.setText(item.getItemName());
        dateCreatedField.setText(DateUtil.format(item.getItemCreateDate()));
        isItemLentField.setText(item.getItemAvailableString());
        itemRatingField.setText("" + item.getItemRating());
        itemDescriptionField.setText(item.getItemDescription());
        itemLentFromField.setText(DateUtil.format(item.getItemLentFrom()));
        itemLentTillField.setText(DateUtil.format(item.getItemLentTill()));

    }

/************************************************************************************************************
 * void handleOk() Method
 *
 * Called when the user clicks ok. And set all values to the Object.
 *
 * author  Lukas Grossenbacher
 * @since  2020.12.07
 * version 0.1
 * param
 * return
 *
 ************************************************************************************************************/
    @FXML
    private void handleOk() {
        System.out.println("Button Ok was clicked");

        if (isInputValid()) {
            item.setItemName(itemNameField.getText());
            item.setItemCreateDate(DateUtil.parse(dateCreatedField.getText()));
            item.setIsItemAvailable(Boolean.parseBoolean(isItemLentField.getText()));
            item.setItemRating(Double.parseDouble(itemRatingField.getText()));
            item.setItemDescription(itemDescriptionField.getText());
            item.setItemLentFrom(DateUtil.parse(itemLentFromField.getText()));
            item.setItemLentTill(DateUtil.parse(itemLentTillField.getText()));
        okClicked = true;
        dialogStage.close();
        }
    }

/************************************************************************************************************
 * void handleCancel() Method
 *
 * Called when the user clicks cancel.
 *
 * author  Lukas Grossenbacher
 * @since  2020.12.07
 * version 0.1
 * param
 * return
 *
 ************************************************************************************************************/
    @FXML
    private void handleCancel() {
        System.out.println("Button Cancel was clicked");
        dialogStage.close();
    }
/************************************************************************************************************
* void isOkClicked() Method
*
* Called when the user clicks ok and returns a true.
*
* author  Lukas Grossenbacher
* @since  2020.12.07
* version 0.1
* param
* return
*
************************************************************************************************************/
    public boolean isOkClicked() {
        return okClicked;
    }
 /************************************************************************************************************
 * void isInputValid() Method
 *
 * Validates the user input in the text fields.
 *
 * author  Lukas Grossenbacher
 * @since  2020.12.07
 * version 0.1
 * param
 * @return true if the input is valid
 *
 ************************************************************************************************************/
    private boolean isInputValid(){
        String errorMessage = "";

        if (itemNameField.getText() == null || itemNameField.getText().length() == 0) {
            errorMessage += "No valid item name!\n";
       }

        if (dateCreatedField.getText() == null || dateCreatedField.getText().length() == 0) {
            errorMessage += "No valid create date!\n";
        } else {
            if (!DateUtil.validDate(dateCreatedField.getText())) {
                errorMessage += "No valid create date. Use the format dd.mm.yyyy!\n";
            }
        }
        if (isItemLentField.getText() == null || isItemLentField.getText().length() == 0) {
            errorMessage += "No valid availability!\n";
        }
        if(isItemLentField.getText().equalsIgnoreCase("true") | isItemLentField.getText().equalsIgnoreCase("false") ){
            /*Do nothing if statement is correct*/
        }else{
            errorMessage += "Availability must be true or false!\n";
        }
        if (itemRatingField.getText() == null || itemRatingField.getText().length() == 0) {
            errorMessage += "No valid rating!\n";
        }
        if (Double.parseDouble(itemRatingField.getText()) > 5 || Double.parseDouble(itemRatingField.getText()) < 0) {
            errorMessage += "Rating must be between 0.0 and 5.0!\n";
        }

        if (itemDescriptionField.getText() == null || itemDescriptionField.getText().length() == 0) {
            errorMessage += "Please enter an description!\n";
        }

        if (itemLentFromField.getText() == null || itemLentFromField.getText().length() == 0) {
            errorMessage += "No valid lent from date!\n";
        } else {
            if (!DateUtil.validDate(itemLentFromField.getText())) {
                errorMessage += "No valid lent from. Use the format dd.mm.yyyy!\n";
            }
        }

        if (itemLentTillField.getText() == null || itemLentTillField.getText().length() == 0) {
            errorMessage += "No valid lent till date!\n";
        } else {
            if (!DateUtil.validDate(itemLentTillField.getText())) {
                errorMessage += "No valid lent till. Use the format dd.mm.yyyy!\n";
            }
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}

