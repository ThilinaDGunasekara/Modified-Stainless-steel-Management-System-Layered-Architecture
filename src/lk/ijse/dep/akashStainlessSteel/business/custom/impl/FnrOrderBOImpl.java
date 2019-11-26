package lk.ijse.dep.akashStainlessSteel.business.custom.impl;

import lk.ijse.dep.akashStainlessSteel.business.custom.FnrOrderBO;
import lk.ijse.dep.akashStainlessSteel.dao.DAOFactory;
import lk.ijse.dep.akashStainlessSteel.dao.DAOTypes;
import lk.ijse.dep.akashStainlessSteel.dao.custom.FnrOrderDAO;
import lk.ijse.dep.akashStainlessSteel.dto.FnrOrderDTO;
import lk.ijse.dep.akashStainlessSteel.entity.FnrOrder;

import java.util.ArrayList;
import java.util.List;

public class FnrOrderBOImpl implements FnrOrderBO {

    FnrOrderDAO fnrOrderDAO = DAOFactory.getInstance().getDAO(DAOTypes.FNR_ORDER);


    @Override
    public boolean saveFnrOrder(FnrOrderDTO fnrOrder) throws Exception {
        return fnrOrderDAO.save(new FnrOrder(
                fnrOrder.getOrderId(),
                fnrOrder.getDate(),
                fnrOrder.getJobId(),
                fnrOrder.getPrice(),
                fnrOrder.getAdvance()
        ));
    }

    @Override
    public boolean updateFnrOrder(FnrOrderDTO fnrOrder) throws Exception {
        return fnrOrderDAO.update(new FnrOrder(
                fnrOrder.getOrderId(),
                fnrOrder.getDate(),
                fnrOrder.getJobId(),
                fnrOrder.getPrice(),
                fnrOrder.getAdvance()
        ));
    }

    @Override
    public boolean deleteFnrOrder(String fnrOrderId) throws Exception {
        return fnrOrderDAO.delete(fnrOrderId);
    }

    @Override
    public List<FnrOrderDTO> findAllFnrOrders() throws Exception {
        List<FnrOrderDTO> fnrOrderDTOS = new ArrayList<>();
        List<FnrOrder> all = fnrOrderDAO.findAll();
        for (FnrOrder fnrOrder : all) {
            fnrOrderDTOS.add(new FnrOrderDTO(
                    fnrOrder.getOrderId(),
                    fnrOrder.getDate(),
                    fnrOrder.getJobId(),
                    fnrOrder.getPrice(),
                    fnrOrder.getAdvance()
            ));
        }
        return fnrOrderDTOS;
    }

    @Override
    public String getLastFnrOrderId() throws Exception {
        return fnrOrderDAO.getLastOrderId();
    }

    @Override
    public FnrOrderDTO findFnrOrder(String fnrOrderId) throws Exception {
        FnrOrder fnrOrder = fnrOrderDAO.find(fnrOrderId);
        return new FnrOrderDTO(
                fnrOrder.getOrderId(),
                fnrOrder.getDate(),
                fnrOrder.getJobId(),
                fnrOrder.getPrice(),
                fnrOrder.getAdvance()
        );
    }

    @Override
    public List<String> getAllFnrOrderIDs() throws Exception {
        List<FnrOrder> all = fnrOrderDAO.findAll();
        List<String> getAlOrderIDs = new ArrayList<>();
        for (FnrOrder fnrOrder : all) {
            getAlOrderIDs.add(fnrOrder.getOrderId());
        }
        return getAlOrderIDs;
    }
}
