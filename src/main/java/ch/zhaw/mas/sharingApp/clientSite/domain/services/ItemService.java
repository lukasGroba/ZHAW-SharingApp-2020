package ch.zhaw.mas.sharingApp.clientSite.domain.services;


import ch.zhaw.mas.sharingApp.clientSite.domain.ItemToShare;
import ch.zhaw.mas.sharingApp.clientSite.persistence.ItemPersistence;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Noemi Kälin
 */

@Service
public class ItemService {
    private ItemPersistence itemPersistence = new ItemPersistence();

    public void saveNewItem(ItemToShare item){
        itemPersistence.saveNewItem(item);
    }

    public void deleteItem(int id){
        itemPersistence.deleteItem(id);
    }

    public void updateItem(ItemToShare item){
        itemPersistence.updateItem(item);
    }

    public List<ItemToShare> getAllItems() throws JsonProcessingException {
        return itemPersistence.getAllItems();
    }

}
