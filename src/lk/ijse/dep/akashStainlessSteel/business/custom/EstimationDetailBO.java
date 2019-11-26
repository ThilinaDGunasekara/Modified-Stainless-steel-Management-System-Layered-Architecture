package lk.ijse.dep.akashStainlessSteel.business.custom;

import lk.ijse.dep.akashStainlessSteel.business.SuperBO;
import lk.ijse.dep.akashStainlessSteel.dto.EstimationDTO;
import lk.ijse.dep.akashStainlessSteel.dto.EstimationDetailDTO;

import java.util.List;

public interface EstimationDetailBO extends SuperBO {
    boolean saveEstimationDetail(EstimationDetailDTO estimationDetailDTO)throws Exception;

    boolean updateEstimationDetail(EstimationDetailDTO estimationDetailDTO)throws Exception;

    List<EstimationDetailDTO> findAllEstimationsDetail() throws Exception;

    EstimationDetailDTO findEstimation(String estimationNo) throws Exception;

}
