package ch.zhaw.mas;

import ch.zhaw.mas.sharingApp.clientSite.presentation.LoginViewController;
import ch.zhaw.mas.sharingApp.clientSite.presentation.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main Program
 *
 * Developed by: Adrian Fischer, Kaelin Noemi, Lukas Grossenbacher
 *
 */
public class SharingApp extends Application
{
    private Stage primaryStage;
    private BorderPane rootLayout;
    private boolean isLoginValid = false;

    /**
     * Constructor of Sharing App
     */

    public SharingApp(){

    }

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("SharingApp");
        //this.primaryStage.getIcons().add(new Image("file:../adressBook2/src/main/resources/images/iconAddressBook.png"));

        /*Check if Login is valid*/
        while(!isLoginValid) {
            openLoginDialog();
        }

        initRootLayout();


    }

    /**
     * Initializes the root layout
     *
     * @author Lukas Grossenbacher
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/RootLayout.fxml"));
            System.out.println(loader.getLocation());
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setSharingApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the openLoginDialog
     */


    public void openLoginDialog() {
        try {
            // Load LoginView layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/LoginView.fxml"));
            System.out.println(loader.getLocation());
            AnchorPane loginPage = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Login SharingApp");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(loginPage);
            dialogStage.setScene(scene);

            // Give the controller access to the main app.
            LoginViewController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            if(controller.isLoginValid()){
                isLoginValid = true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
