package ch.zhaw.mas.sharingApp.clientSite.persistence;

import ch.zhaw.mas.sharingApp.clientSite.domain.ItemToShare;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPersistence extends Persistence{
    public ItemPersistence() {
        super("books/test"); // TODO: 11.12.2020 anpassen (items anstatt books)
    }

    public List<ItemToShare> getAllItems(){
        return null;
    }

    public void saveNewItem(ItemToShare item){

    }

    public void deleteItem(int id){

    }


    public void updateItem(ItemToShare item) {
    }
}
