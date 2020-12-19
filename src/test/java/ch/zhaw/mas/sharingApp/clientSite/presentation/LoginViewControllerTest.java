package ch.zhaw.mas.sharingApp.clientSite.presentation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.zhaw.mas.sharingApp.clientSite.SharingApp;
import ch.zhaw.mas.sharingApp.clientSite.domain.User;
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
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.TimeoutException;

/************************************************************************************************************
 * LoginViewControllerTest class
 *
 * This is the Test Class for the LoginViewController. In this class some jUnit Tests will be
 * implemented for automatic testing
 *
 * @author  Lukas Grossenbacher
 * @since   2020.12.19
 * @version 0.1
 *
 ************************************************************************************************************/
class LoginViewControllerTest extends ApplicationTest {
    TextField nameTest;
    PasswordField passwordTest;
    Button btnLoginTest;
    Button btnCancelTest;
    Button btnSignUpTest;
    LoginViewController controller;

    Parent mainNode;

    @Override
    public void start(Stage stage) throws Exception{
        controller = new LoginViewController();
        FXMLLoader loader = new FXMLLoader(SharingApp.class.getClassLoader().getResource("FXML/LoginView.fxml"));
        mainNode = (AnchorPane) loader.load();
        stage.setScene(new Scene(mainNode));
        stage.show();       //Displays the LoginView Stage
        stage.toFront();    //Set the Stage to the front that the robot for testing is interacting with the correct window!
    }

    /*Shortcut to retrieve widgets in the GUI*/
    public <T extends Node> T find(final String query){
        return lookup(query).query();
    }

    @BeforeEach
    public void setUp(){

        /*Retrieving the tested widgets from the GUI*/
        nameTest = find("#userName");
        passwordTest = find("#userPassword");

        btnLoginTest = find("#loginBtn");
        btnCancelTest = find("#cancelBtn");
        btnSignUpTest = find("#signUpBtn");

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
        clickOn(nameTest).type(KeyCode.B).type(KeyCode.R).type(KeyCode.I).type(KeyCode.A).type(KeyCode.N);
        WaitForAsyncUtils.waitForFxEvents();
        assertEquals("brian", nameTest.getText());
    }

    @Test
    public void testTypeUserPassword() {
        clickOn(passwordTest).type(KeyCode.A).type(KeyCode.D).type(KeyCode.M).type(KeyCode.I).type(KeyCode.N);
        WaitForAsyncUtils.waitForFxEvents();
        assertEquals("admin", passwordTest.getText());
    }

    @Test
    public void testClickOnButtonLoginNotValid(){

        clickOn(btnLoginTest);
        WaitForAsyncUtils.waitForFxEvents();

        assertFalse(controller.isLoginValid());
    }

    @Test
    public void testClickOnButtonLoginValid(){
        //Boolean loginValidTest = true;

//        clickOn(nameTest).type(KeyCode.B).type(KeyCode.R).type(KeyCode.I).type(KeyCode.A).type(KeyCode.N);
//        WaitForAsyncUtils.waitForFxEvents();
//
//        clickOn(passwordTest).type(KeyCode.A).type(KeyCode.D).type(KeyCode.M).type(KeyCode.I).type(KeyCode.N);
//        WaitForAsyncUtils.waitForFxEvents();
//
//        clickOn(btnLoginTest);
//        WaitForAsyncUtils.waitForFxEvents();
//
//        assertTrue(controller.isLoginValid());
    }

}