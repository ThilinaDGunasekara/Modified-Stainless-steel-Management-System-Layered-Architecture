package lk.ijse.dep.akashStainlessSteel.business.custom;

import lk.ijse.dep.akashStainlessSteel.business.SuperBO;
import lk.ijse.dep.akashStainlessSteel.dto.FnrOrderDTO;
import lk.ijse.dep.akashStainlessSteel.dto.FrOrderDTO;

import java.util.List;

public interface FrOrderBO  extends SuperBO {

    String getLastFrOrderId()throws Exception;
    public boolean saveFrOrder(FrOrderDTO frOrder) throws Exception;
    public boolean updateFrOrder(FrOrderDTO frOrder) throws Exception;
    public boolean deleteFrOrder(String frOrderId) throws Exception;
    public List<FrOrderDTO> findAllFrOrders() throws Exception;
    public FrOrderDTO findFrOrder(String fnrOrderId) throws Exception;

}
