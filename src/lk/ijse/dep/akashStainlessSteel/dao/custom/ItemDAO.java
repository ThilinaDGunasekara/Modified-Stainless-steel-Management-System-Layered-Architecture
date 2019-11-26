package lk.ijse.dep.akashStainlessSteel.dao.custom;
import lk.ijse.dep.akashStainlessSteel.dao.CrudDAO;
import lk.ijse.dep.akashStainlessSteel.entity.Item;


public interface ItemDAO extends CrudDAO<Item,String> {

    public String getLastItemCode() throws Exception;
}
