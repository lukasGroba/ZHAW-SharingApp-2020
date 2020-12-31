package ch.zhaw.mas.sharingApp;

import ch.zhaw.mas.sharingApp.clientSite.SharingApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SharingAppApplication {


	public static void main(String[] args) {
		SharingApp.launch(SharingApp.class, args);	//Runs SharingApplication GUI
		SpringApplication.run(SharingAppApplication.class, args);
	}



}
