package ch.zhaw.mas.sharingApp.clientSite.domain;

import ch.zhaw.mas.sharingApp.clientSite.persistence.Userpersistence;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String username;
    private String mail; // evtl. = username?
//    private String password;
//    private Location location;
// Items? Picture? Bewertung?


}
