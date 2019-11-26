package lk.ijse.dep.akashStainlessSteel.business.custom;

import lk.ijse.dep.akashStainlessSteel.business.SuperBO;
import lk.ijse.dep.akashStainlessSteel.dto.EstimationDTO;

import java.util.List;

public interface EstimationBO extends SuperBO {
    boolean saveEstimation(EstimationDTO estimation)throws Exception;

    boolean updateEstimation(EstimationDTO estimation)throws Exception;

    boolean deleteEstimation(String estimationNo) throws Exception;

    List<EstimationDTO> findAllEstimations() throws Exception;

    String getLastEstimationId()throws Exception;

    EstimationDTO findEstimation(String estimationNo) throws Exception;

    List<String> getAllEstimationIDs() throws Exception;
}
