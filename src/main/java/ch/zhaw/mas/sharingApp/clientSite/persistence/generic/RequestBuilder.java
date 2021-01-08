package ch.zhaw.mas.sharingApp.clientSite.persistence.generic;

import ch.zhaw.mas.sharingApp.clientSite.domain.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Class used to build a http request.
 *
 * @author Noemi Kälin
 */
public class RequestBuilder {
    private RestTemplateOwn restTemplateOwn = new RestTemplateOwn();


    /**
     * Creates a get request and returns the response from the server.
     *
     * @param params Parameters to add to the request (for example id, name, etc.)
     * @param url    Url where the request should be sent.
     * @author Noemi Kälin
     */
//    public HttpEntity<String> httpGetRequest(Map<String, String> params, String url) throws BackendError {
//
//        UriComponentsBuilder builder = getBuilder(params, url);
//
//        try {
//            HttpEntity<String> response = restTemplateOwn.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity(getHeaders()), String.class);
//            return response;
//        } catch (Exception ex) {
//            throw new BackendError(ex.getMessage());
//        }
//    }

    public Object httpGetRequest(Map<String, String> params, String url, Class expectedClass) throws BackendError {
        UriComponentsBuilder builder = getBuilder(params, url);
//        System.out.println(builder.toUriString());
        try {
            ResponseEntity responseEntity = restTemplateOwn.getForEntity(builder.toUriString(), expectedClass);
            return responseEntity.getBody();
        } catch (Exception ex) {
            throw new BackendError(ex.getMessage());
        }
    }

    /**
     * Creates a post request and returns the response from the server.
     *
     * @param params Parameters to add to the request (for example id, name, etc.)
     * @param url    Url where the request should be sent.
     * @param body   The object that should be sent.
     * @author Noemi Kälin
     */
    public HttpEntity<String> httpPostRequest(Map<String, String> params, String url, Object body) throws BackendError {

        UriComponentsBuilder builder = getBuilder(params, url);

        HttpEntity httpEntity = new HttpEntity(body, getHeaders());

        try {
            HttpEntity<String> response = restTemplateOwn.exchange(builder.toUriString(), HttpMethod.POST, httpEntity, String.class);

            return response;
        } catch (Exception ex) {
            throw new BackendError(ex.getMessage());
        }

    }

    public HttpEntity<String> httpDeleteRequest(Map<String, String> params, String url) throws BackendError {
        UriComponentsBuilder builder = getBuilder(params, url);

        try {
            HttpEntity<String> response = restTemplateOwn.exchange(builder.toUriString(), HttpMethod.DELETE, new HttpEntity(getHeaders()), String.class);

            return response;
        } catch (RestClientException e) {
            throw new BackendError(e.getMessage());
        }

    }

    public HttpEntity<String> httpPutRequest(Map<String, String> params, String url, Object body) throws BackendError {
        UriComponentsBuilder builder = getBuilder(params, url);

        HttpEntity httpEntity = new HttpEntity(body, getHeaders());

        try {
            HttpEntity<String> response = restTemplateOwn.exchange(builder.toUriString(), HttpMethod.PUT, httpEntity, String.class);
            return response;
        } catch (RestClientException e) {
            throw new BackendError(e.getMessage());
        }

    }

    private UriComponentsBuilder getBuilder(Map<String, String> params, String url) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }
        return builder;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        return headers;
    }
}
