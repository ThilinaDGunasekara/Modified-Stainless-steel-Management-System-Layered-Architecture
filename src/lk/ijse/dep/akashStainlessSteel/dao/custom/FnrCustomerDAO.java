package lk.ijse.dep.akashStainlessSteel.dao.custom;

import lk.ijse.dep.akashStainlessSteel.dao.CrudDAO;
import lk.ijse.dep.akashStainlessSteel.entity.EstimationDetail;
import lk.ijse.dep.akashStainlessSteel.entity.FnrCustomer;

import java.util.List;

public interface FnrCustomerDAO extends CrudDAO<FnrCustomer,String> {
    public String getLastCustomerId() throws Exception;
}
