package lk.ijse.dep.akashStainlessSteel.dao.custom;
import lk.ijse.dep.akashStainlessSteel.dao.CrudDAO;
import lk.ijse.dep.akashStainlessSteel.entity.FrOrder;

public interface FrOrderDAO extends CrudDAO<FrOrder,String> {

    public String getLastOrderId() throws Exception;
    boolean existsByCustomerId(String customerId) throws Exception;

}
