package lk.ijse.dep.akashStainlessSteel.business.custom;

import lk.ijse.dep.akashStainlessSteel.business.SuperBO;
import lk.ijse.dep.akashStainlessSteel.dto.SearchCustomerDTO;

import java.util.List;

public interface SearchCustomerBO extends SuperBO {
    public List<SearchCustomerDTO> searchCustomer(String searchTxt) throws Exception;
    public List<SearchCustomerDTO> searchCustomerFnr(String searchTxt) throws Exception;
}
