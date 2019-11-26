package lk.ijse.dep.akashStainlessSteel.business.custom.impl;
import lk.ijse.dep.akashStainlessSteel.business.custom.ItemBO;
import lk.ijse.dep.akashStainlessSteel.business.exception.AlreadyExistsInEstimationDetailOrOrderDetailException;
import lk.ijse.dep.akashStainlessSteel.dao.DAOFactory;
import lk.ijse.dep.akashStainlessSteel.dao.DAOTypes;
import lk.ijse.dep.akashStainlessSteel.dao.custom.EstimationDetailDAO;
import lk.ijse.dep.akashStainlessSteel.dao.custom.ItemDAO;
import lk.ijse.dep.akashStainlessSteel.dao.custom.OrderDetailDAO;
import lk.ijse.dep.akashStainlessSteel.dto.ItemDTO;
import lk.ijse.dep.akashStainlessSteel.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOTypes.ITEM);
    OrderDetailDAO orderDetailDAO = DAOFactory.getInstance().getDAO(DAOTypes.ORDER_DETAIL);
    EstimationDetailDAO estimationDetailDAO = DAOFactory.getInstance().getDAO(DAOTypes.ESTIMATION_DETAIL);


    @Override
    public boolean saveItem(ItemDTO item) throws Exception {
        return itemDAO.save(new Item(
                item.getItemCode(),
                item.getDescription(),
                item.getUnitPrice(),
                item.getQtyOnHand()
        ));
    }

    @Override
    public boolean updateItem(ItemDTO item) throws Exception {
        return itemDAO.update(new Item(
                item.getItemCode(),
                item.getDescription(),
                item.getUnitPrice(),
                item.getQtyOnHand()
        ));
    }

    @Override
    public boolean deleteItem(String itemId) throws Exception {
        if(orderDetailDAO.existsByItemCode(itemId) || estimationDetailDAO.existsByItemCode(itemId)){
            throw new AlreadyExistsInEstimationDetailOrOrderDetailException("Item already exists in OrderDetail or EstimationDetail, hence unable to delete");
        }
        return itemDAO.delete(itemId);
    }

    @Override
    public List<ItemDTO> findAllItems() throws Exception {
        List<Item> all = itemDAO.findAll();
        List<ItemDTO> allItems =new ArrayList<>();
        for (Item item : all) {
            allItems.add(new ItemDTO(
                    item.getItemCode(),
                    item.getDescription(),
                    item.getUnitPrice(),
                    item.getQtyOnHand()
            ));
        }
        return allItems;
    }

    @Override
    public String getLastItemCode() throws Exception {
        return itemDAO.getLastItemCode();
    }

    @Override
    public ItemDTO findItem(String itemId) throws Exception {
        Item item = itemDAO.find(itemId);
        return new ItemDTO(
                item.getItemCode(),
                item.getDescription(),
                item.getUnitPrice(),
                item.getQtyOnHand()
        );
    }

    @Override
    public List<String> getAllItemIDs() throws Exception {
        List<Item> all = itemDAO.findAll();
        List<String> allItemIDs = new ArrayList<>();
        for (Item item : all) {
            allItemIDs.add(item.getItemCode());
        }
        return allItemIDs;
    }
}
