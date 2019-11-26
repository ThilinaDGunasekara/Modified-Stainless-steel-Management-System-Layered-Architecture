package lk.ijse.dep.akashStainlessSteel.business.custom;

import lk.ijse.dep.akashStainlessSteel.business.SuperBO;
import lk.ijse.dep.akashStainlessSteel.dao.DAOFactory;
import lk.ijse.dep.akashStainlessSteel.dao.DAOTypes;
import lk.ijse.dep.akashStainlessSteel.dao.custom.EstimationDAO;
import lk.ijse.dep.akashStainlessSteel.dao.custom.JobDAO;
import lk.ijse.dep.akashStainlessSteel.dao.custom.WorkerDAO;
import lk.ijse.dep.akashStainlessSteel.dto.WorkerDTO;

import java.util.List;

public interface WorkerBO  extends SuperBO {

    boolean saveWorker(WorkerDTO worker)throws Exception;

    boolean updateWorker(WorkerDTO worker)throws Exception;

    boolean deleteWorker(String workerId) throws Exception;

    List<WorkerDTO> findAllWorkers() throws Exception;

    String getLastWorkerId()throws Exception;

    WorkerDTO findWorker(String workerId) throws Exception;

    List<String> getAllWorkerIDs() throws Exception;

}
