package ch.zhaw.mas.sharingApp.clientSite.domain.services;


import ch.zhaw.mas.sharingApp.clientSite.domain.ItemToShare;
import ch.zhaw.mas.sharingApp.clientSite.persistence.ItemPersistence;
import ch.zhaw.mas.sharingApp.clientSite.persistence.generic.BackendError;
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

    public void saveNewItem(ItemToShare item) throws BackendError {
        itemPersistence.saveNewItem(item);
    }

    public void deleteItem(int id) throws BackendError {
        itemPersistence.deleteItem(id);
    }

    public void updateItem(ItemToShare item) throws BackendError {
        itemPersistence.updateItem(item);
    }

    public List<ItemToShare> getAllItems() throws JsonProcessingException, BackendError {
        return itemPersistence.getAllItems();
    }

}
