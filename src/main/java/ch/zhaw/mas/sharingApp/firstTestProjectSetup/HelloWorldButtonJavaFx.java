package ch.zhaw.mas.sharingApp.firstTestProjectSetup;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloWorldButtonJavaFx extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        Label label1 = new Label("Hallo");
        Label label2 = new Label("Welt");
        VBox root = new VBox();//Definition der Layout Klasse
        root.getChildren().add(label1);
        root.getChildren().add(label2);

        /*Erstellen eines Fensters (Scene)*/
        Scene scene = new Scene(root, 180, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello World");   //Beschriftung des Fensters
        primaryStage.show();

        /*Button erstellen*/
        root.getChildren().add(createButton());
        root.getChildren().add(createButtonLambda());
    }


    Pane createButton() {
        final Button button = new Button();
        button.setText("Add 'Hello World' Label");
        final FlowPane pane = new FlowPane();
        pane.setPadding(new Insets(7, 7, 7, 7));
        pane.getChildren().add(button);
// ActionHandler registrieren
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().add(new Label("- Hello World! -"));
            }
        });
        return pane;
    }

    Pane createButtonLambda() {
        final Button button = new Button();
        button.setText("Add 'Hello World' Label");
        final FlowPane pane = new FlowPane();
        pane.setPadding(new Insets(7, 7, 7, 7));
        pane.getChildren().add(button);
        // ActionHandler registrieren
        button.setOnAction(event -> pane.getChildren()
                .add(new Label("- Hello World! -")));

        return pane;
    }

}
