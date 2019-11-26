package lk.ijse.dep.akashStainlessSteel.dao.custom;

import lk.ijse.dep.akashStainlessSteel.dao.SuperDAO;
import lk.ijse.dep.akashStainlessSteel.entity.SearchCustomer;

import java.util.List;

public interface SearchCustomerDAO extends SuperDAO {
    List<SearchCustomer> searchCustomer(String search) throws Exception;
    public List<SearchCustomer> searchCustomerFnr(String searchTxt) throws Exception;
}
