package lk.ijse.dep.akashStainlessSteel.business.custom;

import lk.ijse.dep.akashStainlessSteel.business.SuperBO;
import lk.ijse.dep.akashStainlessSteel.business.exception.AlreadyExistsInOrderException;
import lk.ijse.dep.akashStainlessSteel.dto.FrCustomerDTO;

import java.util.List;

public interface FrCustomerBO  extends SuperBO {
    boolean saveFrCustomer(FrCustomerDTO frCustomer)throws Exception;

    boolean updateFrCustomer(FrCustomerDTO frCustomer)throws Exception;

    boolean deleteFrCustomer(String frCustomerId) throws Exception;

    List<FrCustomerDTO> findAllFrCustomers() throws Exception;

    String getLastFrCustomerId()throws Exception;

    FrCustomerDTO findFrCustomer(String frCustomerId) throws Exception;

    List<String> getAllFrCustomerIDs() throws Exception;
}
