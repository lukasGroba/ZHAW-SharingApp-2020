package ch.zhaw.mas.sharingApp.clientSite.persistence;

import ch.zhaw.mas.sharingApp.clientSite.domain.ItemToShare;
import ch.zhaw.mas.sharingApp.clientSite.domain.User;
import ch.zhaw.mas.sharingApp.clientSite.persistence.generic.Persistence;
import ch.zhaw.mas.sharingApp.clientSite.persistence.generic.RequestBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * This class is for persisting Items to/from the server.
 *
 * @author Noemi Kälin
 */
@Service
public class ItemPersistence extends Persistence {
    public ItemPersistence() {
        super("items"); // TODO: 11.12.2020 anpassen (items anstatt books)
    }

    public List<ItemToShare> getAllItems() throws JsonProcessingException {
        RequestBuilder requestBuilder = new RequestBuilder();
        HttpEntity<String> response = requestBuilder.httpGetRequest(new HashMap<>(), this.getUrl());
        ObjectMapper objectMapper = new ObjectMapper();
        List<ItemToShare> items = objectMapper.readValue(response.getBody(), List.class);
        return items;
    }

    public void saveNewItem(ItemToShare item){

    }

    public void deleteItem(int id){

    }


    public void updateItem(ItemToShare item) {
    }
}
