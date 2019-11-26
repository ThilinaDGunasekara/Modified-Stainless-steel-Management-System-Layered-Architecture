package lk.ijse.dep.akashStainlessSteel.dao.custom;

import lk.ijse.dep.akashStainlessSteel.dao.CrudDAO;
import lk.ijse.dep.akashStainlessSteel.entity.Estimation;

import java.util.List;

public interface EstimationDAO extends CrudDAO<Estimation,String> {

    public String getLastEstimationNo() throws Exception;
    boolean existsByJobId(String jobId) throws Exception;
    boolean existsByWorkerId(String workerId) throws Exception;
}
