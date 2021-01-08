package ch.zhaw.mas.sharingApp.clientSite.persistence.generic;

import lombok.Data;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;


/**
 * This class is for persisting other classes to/from the server. It holds everything that is needed for communication with the server.
 *
 * @author Noemi Kälin
 */
@Data
public abstract class Persistence {
    private RestTemplateOwn restTemplate = new RestTemplateOwn();

    private String url;

    private UriComponentsBuilder componentsBuilder;

    /**
     * @param specificUrl The url that is needed to adress the specific part responsible for persisting the given Class.
     * @author Noemi Kälin
     */
    public Persistence(String specificUrl) {
        // TODO: 11.12.2020 from application properties!!
        this.url = "http://localhost:8080/" + specificUrl;
        this.componentsBuilder = UriComponentsBuilder.fromHttpUrl(this.url);
    }

    protected Map<String, String> getParamsWithUserLoggedIn(String userLoggedInMail){
        Map<String, String> params = new HashMap<>();
        params.put("userLoggedInMail", userLoggedInMail);
        return params;
    }

}
