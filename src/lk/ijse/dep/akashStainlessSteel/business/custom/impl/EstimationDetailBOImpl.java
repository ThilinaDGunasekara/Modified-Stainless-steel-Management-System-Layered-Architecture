package lk.ijse.dep.akashStainlessSteel.business.custom.impl;
import lk.ijse.dep.akashStainlessSteel.business.custom.EstimationDetailBO;
import lk.ijse.dep.akashStainlessSteel.dao.DAOFactory;
import lk.ijse.dep.akashStainlessSteel.dao.DAOTypes;
import lk.ijse.dep.akashStainlessSteel.dao.custom.EstimationDetailDAO;
import lk.ijse.dep.akashStainlessSteel.dto.EstimationDetailDTO;
import lk.ijse.dep.akashStainlessSteel.entity.EstimationDetail;

import java.util.ArrayList;
import java.util.List;

public class EstimationDetailBOImpl implements EstimationDetailBO {


    EstimationDetailDAO estimationDetailDAO = DAOFactory.getInstance().getDAO(DAOTypes.ESTIMATION_DETAIL);
    @Override
    public boolean saveEstimationDetail(EstimationDetailDTO estimationDetailDTO) throws Exception {

        return estimationDetailDAO.save(new EstimationDetail(
                estimationDetailDTO.getEstimationNo(),
                estimationDetailDTO.getItemCode(),
                estimationDetailDTO.getEiQuantity(),
                estimationDetailDTO.getUnitPrice()
        ));
    }

    @Override
    public boolean updateEstimationDetail(EstimationDetailDTO estimationDetailDTO) throws Exception {
        return estimationDetailDAO.update(new EstimationDetail(
                estimationDetailDTO.getEstimationNo(),
                estimationDetailDTO.getItemCode(),
                estimationDetailDTO.getEiQuantity(),
                estimationDetailDTO.getUnitPrice()
        ));
    }

    @Override
    public List<EstimationDetailDTO> findAllEstimationsDetail() throws Exception {

        List<EstimationDetail> all = estimationDetailDAO.findAll();
        List<EstimationDetailDTO> estimationDetailDTOS = new ArrayList<>();

        for (EstimationDetail estimationDetail : all) {
            estimationDetailDTOS.add(new EstimationDetailDTO(
                    estimationDetail.getEstimationDetailPK().getEstimationNo(),
                    estimationDetail.getEstimationDetailPK().getItemCode(),
                    estimationDetail.getEiQuantity(),
                    estimationDetail.getUnitPrice()
            ));
        }
        return estimationDetailDTOS;
    }

    @Override
    public EstimationDetailDTO findEstimation(String estimationNo) throws Exception {

        EstimationDetail estimationDetail = estimationDetailDAO.findEstimationDetail(estimationNo);

        return new EstimationDetailDTO(
                estimationDetail.getEstimationDetailPK().getEstimationNo(),
                estimationDetail.getEstimationDetailPK().getItemCode(),
                estimationDetail.getEiQuantity(),
                estimationDetail.getUnitPrice()
        );
    }
}
