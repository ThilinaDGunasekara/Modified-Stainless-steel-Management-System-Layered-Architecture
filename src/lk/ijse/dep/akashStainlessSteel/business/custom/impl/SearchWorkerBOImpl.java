package lk.ijse.dep.akashStainlessSteel.business.custom.impl;

import lk.ijse.dep.akashStainlessSteel.business.custom.SearchWorkerBO;
import lk.ijse.dep.akashStainlessSteel.dao.DAOFactory;
import lk.ijse.dep.akashStainlessSteel.dao.DAOTypes;
import lk.ijse.dep.akashStainlessSteel.dao.custom.SearchWorkerDAO;
import lk.ijse.dep.akashStainlessSteel.dto.WorkerDTO;
import lk.ijse.dep.akashStainlessSteel.entity.Worker;

import java.util.ArrayList;
import java.util.List;

public class SearchWorkerBOImpl implements SearchWorkerBO {
    SearchWorkerDAO searchWorkerDAO = DAOFactory.getInstance().getDAO(DAOTypes.SEARCH_WORKERS);

    @Override
    public List<WorkerDTO> searchWorker(String searchText) throws Exception {
        List<Worker> workers = searchWorkerDAO.searchWorker(searchText);
        List<WorkerDTO> workerDTOS = new ArrayList<>();

        for (Worker worker : workers) {
            workerDTOS.add(new WorkerDTO(
                    worker.getWorkerId(),
                    worker.getName(),
                    worker.getAddress(),
                    worker.getContactNo(),
                    worker.getSalary()
            ));
        }
        return workerDTOS;
    }
}
