package ch.zhaw.mas.sharingApp.clientSite;

import ch.zhaw.mas.sharingApp.clientSite.domain.ItemToShare;
import ch.zhaw.mas.sharingApp.clientSite.domain.User;
import ch.zhaw.mas.sharingApp.clientSite.presentation.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

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
    private static Stage primaryStage;
    private BorderPane rootLayout;
    private boolean isLoginValid = false;

    private ObservableList<ItemFxView> itemDataList = FXCollections.observableArrayList();

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
        this.primaryStage.getIcons().add(new Image("file:../ZHAW-SharingApp-2020/src/main/resources/images/iconNetwork.png"));

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

            dialogStage.getIcons().add(new Image("file:../ZHAW-SharingApp-2020/src/main/resources/images/iconLogin.png"));

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
     * void showEditItemDialog() Method
     *
     * Opens a dialog to edit details for the specified Item. If the user
     * clicks OK, the changes are saved into the provided Item object and true
     * is returned.
     *
     * author  Lukas Grossenbacher
     * @since  2020.12.07
     * version 0.1
     * param   itemFxView
     * @return true if the user clicked OK, false otherwise
     ************************************************************************************************************/
    public static boolean showEditItemDialog(ItemFxView itemFxView){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader(SharingApp.class.getClassLoader().getResource("FXML/EditItemDialog.fxml"));
            System.out.println(loader.getLocation());
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Item");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            dialogStage.getIcons().add(new Image("file:../ZHAW-SharingApp-2020/src/main/resources/images/iconNetwork.png"));

            // Set the person into the controller.
            EditItemDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setItem(itemFxView);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
//        itemDataList.add(new ItemFxView("Hemd", "Max Muster"));
//        itemDataList.add(new ItemFxView("Motorrad", "Franz Meier"));
//        itemDataList.add(new ItemFxView("Dia Projektor", "Harry Hasler"));
//        itemDataList.add(new ItemFxView("Bohrmaschine", "Josef Mueller"));
//        itemDataList.add(new ItemFxView("Schraubenzieherset", "Heidi Gisler"));
//        itemDataList.add(new ItemFxView("Stabmixer", "Jasmin Staub"));

        /*Create sample User*/
        User user = new User();
        user.setUsername("Max Muster");
        user.setMail("max.muster@gmail.com");

        /*Create sample Item*/
        ItemToShare itemToShare = new ItemToShare();
        itemToShare.setId(1);
        itemToShare.setLent(false);
        itemToShare.setRating(3.8);
        itemToShare.setDescription("Das ist eine Beispiel-Beschreibung.\n" +
                                    "35 Zeichen Pro Zeile max. im Moment\n" +
                                    "35 Zeichen Pro Zeile max. im Moment\n" +
                                    "35 Zeichen Pro Zeile max. im Moment\n" +
                                    "35 Zeichen Pro Zeile max. im Moment\n");
        itemToShare.setOwner(user);
        itemToShare.setDateCreated(LocalDate.of(2020,12,07));
        itemToShare.setLentFrom(LocalDate.of(1999,01,01));
        itemToShare.setLentTill(LocalDate.of(1999,01,01));

        itemToShare.setName("Bohrmaschine");
        itemDataList.add(new ItemFxView(itemToShare));

        itemToShare.setName("Hut");
        itemDataList.add(new ItemFxView(itemToShare));

        itemToShare.setName("Motorrad");
        itemDataList.add(new ItemFxView(itemToShare));

        itemToShare.setName("Dia Projektor");
        itemDataList.add(new ItemFxView(itemToShare));

        itemToShare.setName("Schraubenzieherset");
        itemDataList.add(new ItemFxView(itemToShare));

        itemToShare.setName("Stabmixer");
        itemDataList.add(new ItemFxView(itemToShare));
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
    public ObservableList<ItemFxView> getItemData() {
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
