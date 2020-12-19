package ch.zhaw.mas.sharingApp.clientSite.presentation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.zhaw.mas.sharingApp.clientSite.SharingApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.TimeoutException;

/************************************************************************************************************
 * EditItemDialogControllerTest class
 *
 * This is the Test Class for the EditItemDialogController. In this class some jUnit Tests will be
 * implemented for automatic testing
 *
 * @author  Lukas Grossenbacher
 * @since   2020.12.19
 * @version 0.1
 *
 ************************************************************************************************************/
class EditItemDialogControllerTest extends ApplicationTest{

    TextField itemNameFieldTest;
    TextField dateCreatedFieldTest;
    TextField isItemLentFieldTest;
    TextField itemRatingFieldTest;
    TextField itemLentFromFieldTest;
    TextField itemLentTillFieldTest;
    TextArea itemDescriptionFieldTest;
    Button btnOkTest;
    Button btnCancelTest;
    EditItemDialogController controller;

    Parent mainNode;

    @Override
    public void start(Stage stage) throws Exception{
        controller = new EditItemDialogController();

        /*Load EditItemDialog stage*/
        FXMLLoader loader = new FXMLLoader(SharingApp.class.getClassLoader().getResource("FXML/EditItemDialog.fxml"));
        mainNode = (AnchorPane) loader.load();
        stage.setScene(new Scene(mainNode));
        stage.show();       //Displays the LoginView Stage
        stage.toFront();    //Set the Stage to the front that the robot for testing is interacting with the correct window!
        controller.setDialogStage(stage);
    }

    /*Shortcut to retrieve widgets in the GUI*/
    public <T extends Node> T find(final String query) {
        return lookup(query).query();
    }

    @BeforeEach
    void setUp() {
        /*Retrieving the tested widgets from the GUI*/
        itemNameFieldTest = find("#itemNameField");
        dateCreatedFieldTest = find("#dateCreatedField");
        isItemLentFieldTest = find("#isItemLentField");
        itemRatingFieldTest = find("#itemRatingField");
        itemLentFromFieldTest = find("#itemLentFromField");
        itemLentTillFieldTest = find("#itemLentTillField");
        itemDescriptionFieldTest = find("#itemDescriptionField");

        btnOkTest = find("#editItemOkBtn");
        btnCancelTest = find("#editItemCancelBtn");


    }

    @AfterEach
    public void tearDown() throws TimeoutException {
        /*Close the window and clear all possible key or mouse events*/
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});

    }

    @Test
    public void testTypeItemName() {
        clickOn(itemNameFieldTest).type(KeyCode.T).type(KeyCode.E).type(KeyCode.S).type(KeyCode.T);
        WaitForAsyncUtils.waitForFxEvents();
        assertEquals("test", itemNameFieldTest.getText());
    }

    @Test
    public void testTypeCreateDate() {
        clickOn(dateCreatedFieldTest).type(KeyCode.DIGIT1).type(KeyCode.DIGIT2).type(KeyCode.DECIMAL)
                .type(KeyCode.DIGIT0).type(KeyCode.DIGIT5).type(KeyCode.DECIMAL)
                .type(KeyCode.DIGIT2).type(KeyCode.DIGIT0).type(KeyCode.DIGIT2).type(KeyCode.DIGIT0);
        WaitForAsyncUtils.waitForFxEvents();
        assertEquals("12.05.2020", dateCreatedFieldTest.getText());
    }

    @Test
    public void testTypeIsItemLent() {
        clickOn(isItemLentFieldTest).type(KeyCode.T).type(KeyCode.R).type(KeyCode.U).type(KeyCode.E);
        WaitForAsyncUtils.waitForFxEvents();
        assertEquals("true", isItemLentFieldTest.getText());
    }

    @Test
    public void testTypeItemRating() {
        clickOn(itemRatingFieldTest).type(KeyCode.DIGIT2).type(KeyCode.DECIMAL).type(KeyCode.DIGIT5);
        WaitForAsyncUtils.waitForFxEvents();
        assertEquals("2.5", itemRatingFieldTest.getText());
    }

    @Test
    public void testTypeLentFrom() {
        clickOn(itemLentFromFieldTest).type(KeyCode.DIGIT1).type(KeyCode.DIGIT2).type(KeyCode.DECIMAL)
                .type(KeyCode.DIGIT0).type(KeyCode.DIGIT5).type(KeyCode.DECIMAL)
                .type(KeyCode.DIGIT2).type(KeyCode.DIGIT0).type(KeyCode.DIGIT2).type(KeyCode.DIGIT0);
        WaitForAsyncUtils.waitForFxEvents();
        assertEquals("12.05.2020", itemLentFromFieldTest.getText());
    }

    @Test
    public void testTypeLentTill() {
        clickOn(itemLentTillFieldTest).type(KeyCode.DIGIT1).type(KeyCode.DIGIT2).type(KeyCode.DECIMAL)
                .type(KeyCode.DIGIT0).type(KeyCode.DIGIT5).type(KeyCode.DECIMAL)
                .type(KeyCode.DIGIT2).type(KeyCode.DIGIT0).type(KeyCode.DIGIT2).type(KeyCode.DIGIT0);
        WaitForAsyncUtils.waitForFxEvents();
        assertEquals("12.05.2020", itemLentTillFieldTest.getText());
    }
    @Test
    public void testTypeItemDescription() {
        clickOn(itemDescriptionFieldTest).type(KeyCode.T).type(KeyCode.E).type(KeyCode.S).type(KeyCode.T);
        WaitForAsyncUtils.waitForFxEvents();
        assertEquals("test", itemDescriptionFieldTest.getText());
    }

//    @Test
//    public void testIfOkClickedIsTrueInputIsValid() {
//        clickOn(itemNameFieldTest).type(KeyCode.T).type(KeyCode.E).type(KeyCode.S).type(KeyCode.T);
//        WaitForAsyncUtils.waitForFxEvents();
//
//        clickOn(dateCreatedFieldTest).type(KeyCode.DIGIT1).type(KeyCode.DIGIT2).type(KeyCode.DECIMAL)
//                .type(KeyCode.DIGIT0).type(KeyCode.DIGIT5).type(KeyCode.DECIMAL)
//                .type(KeyCode.DIGIT2).type(KeyCode.DIGIT0).type(KeyCode.DIGIT2).type(KeyCode.DIGIT0);
//        WaitForAsyncUtils.waitForFxEvents();
//
//        clickOn(isItemLentFieldTest).type(KeyCode.T).type(KeyCode.R).type(KeyCode.U).type(KeyCode.E);
//        WaitForAsyncUtils.waitForFxEvents();
//
//        clickOn(itemRatingFieldTest).type(KeyCode.DIGIT2).type(KeyCode.DECIMAL).type(KeyCode.DIGIT5);
//        WaitForAsyncUtils.waitForFxEvents();
//
//        clickOn(itemLentFromFieldTest).type(KeyCode.DIGIT1).type(KeyCode.DIGIT2).type(KeyCode.DECIMAL)
//                .type(KeyCode.DIGIT0).type(KeyCode.DIGIT5).type(KeyCode.DECIMAL)
//                .type(KeyCode.DIGIT2).type(KeyCode.DIGIT0).type(KeyCode.DIGIT2).type(KeyCode.DIGIT0);
//        WaitForAsyncUtils.waitForFxEvents();
//
//        clickOn(itemLentTillFieldTest).type(KeyCode.DIGIT1).type(KeyCode.DIGIT2).type(KeyCode.DECIMAL)
//                .type(KeyCode.DIGIT0).type(KeyCode.DIGIT5).type(KeyCode.DECIMAL)
//                .type(KeyCode.DIGIT2).type(KeyCode.DIGIT0).type(KeyCode.DIGIT2).type(KeyCode.DIGIT0);
//        WaitForAsyncUtils.waitForFxEvents();
//
//        clickOn(itemDescriptionFieldTest).type(KeyCode.T).type(KeyCode.E).type(KeyCode.S).type(KeyCode.T);
//        WaitForAsyncUtils.waitForFxEvents();
//
//        clickOn(btnOkTest);
//        WaitForAsyncUtils.waitForFxEvents();
//
//        assertTrue(controller.isOkClicked());
//    }
//
//    private ItemToShare createSampleItem(){
//        /*Create sample User*/
//        User sampleUser = new User();
//        sampleUser.setUsername("Test User");
//        sampleUser.setMail("test.user@sample.com");
//
//        /*Create sample Item*/
//        ItemToShare itemToShare = new ItemToShare();
//        itemToShare.setId(0);
//        itemToShare.setLent(false);
//        itemToShare.setRating(3.8);
//        itemToShare.setDescription("Test Item");
//        itemToShare.setOwner(sampleUser);
//        itemToShare.setDateCreated(LocalDate.now());
//        itemToShare.setLentFrom(LocalDate.of(1999,01,01));
//        itemToShare.setLentTill(LocalDate.of(1999,01,01));
//        itemToShare.setName("TestItem");
//
//        return(itemToShare);
//    }
}