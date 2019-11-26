package lk.ijse.dep.akashStainlessSteel.business.custom;

import lk.ijse.dep.akashStainlessSteel.business.SuperBO;
import lk.ijse.dep.akashStainlessSteel.dto.WorkerDTO;

import java.util.List;

public interface SearchWorkerBO extends SuperBO {
    List<WorkerDTO> searchWorker(String searchText) throws Exception;
}
