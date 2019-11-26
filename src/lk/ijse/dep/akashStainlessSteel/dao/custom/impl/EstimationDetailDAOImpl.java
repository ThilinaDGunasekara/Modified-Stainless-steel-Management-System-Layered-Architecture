package lk.ijse.dep.akashStainlessSteel.dao.custom.impl;
import lk.ijse.dep.akashStainlessSteel.dao.CrudUtil;
import lk.ijse.dep.akashStainlessSteel.dao.custom.EstimationDetailDAO;
import lk.ijse.dep.akashStainlessSteel.entity.EstimationDetail;
import lk.ijse.dep.akashStainlessSteel.entity.EstimationDetailPK;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstimationDetailDAOImpl implements EstimationDetailDAO {

    @Override
    public List<EstimationDetail> findAll() throws Exception {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM estimationDetail");
        List<EstimationDetail> estimationDetails = new ArrayList<>();
        while (resultSet.next()){
            estimationDetails.add(new EstimationDetail(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)
            ));
        }
        return estimationDetails;
    }

    @Override
    public EstimationDetail find(EstimationDetailPK estimationDetailPK) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM estimationDetail WHERE estimationNo=? AND itemCode=?",estimationDetailPK.getEstimationNo(),estimationDetailPK.getItemCode());
        if(resultSet.next()){
            return new EstimationDetail(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4));
        }
        return null;
    }

    @Override
    public boolean save(EstimationDetail entity) throws Exception {
        return CrudUtil.execute("INSERT INTO estimationDetail VALUES (?,?,?,?)",
                entity.getEstimationDetailPK().getEstimationNo(),
                entity.getEstimationDetailPK().getItemCode(),
                entity.getEiQuantity(),
                entity.getUnitPrice());
    }

    @Override
    public boolean update(EstimationDetail entity) throws Exception {
        return CrudUtil.execute("UPDATE estimationDetail SET eiQuantity=?,unitPrice=? WHERE estimationNo =? AND itemCode=? ",
                entity.getEstimationDetailPK().getEstimationNo(),
                entity.getEstimationDetailPK().getItemCode(),
                entity.getEiQuantity(),
                entity.getUnitPrice()
        );
    }

    @Override
    public boolean delete(EstimationDetailPK estimationDetailPK) throws Exception {
        return CrudUtil.execute("DELETE FROM estimationDetail WHERE estimationNo=? AND itemCode=?",
                estimationDetailPK.getEstimationNo(),
                estimationDetailPK.getItemCode()
        );
    }

    @Override
    public boolean existsByItemCode(String ItemCode) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM estimationDetail WHERE itemCode=?",ItemCode);
        return resultSet.next();
    }

    @Override
    public EstimationDetail findEstimationDetail(String estimationNo) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM estimationDetail WHERE estimationNo=?",estimationNo);
        if(resultSet.next()){
            return new EstimationDetail(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4));

        }
        return null;
    }
}
