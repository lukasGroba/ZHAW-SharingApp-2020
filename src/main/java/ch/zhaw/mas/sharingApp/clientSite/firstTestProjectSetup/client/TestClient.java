package ch.zhaw.mas.sharingApp.clientSite.firstTestProjectSetup.client;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static javafx.application.Application.launch;

public class TestClient extends Application {


    public static void main(String[] args) {
        launch(args);

    }

    public static void test(){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8080/books/test";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        test();
    }
}
