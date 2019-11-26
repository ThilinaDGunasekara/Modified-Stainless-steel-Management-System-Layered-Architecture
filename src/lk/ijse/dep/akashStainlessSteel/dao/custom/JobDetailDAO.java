package lk.ijse.dep.akashStainlessSteel.dao.custom;
import lk.ijse.dep.akashStainlessSteel.dao.CrudDAO;
import lk.ijse.dep.akashStainlessSteel.entity.JobDetail;
import lk.ijse.dep.akashStainlessSteel.entity.JobDetailPK;
import lk.ijse.dep.akashStainlessSteel.entity.OrderDetailPK;


public interface JobDetailDAO extends CrudDAO<JobDetail, JobDetailPK> {
    boolean existsByWorkerId(String WorkerId) throws Exception;
    boolean existsByJobId(String JobId) throws Exception;
}
