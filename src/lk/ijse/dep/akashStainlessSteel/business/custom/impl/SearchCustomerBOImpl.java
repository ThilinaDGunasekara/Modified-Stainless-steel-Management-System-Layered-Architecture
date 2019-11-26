package lk.ijse.dep.akashStainlessSteel.business.custom.impl;

import lk.ijse.dep.akashStainlessSteel.business.custom.SearchCustomerBO;
import lk.ijse.dep.akashStainlessSteel.dao.DAOFactory;
import lk.ijse.dep.akashStainlessSteel.dao.DAOTypes;
import lk.ijse.dep.akashStainlessSteel.dao.custom.SearchCustomerDAO;
import lk.ijse.dep.akashStainlessSteel.dto.SearchCustomerDTO;
import lk.ijse.dep.akashStainlessSteel.entity.SearchCustomer;

import java.util.ArrayList;
import java.util.List;

public class SearchCustomerBOImpl implements SearchCustomerBO {
    SearchCustomerDAO searchCustomerDAO = DAOFactory.getInstance().getDAO(DAOTypes.SEARCH_CUSTOMER);

    @Override
    public List<SearchCustomerDTO> searchCustomer(String searchTxt) throws Exception {

        List<SearchCustomerDTO> searchCustomerDTOS = new ArrayList<>();
        List<SearchCustomer> searchCustomers = searchCustomerDAO.searchCustomer(searchTxt);
        for (SearchCustomer searchCustomer : searchCustomers) {
            searchCustomerDTOS.add(new SearchCustomerDTO(
                    searchCustomer.getCustomerId(),
                    searchCustomer.getName(),
                    searchCustomer.getAddress(),
                    searchCustomer.getContactNo()
            ));
        }
        return searchCustomerDTOS;
    }

    @Override
    public List<SearchCustomerDTO> searchCustomerFnr(String searchTxt) throws Exception {

        List<SearchCustomerDTO> searchCustomerDTOS =new ArrayList<>();
        List<SearchCustomer> searchCustomers = searchCustomerDAO.searchCustomerFnr(searchTxt);

        for (SearchCustomer searchCustomer : searchCustomers) {
            searchCustomerDTOS.add(new SearchCustomerDTO(
                    searchCustomer.getCustomerId(),
                    searchCustomer.getName(),
                    searchCustomer.getAddress(),
                    searchCustomer.getContactNo()
            ));
        }
        return searchCustomerDTOS;
    }
}
