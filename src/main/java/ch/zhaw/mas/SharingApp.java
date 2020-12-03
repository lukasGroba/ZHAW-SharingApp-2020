package ch.zhaw.mas;

import ch.zhaw.mas.sharingApp.clientSite.domain.Item;
import ch.zhaw.mas.sharingApp.clientSite.presentation.ItemListOverviewController;
import ch.zhaw.mas.sharingApp.clientSite.presentation.LoginViewController;
import ch.zhaw.mas.sharingApp.clientSite.presentation.RootLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/************************************************************************************************************
 * SharingApp class
 *
 * Main Program of SharingApp
 * This is the main application of the SharingApp. It contains the main structure of the whole program and GUI
 *
 * @author  Lukas Grossenbacher
 * @since   2020.12.02
 * @version 0.1
 *
 ************************************************************************************************************/
public class SharingApp extends Application
{
    private Stage primaryStage;
    private BorderPane rootLayout;
    private boolean isLoginValid = false;

    private ObservableList<Item> itemDataList = FXCollections.observableArrayList();

    /**
     * Constructor of Sharing App
     */

    public SharingApp(){

        /*Create sample Data List*/
        addSampleItemData();

    }

    /************************************************************************************************************
     * void start() Method
     *
     * This is the start method of the GUI. It start up the login and initialize the rootLayout
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.02
     * version 0.1
     * @param   primaryStage
     * return
     *
     ************************************************************************************************************/
    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("SharingApp");
        //this.primaryStage.getIcons().add(new Image("file:../adressBook2/src/main/resources/images/iconAddressBook.png"));

        /*Check if Login is valid*/
        while(!isLoginValid) {
            openLoginDialog();
        }
        //RootLayout will be initialized
        initRootLayout();

        //Shows the item list inside the rootLayout
        showItemListOverview();


    }

    /************************************************************************************************************
     * void initRootLayout() Method
     *
     * This method initializes the rootLayout of the GUI
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.02
     * version 0.1
     * param
     * return
     *
     ************************************************************************************************************/
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

    /************************************************************************************************************
     * void openLoginDialog() Method
     *
     * This method create and opens the first loginDialog of the SharingApp. This is needed to verify the user.
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.02
     * version 0.1
     * param
     * return
     *
     ************************************************************************************************************/
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

            // Give the controller access to the main SharingApp.
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
    /************************************************************************************************************
     * void showItemListOverview() Method
     *
     * This method create the itemListOverview inside the root Layout to view the list with details.
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.03
     * version 0.1
     * param
     * return
     *
     ************************************************************************************************************/
    public void showItemListOverview(){
        try {
            // Load item list overview
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/ItemListOverview.fxml"));
            System.out.println(loader.getLocation());
            AnchorPane itemListOverview = (AnchorPane) loader.load();

            // Set item list overview into the center of root layout.
            rootLayout.setCenter(itemListOverview);

            // Give the controller access to the main SharingApp.
            ItemListOverviewController controller = loader.getController();
            controller.setSharingApp(this);

        }catch(IOException exp){
            exp.printStackTrace();
        }

    }

    /************************************************************************************************************
     * void addSampleItemData() Method
     *
     * This method create the itemListOverview inside the root Layout to view the list with details.
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.03
     * version 0.1
     * param
     * return
     *
     ************************************************************************************************************/
    public void addSampleItemData(){
        // Add some sample data
        itemDataList.add(new Item("Hemd", "Max Muster"));
        itemDataList.add(new Item("Motorrad", "Franz Meier"));
        itemDataList.add(new Item("Dia Projektor", "Harry Hasler"));
        itemDataList.add(new Item("Bohrmaschine", "Josef Mueller"));
        itemDataList.add(new Item("Schraubenzieherset", "Heidi Gisler"));
        itemDataList.add(new Item("Stabmixer", "Jasmin Staub"));
    }

    /************************************************************************************************************
     * ObservableList<Item> getItemData() Method
     *
     * This method returns the complete observable itemDataList.
     *
     * author  Lukas Grossenbacher
     * @since  2020.12.03
     * version 0.1
     * param
     * return itemDataList
     *
     ************************************************************************************************************/
    /**
     * Returns the data as an observable list of Persons.
     * @return
     */
    public ObservableList<Item> getItemData() {
        return this.itemDataList;
    }

    /************************************************************************************************************
     * void main() Method
     *
     * This is the main method to start the complete application
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.02
     * version 0.1
     * @param   args
     * return
     *
     ************************************************************************************************************/
    public static void main(String[] args) {
        launch(args);
    }
}
