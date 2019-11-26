package lk.ijse.dep.akashStainlessSteel.business.custom;

import lk.ijse.dep.akashStainlessSteel.business.SuperBO;
import lk.ijse.dep.akashStainlessSteel.dto.FnrOrderDTO;

import java.util.List;

public interface FnrOrderBO  extends SuperBO {
    boolean saveFnrOrder(FnrOrderDTO fnrOrder)throws Exception;

    boolean updateFnrOrder(FnrOrderDTO fnrOrder)throws Exception;

    boolean deleteFnrOrder(String fnrOrderId) throws Exception;

    List<FnrOrderDTO> findAllFnrOrders() throws Exception;

    String getLastFnrOrderId()throws Exception;

    FnrOrderDTO findFnrOrder(String fnrOrderId) throws Exception;

    List<String> getAllFnrOrderIDs() throws Exception;
}
