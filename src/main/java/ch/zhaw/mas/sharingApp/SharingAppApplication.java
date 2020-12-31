package ch.zhaw.mas.sharingApp;

import ch.zhaw.mas.sharingApp.clientSite.SharingApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
@SpringBootApplication
public class SharingAppApplication {


	public static void main(String[] args) {
		SharingApp.launch(SharingApp.class, args);	//Runs SharingApplication GUI
		SpringApplication.run(SharingAppApplication.class, args);
	}



}
