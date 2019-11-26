package lk.ijse.dep.akashStainlessSteel.business.custom.impl;

import lk.ijse.dep.akashStainlessSteel.business.custom.WorkerBO;
import lk.ijse.dep.akashStainlessSteel.business.exception.AlreadyExistsInWorkerException;
import lk.ijse.dep.akashStainlessSteel.dao.DAOFactory;
import lk.ijse.dep.akashStainlessSteel.dao.DAOTypes;
import lk.ijse.dep.akashStainlessSteel.dao.custom.EstimationDAO;
import lk.ijse.dep.akashStainlessSteel.dao.custom.FnrCustomerDAO;
import lk.ijse.dep.akashStainlessSteel.dao.custom.WorkerDAO;
import lk.ijse.dep.akashStainlessSteel.dto.FnrCustomerDTO;
import lk.ijse.dep.akashStainlessSteel.dto.WorkerDTO;
import lk.ijse.dep.akashStainlessSteel.entity.FnrCustomer;
import lk.ijse.dep.akashStainlessSteel.entity.Worker;

import java.util.ArrayList;
import java.util.List;

public class WorkerBOImpl implements WorkerBO {

    WorkerDAO workerDAO = DAOFactory.getInstance().getDAO(DAOTypes.WORKER);
    EstimationDAO estimationDAO = DAOFactory.getInstance().getDAO(DAOTypes.ESTIMATION);

    @Override
    public boolean saveWorker(WorkerDTO worker) throws Exception {
        return workerDAO.save(new Worker(
                worker.getWorkerId(),
                worker.getName(),
                worker.getAddress(),
                worker.getContactNo(),
                worker.getSalary()
        ));
    }

    @Override
    public boolean updateWorker(WorkerDTO worker) throws Exception {
        return workerDAO.update(new Worker(
                worker.getWorkerId(),
                worker.getName(),
                worker.getAddress(),
                worker.getContactNo(),
                worker.getSalary()
        ));
    }

    @Override
    public boolean deleteWorker(String workerId) throws Exception {
        if(estimationDAO.existsByWorkerId(workerId)){
            throw new AlreadyExistsInWorkerException("Worker already have an Estimation, hence unable to delete");
        }
        return workerDAO.delete(workerId);
    }

    @Override
    public List<WorkerDTO> findAllWorkers() throws Exception {
        List<Worker> workers  = workerDAO.findAll();
        List<WorkerDTO> alWorkers= new ArrayList<>();
        for (Worker worker : workers) {
            alWorkers.add(new WorkerDTO(worker.getWorkerId(),
                    worker.getName(),
                    worker.getAddress(),
                    worker.getContactNo(),
                    worker.getSalary()
            ));
        }
        return alWorkers;
    }

    @Override
    public String getLastWorkerId() throws Exception {
        return workerDAO.getLastWorkerId();
    }

    @Override
    public WorkerDTO findWorker(String workerId) throws Exception {
        Worker worker = workerDAO.find(workerId);
        return new WorkerDTO(worker.getWorkerId(),
                worker.getName(),
                worker.getAddress(),
                worker.getContactNo(),
                worker.getSalary()
        );
    }

    @Override
    public List<String> getAllWorkerIDs() throws Exception {
        List<Worker> all = workerDAO.findAll();
        List<String> allWorkerIDs = new ArrayList<>();
        for (Worker worker : all) {
            allWorkerIDs.add(worker.getWorkerId());
        }
        return allWorkerIDs;
    }
}
