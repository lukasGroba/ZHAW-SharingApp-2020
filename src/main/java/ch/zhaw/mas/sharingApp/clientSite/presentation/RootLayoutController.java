package ch.zhaw.mas.sharingApp.clientSite.presentation;
import ch.zhaw.mas.SharingApp;
import javafx.fxml.FXML;

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