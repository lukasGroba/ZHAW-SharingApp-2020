package ch.zhaw.mas.sharingApp.clientSite.persistence.generic;

import lombok.Data;
import org.springframework.web.util.UriComponentsBuilder;

@Data
public abstract class Persistence {
    private RestTemplateOwn restTemplate = new RestTemplateOwn();

    private String url;

    private UriComponentsBuilder componentsBuilder;

    public Persistence(String specificUrl) {
        // TODO: 11.12.2020 from application properties!!
        this.url = "http://localhost:8080/" + specificUrl;
        this.componentsBuilder = UriComponentsBuilder.fromHttpUrl(this.url);
    }

}
