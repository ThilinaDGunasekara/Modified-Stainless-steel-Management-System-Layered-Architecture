package lk.ijse.dep.akashStainlessSteel.business.custom.impl;
import lk.ijse.dep.akashStainlessSteel.business.custom.FrOrderBO;
import lk.ijse.dep.akashStainlessSteel.dao.DAOFactory;
import lk.ijse.dep.akashStainlessSteel.dao.DAOTypes;
import lk.ijse.dep.akashStainlessSteel.dao.custom.FrOrderDAO;
import lk.ijse.dep.akashStainlessSteel.dao.custom.ItemDAO;
import lk.ijse.dep.akashStainlessSteel.dao.custom.OrderDetailDAO;
import lk.ijse.dep.akashStainlessSteel.dto.FrOrderDTO;
import lk.ijse.dep.akashStainlessSteel.entity.FrOrder;

import java.util.ArrayList;
import java.util.List;


public class FrOrderBOImpl implements FrOrderBO {

    private FrOrderDAO frOrderDAO = DAOFactory.getInstance().getDAO(DAOTypes.FR_ORDER);
    private OrderDetailDAO orderDetailDAO = DAOFactory.getInstance().getDAO(DAOTypes.ORDER_DETAIL);
    private ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOTypes.ITEM);

    @Override
    public String getLastFrOrderId() throws Exception {
        return frOrderDAO.getLastOrderId();
    }

    @Override
    public boolean saveFrOrder(FrOrderDTO frOrder) throws Exception {

        return frOrderDAO.save(new FrOrder(
              frOrder.getOrderId(),
              frOrder.getDate(),
              frOrder.getCustomerId()
        ));

    }

    @Override
    public boolean updateFrOrder(FrOrderDTO fnrOrder) throws Exception {
        return frOrderDAO.update(new FrOrder(
                fnrOrder.getOrderId(),
                fnrOrder.getDate(),
                fnrOrder.getCustomerId()
        ));
    }

    @Override
    public boolean deleteFrOrder(String frOrderId) throws Exception {
        return frOrderDAO.delete(frOrderId);
    }

    @Override
    public List<FrOrderDTO> findAllFrOrders() throws Exception {
        List<FrOrder> all = frOrderDAO.findAll();
        List<FrOrderDTO> frOrderDTOS = new ArrayList<>();

        for (FrOrder frOrder : all) {
            frOrderDTOS.add(new FrOrderDTO(
                    frOrder.getOrderId(),
                    frOrder.getDate(),
                    frOrder.getCustomerId()
            ));
        }

        return frOrderDTOS;
    }

    @Override
    public FrOrderDTO findFrOrder(String fnrOrderId) throws Exception {
        FrOrder frOrder = frOrderDAO.find(fnrOrderId);
        FrOrderDTO frOrderDTO = new FrOrderDTO(
                frOrder.getOrderId(),
                frOrder.getDate(),
                frOrder.getCustomerId()
        );
        return frOrderDTO;
    }
}
