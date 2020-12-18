package ch.zhaw.mas.sharingApp.clientSite.persistence.generic;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class RequestBuilder {
    private RestTemplateOwn restTemplateOwn = new RestTemplateOwn();



    public HttpEntity<String> httpGetRequest(Map<String, String> params, String url){

        UriComponentsBuilder builder = getBuilder(params, url);

        HttpEntity<String> response = restTemplateOwn.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity(getHeaders()), String.class);

        return response;
    }

    public HttpEntity<String> httpPostRequest(Map<String, String> params, String url, Object body){

        UriComponentsBuilder builder = getBuilder(params, url);

        HttpEntity httpEntity = new HttpEntity(body, getHeaders());

        HttpEntity<String> response = restTemplateOwn.exchange(builder.toUriString(), HttpMethod.POST, httpEntity, String.class);

        return response;
    }

    private UriComponentsBuilder getBuilder(Map<String, String> params, String url){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }
        return builder;
    }
    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        return headers;
    }
}
