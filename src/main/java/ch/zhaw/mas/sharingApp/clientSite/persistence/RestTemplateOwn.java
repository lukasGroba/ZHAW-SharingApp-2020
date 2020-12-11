package ch.zhaw.mas.sharingApp.clientSite.persistence;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


public class RestTemplateOwn extends RestTemplate{

    @Autowired
    CloseableHttpClient httpClient;

    public RestTemplate restTemplate() {

        RestTemplate restTemplate = new RestTemplate(this.clientHttpRequestFactory());
        return restTemplate;
    }

    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {

        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClient);
        return clientHttpRequestFactory;
    }
}
