package lk.ijse.dep.akashStainlessSteel.dao.custom;
import lk.ijse.dep.akashStainlessSteel.dao.CrudDAO;
import lk.ijse.dep.akashStainlessSteel.entity.Job;

public interface JobDAO extends CrudDAO<Job,String> {
    public String getLastJobId() throws Exception;
    boolean existsByCustomerId(String customerId) throws Exception;
}
