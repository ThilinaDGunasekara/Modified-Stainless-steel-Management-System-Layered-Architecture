package test.lk.ijse.dep.akashStainlessSteel.dao;

import lk.ijse.dep.akashStainlessSteel.dao.custom.impl.SearchCustomerDAOImpl;
import lk.ijse.dep.akashStainlessSteel.entity.SearchCustomer;

import java.util.List;

public class searchCustomerDAOTest {
    public static void main(String[] args) throws Exception {
        searchCustomerDAOTest searchCustomerDAOTest = new searchCustomerDAOTest();
        searchCustomerDAOTest.searchCustomerFnr("FRC%");
        System.out.println("\n\n");
        searchCustomerDAOTest.searchCustomer("FNRC001%");
    }

    public List<SearchCustomer> searchCustomerFnr(String searchTxt) throws Exception{
        SearchCustomerDAOImpl searchCustomerDAO = new SearchCustomerDAOImpl();
        List<SearchCustomer> searchCustomers = searchCustomerDAO.searchCustomer(searchTxt);
        for (SearchCustomer searchCustomer : searchCustomers) {
            System.out.println(searchCustomer);
        }
        return searchCustomers;
    }
    public List<SearchCustomer> searchCustomer(String searchTxt) throws Exception{
        SearchCustomerDAOImpl searchCustomerDAO = new SearchCustomerDAOImpl();
        List<SearchCustomer> searchCustomers = searchCustomerDAO.searchCustomerFnr(searchTxt);
        for (SearchCustomer searchCustomer : searchCustomers) {
            System.out.println(searchCustomer);
        }
        return searchCustomers;
    }

}
