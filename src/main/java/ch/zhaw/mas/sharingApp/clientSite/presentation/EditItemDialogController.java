package ch.zhaw.mas.sharingApp.clientSite.presentation;

import ch.zhaw.mas.sharingApp.clientSite.domain.DateUtil;
import ch.zhaw.mas.sharingApp.clientSite.domain.ItemToShare;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private TextField itemDescriptionField;
    @FXML
    private TextField isItemLentField;
    @FXML
    private TextField itemRatingField;
    @FXML
    private TextField itemLentFrom;
    @FXML
    private TextField itemLentTill;


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
        itemLentFrom.setText(DateUtil.format(item.getItemLentFrom()));
        itemLentTill.setText(DateUtil.format(item.getItemLentTill()));

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
        okClicked = true;
        dialogStage.close();
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
    private boolean isInputValid() {
        String errorMessage = "";

//        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
//            errorMessage += "No valid first name!\n";
//        }
//        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
//            errorMessage += "No valid last name!\n";
//        }
//        if (streetField.getText() == null || streetField.getText().length() == 0) {
//            errorMessage += "No valid street!\n";
//        }
//
//        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
//            errorMessage += "No valid postal code!\n";
//        } else {
//            // try to parse the postal code into an int.
//            try {
//                Integer.parseInt(postalCodeField.getText());
//            } catch (NumberFormatException e) {
//                errorMessage += "No valid postal code (must be an integer)!\n";
//            }
//        }
//
//        if (cityField.getText() == null || cityField.getText().length() == 0) {
//            errorMessage += "No valid city!\n";
//        }
//
//        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
//            errorMessage += "No valid birthday!\n";
//        } else {
//            if (!DateUtil.validDate(birthdayField.getText())) {
//                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
//            }
//        }

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

