package lk.ijse.dep.akashStainlessSteel.dao.custom.impl;

import lk.ijse.dep.akashStainlessSteel.dao.CrudUtil;
import lk.ijse.dep.akashStainlessSteel.dao.custom.SearchItemsDAO;
import lk.ijse.dep.akashStainlessSteel.entity.Item;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SearchItemDAOImpl implements SearchItemsDAO {

    @Override
    public List<Item> searchItem(String search) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM item WHERE itemCode LIKE ? OR description LIKE ? OR unitPrice LIKE ? OR qtyOnHand LIKE ?",search,search,search,search);
        List<Item> items = new ArrayList<>();
        while (resultSet.next()){
            items.add(new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            ));
        }
        return items;
    }
}
