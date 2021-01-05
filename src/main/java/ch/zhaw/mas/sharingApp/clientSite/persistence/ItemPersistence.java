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


    // TODO: 31.12.2020 not working (json processing...)
    public List<ItemToShare> getAllItems() throws JsonProcessingException, BackendError {
        RequestBuilder requestBuilder = new RequestBuilder();
        ItemToShare[] items = (ItemToShare[]) requestBuilder.httpGetRequest(new HashMap<>(), this.getUrl(), ItemToShare[].class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());


//        List<ItemToShare> items = objectMapper.readValue(response.getBody(), new TypeReference<List<ItemToShare>>(){});
//        for(ItemToShare item : items){
//            item = objectMapper.convertValue(item, ItemToShare.class);
//        }
        return Arrays.asList(items);
    }

    public void saveNewItem(ItemToShare item) throws BackendError {
        RequestBuilder requestBuilder = new RequestBuilder();
        HttpEntity<String> response = requestBuilder.httpPostRequest(new HashMap<>(), this.getUrl(), item);
    }

    public void deleteItem(Integer id) throws BackendError {
        Map<String, String> params = new HashMap<>();
        params.put("id", id.toString());
        RequestBuilder requestBuilder = new RequestBuilder();
        HttpEntity<String> response = requestBuilder.httpDeleteRequest(params, this.getUrl());
    }


    public void updateItem(ItemToShare item) throws BackendError {
        Map<String, String> params = new HashMap<>();
        params.put("id", item.getIdAsString());
        RequestBuilder requestBuilder = new RequestBuilder();
        HttpEntity<String> response = requestBuilder.httpPutRequest(params, this.getUrl(), item);
    }
}
