package test.lk.ijse.dep.akashStainlessSteel.dao.custom.impl;


import lk.ijse.dep.akashStainlessSteel.dao.custom.impl.SearchCustomerDAOImpl;
import lk.ijse.dep.akashStainlessSteel.entity.SearchCustomer;

import java.util.List;

class SearchCustomerDAOImplTest {

    public static void main(String[] args) {
        SearchCustomerDAOImplTest searchCustomerDAOImplTest = new SearchCustomerDAOImplTest();
        searchCustomerDAOImplTest.searchCustomer();
    }
    void searchCustomer() {
        SearchCustomerDAOImpl searchCustomerDAO = new SearchCustomerDAOImpl();
        try {
            List<SearchCustomer> searchCustomers = searchCustomerDAO.searchCustomer("%ha%");
            System.out.println(searchCustomers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}