package lk.ijse.dep.akashStainlessSteel.business.custom.impl;

import lk.ijse.dep.akashStainlessSteel.business.custom.EstimationBO;
import lk.ijse.dep.akashStainlessSteel.dao.DAOFactory;
import lk.ijse.dep.akashStainlessSteel.dao.DAOTypes;
import lk.ijse.dep.akashStainlessSteel.dao.custom.EstimationDAO;
import lk.ijse.dep.akashStainlessSteel.dto.EstimationDTO;
import lk.ijse.dep.akashStainlessSteel.entity.Estimation;

import java.util.ArrayList;
import java.util.List;

public class EstimationBOImpl implements EstimationBO {

    EstimationDAO estimationDAO = DAOFactory.getInstance().getDAO(DAOTypes.ESTIMATION);

    @Override
    public boolean saveEstimation(EstimationDTO estimation) throws Exception {
        return estimationDAO.save(new Estimation(
                estimation.getEstimationNo(),
                estimation.getJobId(),
                estimation.getWorkerId(),
                estimation.getPrice()
        ));
    }

    @Override
    public boolean updateEstimation(EstimationDTO estimation) throws Exception {
        return  estimationDAO.update(new Estimation(estimation.getEstimationNo(),estimation.getJobId(),estimation.getWorkerId(),estimation.getPrice()));
    }

    @Override
    public boolean deleteEstimation(String estimationNo) throws Exception {
        return false;
    }

    @Override
    public List<EstimationDTO> findAllEstimations() throws Exception {
        List<Estimation> alEstimation = estimationDAO.findAll();
        List<EstimationDTO> alEstimationFromDTO = new ArrayList<>();
        for (Estimation estimation : alEstimation) {
            alEstimationFromDTO.add(new EstimationDTO(estimation.getEstimationNo(),estimation.getJobId(),estimation.getWorkerId(),estimation.getPrice()));
        }
        return alEstimationFromDTO;
    }

    @Override
    public String getLastEstimationId() throws Exception {
        return estimationDAO.getLastEstimationNo();
    }

    @Override
    public EstimationDTO findEstimation(String estimationNo) throws Exception {
        Estimation estimation = estimationDAO.find(estimationNo);

        return new EstimationDTO(
                estimation.getEstimationNo(),
                estimation.getJobId(),
                estimation.getWorkerId(),
                estimation.getPrice()
        );

    }

    @Override
    public List<String> getAllEstimationIDs() throws Exception {
        List<Estimation> alEstimation = estimationDAO.findAll();
        List<String> alEstimationIDs = new ArrayList<>();
        for (Estimation estimation : alEstimation) {
            alEstimationIDs.add(estimation.getEstimationNo());
        }
        return alEstimationIDs;
    }


}
