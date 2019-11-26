package lk.ijse.dep.akashStainlessSteel.dao.custom;
import lk.ijse.dep.akashStainlessSteel.dao.CrudDAO;
import lk.ijse.dep.akashStainlessSteel.entity.Worker;

public interface WorkerDAO extends CrudDAO<Worker,String> {
    public String getLastWorkerId() throws Exception;
}
