package lk.ijse.dep.akashStainlessSteel.business.custom;

import lk.ijse.dep.akashStainlessSteel.business.SuperBO;
import lk.ijse.dep.akashStainlessSteel.dto.OrderDetailDTO;
import lk.ijse.dep.akashStainlessSteel.entity.OrderDetailPK;

import java.util.List;

public interface FrOrderDetailBO extends SuperBO {

    public boolean saveFrOrderDetail(OrderDetailDTO frOrder) throws Exception;
    public boolean updateFrOrderDetail(OrderDetailDTO frOrder) throws Exception;
    public List<OrderDetailDTO> findAllFrOrderDetail() throws Exception;
    public OrderDetailDTO findFrOrderDetail(OrderDetailPK fnrOrderId) throws Exception;
}
