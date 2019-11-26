package lk.ijse.dep.akashStainlessSteel.dao.custom;
import lk.ijse.dep.akashStainlessSteel.dao.CrudDAO;
import lk.ijse.dep.akashStainlessSteel.dao.CrudUtil;
import lk.ijse.dep.akashStainlessSteel.entity.FnrOrder;
import lk.ijse.dep.akashStainlessSteel.entity.SuperEntity;

import java.sql.ResultSet;


public interface FnrOrderDAO extends CrudDAO<FnrOrder, String>{
    public String getLastOrderId() throws Exception;

    public boolean existsByJobId(String ItemCode) throws Exception ;
}
