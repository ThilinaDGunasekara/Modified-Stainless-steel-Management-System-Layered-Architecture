package test.lk.ijse.dep.akashStainlessSteel.dao;

import lk.ijse.dep.akashStainlessSteel.business.custom.impl.SearchCustomerBOImpl;
import lk.ijse.dep.akashStainlessSteel.dto.SearchCustomerDTO;

import java.util.List;

public class SearchCustomerBOImplTest {
    public static void main(String[] args) throws Exception {
        SearchCustomerBOImplTest searchCustomerBOImplTest = new SearchCustomerBOImplTest();
        searchCustomerBOImplTest.searchCustomer("FRC%");
        searchCustomerBOImplTest.searchCustomerFnr("FNRC%");
    }

    public List<SearchCustomerDTO> searchCustomer(String searchTxt) throws Exception{

        SearchCustomerBOImpl searchCustomerBO = new SearchCustomerBOImpl();
        List<SearchCustomerDTO> searchCustomerDTOS = searchCustomerBO.searchCustomer(searchTxt);
        for (SearchCustomerDTO searchCustomerDTO : searchCustomerDTOS) {
            System.out.println(searchCustomerDTO);
        }
        return searchCustomerDTOS;
    }

    public List<SearchCustomerDTO> searchCustomerFnr(String searchTxt) throws Exception{
        SearchCustomerBOImpl searchCustomerBO = new SearchCustomerBOImpl();
        List<SearchCustomerDTO> searchCustomerDTOS = searchCustomerBO.searchCustomerFnr(searchTxt);
        for (SearchCustomerDTO searchCustomerDTO : searchCustomerDTOS) {
            System.out.println(searchCustomerDTO);
        }
        return searchCustomerDTOS;
    }
}
