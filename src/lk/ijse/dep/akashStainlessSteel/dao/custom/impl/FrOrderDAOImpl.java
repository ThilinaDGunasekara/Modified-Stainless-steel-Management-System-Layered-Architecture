package lk.ijse.dep.akashStainlessSteel.dao.custom.impl;
import lk.ijse.dep.akashStainlessSteel.dao.CrudUtil;
import lk.ijse.dep.akashStainlessSteel.dao.custom.FrOrderDAO;
import lk.ijse.dep.akashStainlessSteel.db.DBConnection;
import lk.ijse.dep.akashStainlessSteel.entity.FrOrder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FrOrderDAOImpl implements FrOrderDAO{

    public String getLastOrderId() throws Exception{
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("SELECT orderId FROM frOrder ORDER BY  orderId DESC LIMIT 1");
        ResultSet resultSet = pst.executeQuery();
        if(resultSet.next()){
            return  resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean existsByCustomerId(String frCustomerId) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM frOrder WHERE customerId=?",frCustomerId);
        return resultSet.next();
    }

    @Override
    public List<FrOrder> findAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM frOrder WHERE orderId=?");
        List<FrOrder> orders = new ArrayList<>();
        while (resultSet.next()){
            orders.add(new FrOrder(
                    resultSet.getString(1),
                    resultSet.getDate(2),
                    resultSet.getString(3)
            ));
        }
        return orders;
    }

    @Override
    public FrOrder find(String orderId) throws Exception {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM frOrder WHERE orderId=?",orderId);
        if(resultSet.next()){
            return new FrOrder(
                    resultSet.getString(1),
                    resultSet.getDate(2),
                    resultSet.getString(3)
            );
        }
        return null;
    }

    @Override
    public boolean save(FrOrder entity) throws Exception {
        return CrudUtil.execute("INSERT INTO frOrder VALUES (?,?,?)",
                entity.getOrderId(),
                entity.getDate(),
                entity.getCustomerId()
        );
    }

    @Override
    public boolean update(FrOrder entity) throws Exception {
        return CrudUtil.execute("UPDATE frOrder SET date=? WHERE orderId=?",
                entity.getDate(),
                entity.getOrderId()
        );
    }

    @Override
    public boolean delete(String orderId) throws Exception {
        return CrudUtil.execute("DELETE FROM frOrder WHERE orderId=?",orderId);
    }
}
