package ch.zhaw.mas.sharingApp.clientSite.persistence;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

public class RequestBuilder {
    private RestTemplateOwn restTemplateOwn = new RestTemplateOwn();



    public HttpEntity<String> getHttpRequest(Map<String, String> params, String url, HttpMethod httpMethod){

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        HttpEntity<String> response = restTemplateOwn.exchange(builder.toUriString(), httpMethod, new HttpEntity(headers), String.class);

        return response;
    }
}
