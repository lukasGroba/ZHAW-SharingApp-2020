package ch.zhaw.mas.sharingApp.clientSite.presentation;


import ch.zhaw.mas.sharingApp.clientSite.SharingApp;
import ch.zhaw.mas.sharingApp.clientSite.domain.User;
import ch.zhaw.mas.sharingApp.clientSite.domain.UserWithPassword;
import ch.zhaw.mas.sharingApp.clientSite.domain.services.UserService;
import ch.zhaw.mas.sharingApp.clientSite.persistence.generic.BackendError;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/************************************************************************************************************
 * SignUpUserDialogController class
 *
 * This is the SignUpUserDialogController and manages all buttons and actions to create a new user to the
 * SharingApp.
 *
 * @author  Lukas Grossenbacher
 * @since   2020.12.08
 * @version 0.1
 *
 ************************************************************************************************************/
public class SignUpUserDialogController {

    @FXML
    private TextField userNameField;
    @FXML
    private TextField userMailField;
    @FXML
    private PasswordField userPasswordField;
    @FXML
    private PasswordField userPasswordValidationField;


    private Stage dialogStage;
    private UserService userService;

    /************************************************************************************************************
     * void initialize() Method
     *
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.08
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
     * @since   2020.12.08
     * version  0.1
     * @param   dialogStage
     * return
     *
     ************************************************************************************************************/
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /************************************************************************************************************
     * void setUserService() Method
     *
     * Sets the UserService() of this dialog to communicate with the server
     *
     * @author  Lukas Grossenbacher
     * @since   2020.12.19
     * version 0.1
     * @param   userService
     * return
     *
     ************************************************************************************************************/
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /************************************************************************************************************
     * void setSharingApp() Method
     *
     * Is called by the main application to give a reference back to itself.
     *
     * author  Lukas Grossenbacher
     * @since  2020.12.08
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
     * void handleOk() Method
     *
     * This method will be called when the user clicks the ok button in GUI. This method creates the new user and
     * send it to the server.
     *
     * author  Lukas Grossenbacher
     * @since   2021.01.05
     * version 0.3
     * param
     * return
     *
     ************************************************************************************************************/
    @FXML
    private void handleOk(){
        //dialogStage.close();
        if (isInputValid()) {
            /*todo GRL: Uncomment try function for real application*/
            try {
                    UserWithPassword userWithPassword = new UserWithPassword(userNameField.getText(), userMailField.getText(), userPasswordValidationField.getText());
                    userService.saveNewUser(userWithPassword);
                    dialogStage.close();
            }catch (BackendError exp) {
                errorAlertMessage(exp.getMessage());
                exp.printStackTrace();
            }

        }
    }

    /************************************************************************************************************
     * void handleCancel() Method
     *
     * This method will be called when the user clicks the cancel button in GUI. This method will close the
     * program end generate a System.exit(0)
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.19
     * version 0.1
     * param
     * return
     *
     ************************************************************************************************************/
    @FXML
    private void handleCancel() {
        /*Empty the complete content of all fields*/
        userNameField.clear();
        userMailField.clear();
        userPasswordField.clear();
        userPasswordValidationField.clear();

        dialogStage.close();
    }

    /************************************************************************************************************
     * boolean isInputValid() Method
     *
     * This method validates the user input in the text fields of the GUI. It will be checked if it is
     * empty or null. Also it checks if the two passwords are identical.
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.08
     * version 0.1
     * param
     * return true if the input is valid / false if the input is not valid
     *
     ************************************************************************************************************/
    private boolean isInputValid() {
        String errorMessage = "";


        if (userNameField.getText() == null || userNameField.getText().length() == 0) {
            errorMessage += "No name entered!\n";
        }
        if (userMailField.getText() == null || userMailField.getText().length() == 0) {
            errorMessage += "No Mail entered!\n";
        }
        if(!userMailField.getText().contains("@")){
            errorMessage += "No valid Mail address! Mail should contains an @!\n";
        }
        if (userPasswordField.getText() == null || userPasswordField.getText().length() == 0) {
            errorMessage += "No password entered!\n";
        }
        if (userPasswordValidationField.getText() == null || userPasswordValidationField.getText().length() == 0) {
            errorMessage += "No password entered!\n";
        }
        if (!userPasswordField.getText().contains(userPasswordValidationField.getText())) {
            errorMessage += "Passwords are not same! Please enter same passwords!\n";
           }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            errorAlertMessage(errorMessage);
            return false;
        }
    }

    /************************************************************************************************************
     * void errorAlertMessage(String errorMessage) Method
     *
     * This method creates an alert with given Message when login has failed.
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.19
     * version 0.1
     * @param errorMessage
     * return
     *
     ************************************************************************************************************/
    private void errorAlertMessage(String errorMessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Invalid Fields");
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText(errorMessage);

        alert.showAndWait();
    }
}
