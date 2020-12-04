package ch.zhaw.mas.sharingApp.clientSite.presentation;
import ch.zhaw.mas.sharingApp.clientSite.SharingApp;
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
     * void handleNew() Method
     *
     * This method will be called when the user clicks the new button in GUI. This method will open a dialog
     * to create a new item and add it the list on the server.
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.02
     * version 0.1
     * param
     * return
     *
     ************************************************************************************************************/
    @FXML
    private void handleNew(){
        System.out.println("handleNew button clicked");
        //todo GRL: add a method to create a new item
    }

    /************************************************************************************************************
     * void handleEdit() Method
     *
     * This method will be called when the user clicks the edit button in GUI. This method will open a dialog
     * to edit an item and store it in the list on the server.
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.02
     * version 0.1
     * param
     * return
     *
     ************************************************************************************************************/
    @FXML
    private void handleEdit(){
        System.out.println("handleEdit button clicked");
        //todo GRL: add a method to edit an item
    }

    /************************************************************************************************************
     * void handleDelete() Method
     *
     * This method will be called when the user clicks the delete button in GUI. This method will delete an item
     * and remove it in the list on the server.
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.02
     * version 0.1
     * param
     * return
     *
     ************************************************************************************************************/
    @FXML
    private void handleDelete(){
        System.out.println("handleDelete button clicked");
        //todo GRL: add a method to delete an item
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