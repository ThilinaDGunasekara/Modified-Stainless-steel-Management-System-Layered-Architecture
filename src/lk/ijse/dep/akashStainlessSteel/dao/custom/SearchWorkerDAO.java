package lk.ijse.dep.akashStainlessSteel.dao.custom;

import lk.ijse.dep.akashStainlessSteel.dao.SuperDAO;
import lk.ijse.dep.akashStainlessSteel.entity.Worker;

import java.util.List;

public interface SearchWorkerDAO extends SuperDAO {
    List<Worker> searchWorker(String searchText) throws Exception;
}
