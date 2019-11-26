package lk.ijse.dep.akashStainlessSteel.dao.custom.impl;
import lk.ijse.dep.akashStainlessSteel.dao.CrudUtil;
import lk.ijse.dep.akashStainlessSteel.dao.custom.EstimationDAO;
import lk.ijse.dep.akashStainlessSteel.db.DBConnection;
import lk.ijse.dep.akashStainlessSteel.entity.Estimation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstimationDAOImpl implements EstimationDAO {

    @Override
    public List<Estimation> findAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM estimation");
        List<Estimation> estimations = new ArrayList<>();
        while (resultSet.next()){
            estimations.add(new Estimation(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }
        return estimations;
    }

    @Override
    public Estimation find(String estimationNo) throws Exception {


        ResultSet resultSet = CrudUtil.execute("SELECT * FROM estimation WHERE estimationNo = ?",estimationNo);
        if(resultSet.next()){
            return new Estimation(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
        }
        return null;
    }

    @Override
    public boolean save(Estimation entity) throws Exception {
        return CrudUtil.execute("INSERT INTO estimation VALUES (?,?,?,?)",entity.getEstimationNo(),entity.getJobId(),entity.getWorkerId(),entity.getPrice());
    }

    @Override
    public boolean update(Estimation entity) throws Exception {
        return CrudUtil.execute("UPDATE estimation SET workerId=?,price=? WHERE estimationNo=?",entity.getWorkerId(),entity.getPrice(),entity.getEstimationNo());
    }

    @Override
    public boolean delete(String estimationNo) throws Exception {
        return CrudUtil.execute("DELETE FROM estimation WHERE estimationNo=?",estimationNo);
    }

    public String getLastEstimationNo() throws Exception{
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("SELECT estimationNo FROM estimation ORDER BY  estimationNo DESC LIMIT 1");
        ResultSet resultSet = pst.executeQuery();
        if(resultSet.next()){
            return  resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean existsByJobId(String jobId) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM estimation WHERE jobId=?",jobId);
        return resultSet.next();
    }

    @Override
    public boolean existsByWorkerId(String workerId) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM estimation WHERE workerId=?",workerId);
        return resultSet.next();
    }
}
