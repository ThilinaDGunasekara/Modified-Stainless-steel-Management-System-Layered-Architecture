package lk.ijse.dep.akashStainlessSteel.business.custom.impl;

import lk.ijse.dep.akashStainlessSteel.business.custom.SearchOrdersBO;
import lk.ijse.dep.akashStainlessSteel.dao.DAOFactory;
import lk.ijse.dep.akashStainlessSteel.dao.DAOTypes;
import lk.ijse.dep.akashStainlessSteel.dao.custom.QueryDAO;
import lk.ijse.dep.akashStainlessSteel.dto.SearchOrderDTO;
import lk.ijse.dep.akashStainlessSteel.entity.SearchOrderEntity;

import java.util.ArrayList;
import java.util.List;

public class SearchOrdersBOImpl implements SearchOrdersBO {

    QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOTypes.QUERY);

    @Override
    public List<SearchOrderDTO> searchOrder(String searchText) throws Exception {
        List<SearchOrderEntity> searchOrderEntities = queryDAO.searchOrder(searchText);
        List<SearchOrderDTO> searchOrderDTOS = new ArrayList<>();

        for (SearchOrderEntity searchOrderEntity : searchOrderEntities) {
            searchOrderDTOS.add(new SearchOrderDTO(
                    searchOrderEntity.getOrderId(),
                    searchOrderEntity.getDate(),
                    searchOrderEntity.getCustomerId(),
                    searchOrderEntity.getName(),
                    searchOrderEntity.getTotal(),
                    searchOrderEntity.getRemainingPayment()
            ));
        }
        return searchOrderDTOS;
    }

    @Override
    public List<SearchOrderDTO> searchOrder2(String searchText) throws Exception {
        List<SearchOrderEntity> searchOrderEntities = queryDAO.searchOrder2(searchText);
        List<SearchOrderDTO> searchOrderDTOS = new ArrayList<>();

        for (SearchOrderEntity searchOrderEntity : searchOrderEntities) {
            searchOrderDTOS.add(new SearchOrderDTO(
                    searchOrderEntity.getOrderId(),
                    searchOrderEntity.getDate(),
                    searchOrderEntity.getCustomerId(),
                    searchOrderEntity.getName(),
                    searchOrderEntity.getTotal()
            ));
        }
        return searchOrderDTOS;
    }

    @Override
    public List<SearchOrderDTO> findAllOrdersInFRC() throws Exception {
        List<SearchOrderEntity> allOrdersInFRC = queryDAO.findAllOrdersInFRC();
        List<SearchOrderDTO> searchOrderDTOS = new ArrayList<>();

        for (SearchOrderEntity searchOrderEntity : allOrdersInFRC) {
            searchOrderDTOS.add(new SearchOrderDTO(
                    searchOrderEntity.getOrderId(),
                    searchOrderEntity.getDate(),
                    searchOrderEntity.getCustomerId(),
                    searchOrderEntity.getName(),
                    searchOrderEntity.getTotal()
            ));
        }
        return searchOrderDTOS;
    }

    @Override
    public List<SearchOrderDTO> findAllOrdersInFNRC() throws Exception {
        List<SearchOrderEntity> allOrdersInFNRC = queryDAO.findAllOrdersInFNRC();
        List<SearchOrderDTO> searchOrderDTOS = new ArrayList<>();

        for (SearchOrderEntity searchOrderEntity : allOrdersInFNRC) {
            searchOrderDTOS.add(new SearchOrderDTO(
                    searchOrderEntity.getOrderId(),
                    searchOrderEntity.getDate(),
                    searchOrderEntity.getCustomerId(),
                    searchOrderEntity.getName(),
                    searchOrderEntity.getTotal(),
                    searchOrderEntity.getRemainingPayment()
            ));
        }
        return searchOrderDTOS;
    }
}
