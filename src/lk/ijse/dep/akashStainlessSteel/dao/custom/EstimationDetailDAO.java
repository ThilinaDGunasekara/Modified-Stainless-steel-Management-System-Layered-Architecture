package lk.ijse.dep.akashStainlessSteel.dao.custom;
import lk.ijse.dep.akashStainlessSteel.dao.CrudDAO;
import lk.ijse.dep.akashStainlessSteel.entity.EstimationDetail;
import lk.ijse.dep.akashStainlessSteel.entity.EstimationDetailPK;


public interface EstimationDetailDAO extends CrudDAO<EstimationDetail, EstimationDetailPK> {
    boolean existsByItemCode(String ItemCode) throws Exception;
    EstimationDetail findEstimationDetail(String estimationNo) throws Exception;
}
