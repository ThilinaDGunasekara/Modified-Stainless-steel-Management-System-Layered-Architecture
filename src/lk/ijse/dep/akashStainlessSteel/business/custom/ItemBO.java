package lk.ijse.dep.akashStainlessSteel.business.custom;

import lk.ijse.dep.akashStainlessSteel.business.SuperBO;
import lk.ijse.dep.akashStainlessSteel.dto.ItemDTO;

import java.util.List;

public interface ItemBO  extends SuperBO {
    boolean saveItem(ItemDTO item)throws Exception;

    boolean updateItem(ItemDTO item)throws Exception;

    boolean deleteItem(String itemId) throws Exception;

    List<ItemDTO> findAllItems() throws Exception;

    String getLastItemCode()throws Exception;

    ItemDTO findItem(String itemId) throws Exception;

    List<String> getAllItemIDs() throws Exception;
}
