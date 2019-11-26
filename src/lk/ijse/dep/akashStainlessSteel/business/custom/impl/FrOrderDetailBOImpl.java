package lk.ijse.dep.akashStainlessSteel.business.custom.impl;

import lk.ijse.dep.akashStainlessSteel.business.custom.FrOrderDetailBO;
import lk.ijse.dep.akashStainlessSteel.dao.DAOFactory;
import lk.ijse.dep.akashStainlessSteel.dao.DAOTypes;
import lk.ijse.dep.akashStainlessSteel.dao.custom.OrderDetailDAO;
import lk.ijse.dep.akashStainlessSteel.dto.OrderDetailDTO;
import lk.ijse.dep.akashStainlessSteel.entity.OrderDetail;
import lk.ijse.dep.akashStainlessSteel.entity.OrderDetailPK;

import java.util.ArrayList;
import java.util.List;

public class FrOrderDetailBOImpl implements FrOrderDetailBO {

    OrderDetailDAO orderDetailDAO = DAOFactory.getInstance().getDAO(DAOTypes.ORDER_DETAIL);


    @Override
    public boolean saveFrOrderDetail(OrderDetailDTO frOrder) throws Exception {
        return orderDetailDAO.save(new OrderDetail(
                frOrder.getOrderId(),
                frOrder.getItemCode(),
                frOrder.getUnitPrice(),
                frOrder.getQuantity()
        ));
    }

    @Override
    public boolean updateFrOrderDetail(OrderDetailDTO frOrder) throws Exception {
        return orderDetailDAO.update(new OrderDetail(
                frOrder.getOrderId(),
                frOrder.getItemCode(),
                frOrder.getUnitPrice(),
                frOrder.getQuantity()
        ));
    }

    @Override
    public List<OrderDetailDTO> findAllFrOrderDetail() throws Exception {
        List<OrderDetail> all = orderDetailDAO.findAll();
        List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();

        for (OrderDetail orderDetail : all) {
            orderDetailDTOS.add(new OrderDetailDTO(
                    orderDetail.getOrderDetailPK().getOrderId(),
                    orderDetail.getOrderDetailPK().getItemCode(),
                    orderDetail.getUnitPrice(),
                    orderDetail.getQuantity()
            ));
        }
        return orderDetailDTOS;
    }

    @Override
    public OrderDetailDTO findFrOrderDetail(OrderDetailPK fnrOrderId) throws Exception {
        OrderDetail orderDetail = orderDetailDAO.find(fnrOrderId);

        return new OrderDetailDTO(
                orderDetail.getOrderDetailPK().getOrderId(),
                orderDetail.getOrderDetailPK().getItemCode(),
                orderDetail.getUnitPrice(),
                orderDetail.getQuantity()
        );
    }
}
