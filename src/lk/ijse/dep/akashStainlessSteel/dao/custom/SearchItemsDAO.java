package lk.ijse.dep.akashStainlessSteel.dao.custom;

import lk.ijse.dep.akashStainlessSteel.dao.SuperDAO;
import lk.ijse.dep.akashStainlessSteel.entity.Item;

import java.util.List;

public interface SearchItemsDAO extends SuperDAO {
    List<Item> searchItem(String search) throws Exception;
}
