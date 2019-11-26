package lk.ijse.dep.akashStainlessSteel.dao.custom.impl;
import lk.ijse.dep.akashStainlessSteel.dao.CrudUtil;
import lk.ijse.dep.akashStainlessSteel.dao.custom.FnrOrderDAO;
import lk.ijse.dep.akashStainlessSteel.db.DBConnection;
import lk.ijse.dep.akashStainlessSteel.entity.FnrOrder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FnrOrderDAOImpl implements FnrOrderDAO {

    public String getLastOrderId() throws Exception{
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("SELECT orderId FROM fnrOrder ORDER BY  orderId DESC LIMIT 1");
        ResultSet resultSet = pst.executeQuery();
        if(resultSet.next()){
            return  resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean existsByJobId(String jobId) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM fnrOrder  WHERE jobId=? ",jobId);
        return resultSet.next();
    }


    @Override
    public List<FnrOrder> findAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM fnrOrder");
        List<FnrOrder> orders = new ArrayList<>();
        while (resultSet.next()){
            orders.add(new FnrOrder(
                    resultSet.getString(1),
                    resultSet.getDate(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5)
            ));
        }
        return orders;
    }

    @Override
    public FnrOrder find(String orderId) throws Exception {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM fnrOrder WHERE orderId=?",orderId);
        if(resultSet.next()){
            return new FnrOrder(
                    resultSet.getString(1),
                    resultSet.getDate(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5));
        }
        return null;
    }

    @Override
    public boolean save(FnrOrder entity) throws Exception {
        return CrudUtil.execute("INSERT INTO fnrOrder VALUES (?,?,?,?,?)",
                entity.getOrderId(),
                entity.getDate(),
                entity.getJobId(),
                entity.getPrice(),
                entity.getAdvance()
        );
    }

    @Override
    public boolean update(FnrOrder entity) throws Exception {
        return CrudUtil.execute("UPDATE fnrOrder SET date=?,jobId=?,price=?,advance=? WHERE orderId=?",
                entity.getDate(),
                entity.getJobId(),
                entity.getPrice(),
                entity.getAdvance(),
                entity.getOrderId()
        );
    }

    @Override
    public boolean delete(String orderId) throws Exception {
        return CrudUtil.execute("DELETE FROM fnrOrder WHERE orderId=?",orderId);
    }
}
