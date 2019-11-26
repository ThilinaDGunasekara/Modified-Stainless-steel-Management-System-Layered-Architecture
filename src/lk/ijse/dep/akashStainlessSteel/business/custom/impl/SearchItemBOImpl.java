package lk.ijse.dep.akashStainlessSteel.business.custom.impl;

import lk.ijse.dep.akashStainlessSteel.business.custom.SearchItemBO;
import lk.ijse.dep.akashStainlessSteel.dao.DAOFactory;
import lk.ijse.dep.akashStainlessSteel.dao.DAOTypes;
import lk.ijse.dep.akashStainlessSteel.dao.custom.SearchItemsDAO;
import lk.ijse.dep.akashStainlessSteel.dto.ItemDTO;
import lk.ijse.dep.akashStainlessSteel.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class SearchItemBOImpl implements SearchItemBO {
    SearchItemsDAO searchItemsDAO = DAOFactory.getInstance().getDAO(DAOTypes.SEARCH_ITEM);

    @Override
    public List<ItemDTO> searchItem(String searchTxt) throws Exception {
        List<Item> items = searchItemsDAO.searchItem(searchTxt);
        List<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : items) {
            itemDTOS.add(new ItemDTO(
                    item.getItemCode(),
                    item.getDescription(),
                    item.getUnitPrice(),
                    item.getQtyOnHand()
            ));
        }
        return itemDTOS;
    }
}
