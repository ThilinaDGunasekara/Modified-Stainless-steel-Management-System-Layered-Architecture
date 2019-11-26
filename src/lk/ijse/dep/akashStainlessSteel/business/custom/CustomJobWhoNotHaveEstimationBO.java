package lk.ijse.dep.akashStainlessSteel.business.custom;

import lk.ijse.dep.akashStainlessSteel.business.SuperBO;
import lk.ijse.dep.akashStainlessSteel.dto.CustomEstimationDTO;
import lk.ijse.dep.akashStainlessSteel.entity.CustomEstimationEntity;

import java.util.List;

public interface CustomJobWhoNotHaveEstimationBO extends SuperBO {

    List<CustomEstimationDTO> loadJobsNotHaveEstimation() throws Exception;
}
