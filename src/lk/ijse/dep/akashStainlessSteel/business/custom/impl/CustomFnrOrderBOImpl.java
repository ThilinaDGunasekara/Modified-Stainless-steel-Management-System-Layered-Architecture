package lk.ijse.dep.akashStainlessSteel.business.custom.impl;
import lk.ijse.dep.akashStainlessSteel.business.custom.CustomFnrOrderBO;
import lk.ijse.dep.akashStainlessSteel.dao.DAOFactory;
import lk.ijse.dep.akashStainlessSteel.dao.DAOTypes;
import lk.ijse.dep.akashStainlessSteel.dao.custom.QueryDAO;
import lk.ijse.dep.akashStainlessSteel.dto.CustomFnrOrderDTO;
import lk.ijse.dep.akashStainlessSteel.entity.CustomFnrOrderEntity;

import java.util.ArrayList;
import java.util.List;

public class CustomFnrOrderBOImpl implements CustomFnrOrderBO {

    QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOTypes.QUERY);

    @Override
    public List<CustomFnrOrderDTO> loadAllDataToFnrOrder() throws Exception {

        List<CustomFnrOrderEntity> customFnrOrderEntities = queryDAO.loadAllDataToFnrOrder();
        List<CustomFnrOrderDTO> customFnrOrderDTOS = new ArrayList<>();

        for (CustomFnrOrderEntity customFnrOrderEntity : customFnrOrderEntities) {
            customFnrOrderDTOS.add(new CustomFnrOrderDTO(
                    customFnrOrderEntity.getJobId(),
                    customFnrOrderEntity.getCustomerId(),
                    customFnrOrderEntity.getName(),
                    customFnrOrderEntity.getPrice()
            ));
        }
        return customFnrOrderDTOS;
    }
}
