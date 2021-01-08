package ch.zhaw.mas.sharingApp.clientSite.persistence;

import ch.zhaw.mas.sharingApp.clientSite.domain.ItemToShare;
import ch.zhaw.mas.sharingApp.clientSite.persistence.generic.BackendError;
import ch.zhaw.mas.sharingApp.clientSite.persistence.generic.Persistence;
import ch.zhaw.mas.sharingApp.clientSite.persistence.generic.RequestBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is for persisting Items to/from the server.
 *
 * @author Noemi Kälin
 */
@Service
public class ItemPersistence extends Persistence {
    public ItemPersistence() {
        super("items");
    }


    public List<ItemToShare> getAllItems(String userLoggedInMail) throws JsonProcessingException, BackendError {
        RequestBuilder requestBuilder = new RequestBuilder();
        Map<String, String> params = getParamsWithUserLoggedIn(userLoggedInMail);
        ItemToShare[] items = (ItemToShare[]) requestBuilder.httpGetRequest(params, this.getUrl(), ItemToShare[].class);
        return Arrays.asList(items);
    }

    public void saveNewItem(ItemToShare item, String userLoggedInMail) throws BackendError {
        RequestBuilder requestBuilder = new RequestBuilder();
        Map<String, String> params = getParamsWithUserLoggedIn(userLoggedInMail);
        HttpEntity<String> response = requestBuilder.httpPostRequest(params, this.getUrl(), item);
    }

    public void deleteItem(Integer id, String userLoggedInMail) throws BackendError {
        Map<String, String> params = getParamsWithUserLoggedIn(userLoggedInMail);
        params.put("id", id.toString());
        RequestBuilder requestBuilder = new RequestBuilder();
        HttpEntity<String> response = requestBuilder.httpDeleteRequest(params, this.getUrl());
    }


    public void updateItem(ItemToShare item, String userLoggedInMail) throws BackendError {
        Map<String, String> params = getParamsWithUserLoggedIn(userLoggedInMail);
        params.put("id", item.getIdAsString());
        RequestBuilder requestBuilder = new RequestBuilder();
        HttpEntity<String> response = requestBuilder.httpPutRequest(params, this.getUrl(), item);
    }
}
