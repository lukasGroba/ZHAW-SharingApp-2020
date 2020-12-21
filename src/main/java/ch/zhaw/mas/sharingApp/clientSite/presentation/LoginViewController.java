package ch.zhaw.mas.sharingApp.clientSite.presentation;
import ch.zhaw.mas.sharingApp.clientSite.SharingApp;
import ch.zhaw.mas.sharingApp.clientSite.domain.User;
import ch.zhaw.mas.sharingApp.clientSite.domain.services.UserService;
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
    private TextField userMail;
    @FXML
    private PasswordField userPassword;


    private Stage dialogStage;
    private boolean loginValid = false;
    private UserService userService;

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
     * @since   2020.12.19
     * version 0.2
     * param
     * return
     *
     ************************************************************************************************************/
    @FXML
    private void handleLogin() {
        if (isInputValid()) {
            /******************Just for Testing**********************/
            User user = new User();
            user.setUsername("Brian Muster");
            user.setMail(userMail.getText());
            /********************************************************/

            //Todo GRL: Uncommnet userService.login for real application to verify user
            //User user = userService.login(userMail.getText(), userPassword.getText()); /*Request to server*/
            if((user.getUsername() != null) && (user.getMail() != null)) {
                /*Set valid user into sharingApp*/
                sharingApp.setUserData(user);

                loginValid = true;
                dialogStage.close();

            }else{
                errorAlertMessage("Login was not successful! Try another 'User Mail' or other 'Password'!");
                /*Empty the textFields*/
                userMail.clear();
                userPassword.clear();
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
     * void handleSignUp() Method
     *
     * This method will be called when the user clicks the SignUp button in GUI. This method creates a dialog
     * Stage that a new user can be created with an new login and stored to the server.
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.08
     * version 0.1
     * param
     * return
     *
     ************************************************************************************************************/
    @FXML
    private void handleSignUp(){
        System.out.println("SignUp button clicked");
        sharingApp.showSignUpUserDialog();
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

        if (userMail.getText() == null || userMail.getText().length() == 0) {
            errorMessage += "No name entered!\n";
        }
        if(!userMail.getText().contains("@")){
            errorMessage += "No valid Mail address! Mail should contains an @!\n";
        }
        if (userPassword.getText() == null || userPassword.getText().length() == 0) {
            errorMessage += "No password entered!\n";
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
