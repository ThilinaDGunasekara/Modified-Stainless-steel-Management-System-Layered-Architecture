package lk.ijse.dep.akashStainlessSteel.dao.custom;
import lk.ijse.dep.akashStainlessSteel.dao.CrudDAO;
import lk.ijse.dep.akashStainlessSteel.entity.FrCustomer;

public interface FrCustomerDAO extends CrudDAO<FrCustomer,String> {

    public String getLastCustomerId() throws Exception;
}
