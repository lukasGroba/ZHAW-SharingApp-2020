package ch.zhaw.mas.sharingApp.clientSite.persistence;

import lombok.Data;

@Data
public abstract class Persistence {
    private RestTemplateOwn restTemplate = new RestTemplateOwn();

    private String url;

    public Persistence(String specificUrl) {
        // TODO: 11.12.2020 from application properties!!
        this.url = "http://localhost:8080/" + specificUrl;
    }
}
