package lk.ijse.dep.akashStainlessSteel.business.custom.impl;

import lk.ijse.dep.akashStainlessSteel.business.custom.CustomJobWhoNotHaveEstimationBO;
import lk.ijse.dep.akashStainlessSteel.dao.DAOFactory;
import lk.ijse.dep.akashStainlessSteel.dao.DAOTypes;
import lk.ijse.dep.akashStainlessSteel.dao.custom.QueryDAO;
import lk.ijse.dep.akashStainlessSteel.dto.CustomEstimationDTO;
import lk.ijse.dep.akashStainlessSteel.entity.CustomEstimationEntity;

import java.util.ArrayList;
import java.util.List;

public class CustomJobWhoNotHaveEstimationImpl implements CustomJobWhoNotHaveEstimationBO {
    QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOTypes.QUERY);

    @Override
    public List<CustomEstimationDTO> loadJobsNotHaveEstimation() throws Exception {
        List<CustomEstimationDTO> customEstimationDTOS = new ArrayList<>();
        List<CustomEstimationEntity> customEstimationEntityList = queryDAO.loadJobsNotHaveEstimation();
        for (CustomEstimationEntity customEstimationEntity : customEstimationEntityList) {
            customEstimationDTOS.add(new CustomEstimationDTO(
                    customEstimationEntity.getJobId(),
                    customEstimationEntity.getDescription()
            ));
        }
        return customEstimationDTOS;
    }
}
