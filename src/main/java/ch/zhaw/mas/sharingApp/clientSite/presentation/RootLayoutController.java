package ch.zhaw.mas.sharingApp.clientSite.presentation;
import ch.zhaw.mas.SharingApp;
import javafx.fxml.FXML;

/**
 * ClassDescription
 *
 * @author Lukas Grossenbacher
 * @date 2020.12.02
 */
public class RootLayoutController {


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

    @FXML
    private void handleExit() {
        System.exit(0);
    }
}