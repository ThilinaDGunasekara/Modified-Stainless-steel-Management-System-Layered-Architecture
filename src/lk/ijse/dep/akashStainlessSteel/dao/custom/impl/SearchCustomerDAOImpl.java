package lk.ijse.dep.akashStainlessSteel.dao.custom.impl;

import lk.ijse.dep.akashStainlessSteel.dao.CrudUtil;
import lk.ijse.dep.akashStainlessSteel.dao.custom.SearchCustomerDAO;
import lk.ijse.dep.akashStainlessSteel.entity.SearchCustomer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SearchCustomerDAOImpl implements SearchCustomerDAO {

    @Override
    public List<SearchCustomer> searchCustomer(String searchTxt) throws Exception {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM frCustomer WHERE customerId LIKE ? OR name LIKE  ? OR address LIKE ? OR contactNo LIKE ?",searchTxt,searchTxt,searchTxt,searchTxt);
        List<SearchCustomer> searchCustomers = new ArrayList<>();
        while (resultSet.next()){
            searchCustomers.add(new SearchCustomer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return searchCustomers;
    }

    @Override
    public List<SearchCustomer> searchCustomerFnr(String searchTxt) throws Exception {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM fnrCustomer WHERE customerId LIKE ? OR name LIKE  ? OR address LIKE ? OR contactNo LIKE ?",searchTxt,searchTxt,searchTxt,searchTxt);
        List<SearchCustomer> searchCustomers = new ArrayList<>();
        while (resultSet.next()){
            searchCustomers.add(new SearchCustomer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return searchCustomers;
    }
}
