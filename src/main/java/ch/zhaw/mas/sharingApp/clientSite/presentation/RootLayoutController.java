package ch.zhaw.mas.sharingApp.clientSite.presentation;
import ch.zhaw.mas.sharingApp.clientSite.SharingApp;
import ch.zhaw.mas.sharingApp.clientSite.domain.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

/************************************************************************************************************
 * RootLayoutController class
 *
 * This is the RootLayoutController and manages all buttons and actions with the RootLayoutView GUI
 *
 * @author  Lukas Grossenbacher
 * @since   2020.12.02
 * @version 0.1
 *
 ************************************************************************************************************/
public class RootLayoutController {

    UserService userService; //Needed for server connection

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
     * void setUserService() Method
     *
     * Sets the UserService() of this dialog to communicate with the server
     *
     * @author  Lukas Grossenbacher
     * @since   2021.01.01
     * version 0.1
     * @param   userService
     * return
     *
     ************************************************************************************************************/
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    /************************************************************************************************************
     * void handleSave() Method
     *
     * This method will be called when the user clicks the save button in GUI. This method will open a save dialog
     * to choose the path and save the data list to this defined path
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.02
     * version 0.1
     * param
     * return
     *
     ************************************************************************************************************/
    @FXML
    private void handleSave(){
        System.out.println("handleSave button clicked");
        //todo GRL: add the save method
    }

    /************************************************************************************************************
     * void handleAbout() Method
     *
     * This method will be called when the user clicks the about button in GUI. This method will open a dialog
     * with description about the program.
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.02
     * version 0.1
     * param
     * return
     *
     ************************************************************************************************************/
    @FXML
    private void handleAbout(){
        System.out.println("handleAbout button clicked");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SharingApp");
        alert.setHeaderText("About");
        alert.setContentText("" +
                "Authors:\n" +
                "Adrian Fischer,\n" +
                "Noemi Kaelin,\n" +
                "Lukas Grossenbacher\n\n" +

                "SharingApp Version:\nv0.1\n\n" +

                "GitRepository:\nhttps://github.com/lukasGroba/ZHAW-SharingApp-2020\n\n" +

                "Copyright:\n(C) 2020 SharingAppTeam / This program may be used freely. " +
                        "No liability is assumed by the developers.\n\n");

        alert.showAndWait();
    }

    /************************************************************************************************************
     * void handleDeleteUser() Method
     *
     * This method will be called when the user clicks the DeleteUser button in GUI. This method will delete the
     * current logged in User on the server and close and generate a System.exit(0) to close the application.
     *
     * author  Lukas Grossenbacher
     * @since   2021.01.01
     * version 0.1
     * param
     * return
     *
     ************************************************************************************************************/
    @FXML
    private void handleDeleteUser() {
        boolean deleted = false;
        try{
            userService.deleteUserByMail(sharingApp.getUserData().getMail());
            deleted = true;

        }catch(Exception exp){ /*todo GRL: Add BackendError expection*/
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Connection Error");
            alert.setHeaderText("Please check server connection!");
            alert.setContentText("Not able to delete the current user. Please check server connection!");
            alert.showAndWait();
        }

        if(deleted){
            System.exit(0);
        }

    }
    /************************************************************************************************************
     * void handleExit() Method
     *
     * This method will be called when the user clicks the exit button in GUI. This method will close the
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
    private void handleExit() {
        System.exit(0);
    }
}