package ch.zhaw.mas.sharingApp.clientSite.presentation;
import ch.zhaw.mas.SharingApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginViewController {

    @FXML
    private TextField userName;
    @FXML
    private PasswordField userPassword;


    private Stage dialogStage;
    private boolean loginValid = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    // Reference to the main application
    private SharingApp sharingApp;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param sharingApp
     */
    public void setSharingApp(SharingApp sharingApp) {
        this.sharingApp = sharingApp;
    }

    /**
     * Called when the user clicks Login.
     */
    @FXML
    private void handleLogin() {
        if (isInputValid()) {
            /*todo: Here should be action when button Login is clicked*/
            /*todo: Load correct user data from server and check if it is valid*/
            /*todo: check here correct username*/
            /*todo: check here correct password*/
            loginValid = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks Login.
     */
    @FXML
    private void handleCancel() {
        System.exit(0);
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
    public boolean isLoginValid(){
        return loginValid;
    }
}
