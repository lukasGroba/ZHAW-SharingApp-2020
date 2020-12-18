package ch.zhaw.mas.sharingApp.clientSite.presentation;

import ch.zhaw.mas.sharingApp.clientSite.SharingApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

class SignUpUserDialogControllerTest extends ApplicationTest {

    TextField userNameFieldTest;
    TextField userMailFieldTest;
    PasswordField userPasswordFieldTest;
    PasswordField userPasswordValidationFieldTest;

    Button btnOkTest;
    Button btnCancelTest;
    SignUpUserDialogController controller;

    Parent mainNode;

    @Override
    public void start(Stage stage) throws Exception{
        controller = new SignUpUserDialogController();

        /*Load EditItemDialog stage*/
        FXMLLoader loader = new FXMLLoader(SharingApp.class.getClassLoader().getResource("FXML/SignUpUserDialog.fxml"));
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
        userNameFieldTest = find("#userNameField");
        userMailFieldTest = find("#userMailField");
        userPasswordFieldTest = find("#userPasswordField");
        userPasswordValidationFieldTest = find("#userPasswordValidationField");


        btnOkTest = find("#signUpBtnOk");
        btnCancelTest = find("#signUpBtnCancel");

    }

    @AfterEach
    public void tearDown() throws TimeoutException {
        /*Close the window and clear all possible key or mouse events*/
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});

    }

    @Test
    public void testTypeUserName() {
        clickOn(userNameFieldTest).type(KeyCode.B).type(KeyCode.R).type(KeyCode.I).type(KeyCode.A).type(KeyCode.N);
        WaitForAsyncUtils.waitForFxEvents();
        assertEquals("brian", userNameFieldTest.getText());
    }

    @Test
    public void testTypeUserMail() {
        clickOn(userMailFieldTest).type(KeyCode.B).type(KeyCode.R).type(KeyCode.I).type(KeyCode.A).type(KeyCode.N)
                .press(KeyCode.CONTROL,KeyCode.ALT, KeyCode.DIGIT2).release(KeyCode.CONTROL,KeyCode.ALT, KeyCode.DIGIT2)
                .type(KeyCode.G).type(KeyCode.M).type(KeyCode.X).type(KeyCode.DECIMAL)
                .type(KeyCode.C).type(KeyCode.H);
        WaitForAsyncUtils.waitForFxEvents();
        assertEquals("brian@gmx.ch", userMailFieldTest.getText());
    }

    @Test
    public void testTypeUserPassword() {
        clickOn(userPasswordFieldTest).type(KeyCode.A).type(KeyCode.D).type(KeyCode.M).type(KeyCode.I).type(KeyCode.N);
        WaitForAsyncUtils.waitForFxEvents();
        assertEquals("admin", userPasswordFieldTest.getText());
    }

    @Test
    public void testTypeUserPasswordValidation() {
        clickOn(userPasswordValidationFieldTest).type(KeyCode.A).type(KeyCode.D).type(KeyCode.M).type(KeyCode.I).type(KeyCode.N);
        WaitForAsyncUtils.waitForFxEvents();
        assertEquals("admin", userPasswordValidationFieldTest.getText());
    }
}