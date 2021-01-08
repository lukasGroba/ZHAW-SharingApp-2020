package ch.zhaw.mas.sharingApp.clientSite;

import ch.zhaw.mas.sharingApp.clientSite.domain.services.ItemService;
import ch.zhaw.mas.sharingApp.clientSite.domain.services.UserService;
import ch.zhaw.mas.sharingApp.clientSite.presentation.ItemFxView;
import ch.zhaw.mas.sharingApp.clientSite.domain.ItemToShare;
import ch.zhaw.mas.sharingApp.clientSite.domain.User;
import ch.zhaw.mas.sharingApp.clientSite.presentation.ItemListOverviewController;
import ch.zhaw.mas.sharingApp.clientSite.presentation.LoginViewController;
import ch.zhaw.mas.sharingApp.clientSite.presentation.RootLayoutController;
import ch.zhaw.mas.sharingApp.clientSite.presentation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/************************************************************************************************************
 * SharingApp class
 *
 * Main Program of SharingApp
 * This is the main application of the SharingApp. It contains the main structure of the whole program and GUI
 *
 * @author  Lukas Grossenbacher
 * @since   2020.12.21
 * @version 0.2
 *
 ************************************************************************************************************/
public class SharingApp extends Application


{
    private static Stage primaryStage;
    private BorderPane rootLayout;
    private boolean isLoginValid = false;
    private static User user = null;
    private static UserService userService = null;
    private static ItemService itemService = null;

    private ObservableList<ItemFxView> itemDataList = FXCollections.observableArrayList();

    public static User getUser() {
        return user;
    }

    /**
     * Constructor of Sharing App
     */

    public SharingApp(){
        /*Create an empty user, which can be filled after valid login*/
        this.user = new User();

        /*Create the UserService of the SharingApplication to communicate with the Server about Users*/
        this.userService = new UserService(getUser());

        /*Create the ItemService of the SharingApplication to communicate with the Server about Items*/
        this.itemService = new ItemService();

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
        openLoginDialog();

        /*Start application if login is valid*/
        if(isLoginValid) {

            //RootLayout will be initialized
            initRootLayout();

            /*Create Initial List from Server after valid login*/
            loadCompleteListFromServer();

            //Shows the item list inside the rootLayout
            showItemListOverview();
        }

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
            controller.setUserService(this.userService); /*Sets the reference of the UserService to communicate with the server*/

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
            controller.setSharingApp(this); //Set reference the main SharingApp
            controller.setDialogStage(dialogStage);
            controller.setUserService(this.userService); /*Sets the reference of the UserService to communicate with the server*/

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
     * void showSignUpUserDialog() Method
     *
     * This method create and opens the SignUpUserDialog of the SharingApp. This is needed to sign up a new User.
     * The user will also checked from the server if it already exists in the system.
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.08
     * version 0.1
     * param
     * return
     *
     ************************************************************************************************************/
    public void showSignUpUserDialog(){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/SignUpUserDialog.fxml"));
            System.out.println(loader.getLocation());
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Sign Up new User");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            dialogStage.getIcons().add(new Image("file:../ZHAW-SharingApp-2020/src/main/resources/images/iconLogin.png"));

            // Set the person into the controller.
            SignUpUserDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUserService(this.userService); /*Sets the reference of the UserService to communicate with the server*/

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

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
            controller.setItemService(this.itemService);

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

        /*Create sample User*/
        User sampleUser = new User();
        sampleUser.setUsername("Max Muster");
        sampleUser.setMail("max.muster@gmail.com");

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
        itemToShare.setOwner(sampleUser);
        itemToShare.setDateCreated(LocalDate.now());
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
     * void loadCompleteListFromServer() Method
     *
     * This method loads the complete item list from the server and load it into the observable list.
     *
     * author  Lukas Grossenbacher
     * @since   2020.12.21
     * version 0.1
     * param
     * return
     *
     ************************************************************************************************************/
    public void loadCompleteListFromServer(){
        List<ItemToShare> itemToShareList = new ArrayList<ItemToShare>();

        //Clear observable list to refresh it from server
        itemDataList.clear();

        try {
            itemToShareList = itemService.getAllItems();    //Load complete item list from the server

            /*Converts complete list of ItemToShare into list of ItemFxView*/
            for (ItemToShare itemToShare : itemToShareList) {
                itemDataList.add(new ItemFxView(itemToShare));
            }
        }catch(Exception exp){
            exp.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(primaryStage);
            alert.setTitle("Connection Error");
            alert.setHeaderText("Load of List Failed");
            alert.setContentText("Please startup the Server for SharingAppApplication");

            alert.showAndWait();
        }

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
     * @return itemDataList
     *
     ************************************************************************************************************/
    public ObservableList<ItemFxView> getItemData() {
        return this.itemDataList;
    }

    /************************************************************************************************************
     * User getUserData() Method
     *
     * This method returns the actual logged in user.
     *
     * author  Lukas Grossenbacher
     * @since  2020.12.14
     * version 0.1
     * param
     * return user
     *
     ************************************************************************************************************/
    public User getUserData() {
        return this.user;
    }

    /************************************************************************************************************
     * setUserData() Method
     *
     * This method sets the actual logged in user.
     *
     * author  Lukas Grossenbacher
     * @since  2020.12.19
     * version 0.1
     * param
     * return user
     *
     ************************************************************************************************************/
    public void setUserData(User user) {
        this.user = user;
    }
}