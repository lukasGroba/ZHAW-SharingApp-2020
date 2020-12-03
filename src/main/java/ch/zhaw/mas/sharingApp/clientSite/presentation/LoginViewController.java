package ch.zhaw.mas.sharingApp.clientSite.presentation;
import ch.zhaw.mas.SharingApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/************************************************************************************************************
 * LoginViewController class
 *
 * This is the LoginViewController and manages all buttons and actions with the LogingView GUI
 *
 * @author  Lukas Grossenbacher
 * @since   2020.12.02
 * @version 0.1
 *
 ************************************************************************************************************/
public class LoginViewController {

    @FXML
    private TextField userName;
    @FXML
    private PasswordField userPassword;


    private Stage dialogStage;
    private boolean loginValid = false;

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
    }

    /************************************************************************************************************
     * void setDialogStage() Method
     *
     * Sets the stage of this dialog.
     *
     * @author  Lukas Grossenbacher
     * @since   2020.12.02
     * version 0.1
     * @param   dialogStage
     * return
     *
     ************************************************************************************************************/
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
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
    }


    /************************************************************************************************************
     * void handleLogin() Method
     *
     * This method will be called when the user clicks the login button in GUI. This method will also set
     * loginValid to true if the login was successful.
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.02
     * version 0.1
     * param
     * return
     *
     ************************************************************************************************************/
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

    /************************************************************************************************************
     * void handleCancel() Method
     *
     * This method will be called when the user clicks the cancel button in GUI. This method will close the
     * program end generate a System.exit(0)
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.02
     * version 0.1
     * param
     * return
     *
     ************************************************************************************************************/
    @FXML
    private void handleCancel() {
        System.exit(0);
    }

    /************************************************************************************************************
     * boolean isInputValid() Method
     *
     * This method validates the user input in the text fields of the GUI. It will be checked if it is
     * empty or null.
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.02
     * version 0.1
     * param
     * return true if the input is valid / false if the input is not valid
     *
     ************************************************************************************************************/
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

    /************************************************************************************************************
     * boolean isLoginValid() Method
     *
     * This method returns the actual state of the loginValid variable.
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.02
     * version 0.1
     * param
     * return  loginValid
     *
     ************************************************************************************************************/
    public boolean isLoginValid(){
        return loginValid;
    }
}
