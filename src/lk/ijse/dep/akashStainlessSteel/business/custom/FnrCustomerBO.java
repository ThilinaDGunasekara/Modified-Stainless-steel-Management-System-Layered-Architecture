package lk.ijse.dep.akashStainlessSteel.business.custom;

import lk.ijse.dep.akashStainlessSteel.business.SuperBO;
import lk.ijse.dep.akashStainlessSteel.dto.FnrCustomerDTO;

import java.util.List;

public interface FnrCustomerBO  extends SuperBO {
    boolean saveFnrCustomer(FnrCustomerDTO fnrCustomer)throws Exception;

    boolean updateFnrCustomer(FnrCustomerDTO fnrCustomer)throws Exception;

    boolean deleteFnrCustomer(String fnrCustomerId) throws Exception;

    List<FnrCustomerDTO> findAllFnrCustomers() throws Exception;

    String getLastFnrCustomerId()throws Exception;

    FnrCustomerDTO findFnrCustomer(String fnrCustomerId) throws Exception;

    List<String> getAllFnrCustomerIDs() throws Exception;
}
