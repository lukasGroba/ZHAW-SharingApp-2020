package ch.zhaw.mas.sharingApp.clientSite.presentation;
import ch.zhaw.mas.SharingApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginViewController {

    @FXML
    private TextField userName;
    @FXML
    private TextField userPassword;


    private Stage dialogStage;
    private boolean LoginClicked = false;
    private boolean cancleClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Called when the user clicks Login.
     */
    @FXML
    private void handleLogin() {
        if (isInputValid()) {
            /*todo: Here should be action when button Login is clicked*/
            /*todo: check here correct username*/
            /*todo: check here correct password*/
            LoginClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks Login.
     */
    @FXML
    private void handleCancel() {
        if (isInputValid()) {
            /*todo: Here should be action when button Cancle is clicked*/
            /*todo: delete userName*/
            /*todo: deletePasswort*/
            cancleClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (userName.getText() == null || userName.getText().length() == 0) {
            errorMessage += "No name entered!\n";
        }
        if (userPassword.getText() == null || userPassword.getText().length() == 0) {
            errorMessage += "No password entered!\n";
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
