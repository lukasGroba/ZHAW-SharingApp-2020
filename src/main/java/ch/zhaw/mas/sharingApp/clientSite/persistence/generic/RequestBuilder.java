package ch.zhaw.mas.sharingApp.clientSite.persistence.generic;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * Class used to build a http request.
 * @author Noemi Kälin
 */
public class RequestBuilder {
    private RestTemplateOwn restTemplateOwn = new RestTemplateOwn();


    /**
     * Creates a get request and returns the response from the server.
     * @param params Parameters to add to the request (for example id, name, etc.)
     * @param url Url where the request should be sent.
     * @author Noemi Kälin
     */
    public HttpEntity<String> httpGetRequest(Map<String, String> params, String url){

        UriComponentsBuilder builder = getBuilder(params, url);

        HttpEntity<String> response = restTemplateOwn.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity(getHeaders()), String.class);

        return response;
    }

    /**
     * Creates a post request and returns the response from the server.
     * @param params Parameters to add to the request (for example id, name, etc.)
     * @param url Url where the request should be sent.
     * @param body The object that should be sent.
     * @author Noemi Kälin
     */
    public HttpEntity<String> httpPostRequest(Map<String, String> params, String url, Object body){

        UriComponentsBuilder builder = getBuilder(params, url);

        HttpEntity httpEntity = new HttpEntity(body, getHeaders());

        HttpEntity<String> response = restTemplateOwn.exchange(builder.toUriString(), HttpMethod.POST, httpEntity, String.class);

        return response;
    }

    public HttpEntity<String> httpDeleteRequest(Map<String, String> params, String url){
        UriComponentsBuilder builder = getBuilder(params, url);

        HttpEntity<String> response = restTemplateOwn.exchange(builder.toUriString(), HttpMethod.DELETE, new HttpEntity(getHeaders()), String.class);

        return response;
    }

    public HttpEntity<String> httpPutRequest(Map<String, String> params, String url, Object body){
        UriComponentsBuilder builder = getBuilder(params, url);

        HttpEntity httpEntity = new HttpEntity(body, getHeaders());

        HttpEntity<String> response = restTemplateOwn.exchange(builder.toUriString(), HttpMethod.PUT, httpEntity, String.class);
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
