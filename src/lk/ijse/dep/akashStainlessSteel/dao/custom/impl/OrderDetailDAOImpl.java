package lk.ijse.dep.akashStainlessSteel.dao.custom.impl;

import lk.ijse.dep.akashStainlessSteel.dao.CrudUtil;
import lk.ijse.dep.akashStainlessSteel.dao.custom.OrderDetailDAO;
import lk.ijse.dep.akashStainlessSteel.db.DBConnection;
import lk.ijse.dep.akashStainlessSteel.entity.OrderDetail;
import lk.ijse.dep.akashStainlessSteel.entity.OrderDetailPK;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    public boolean deleteOrderDetail(OrderDetailPK orderDetailPK) throws Exception{
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("DELETE FROM orderDetail WHERE orderId=? AND itemCode=?");
        pst.setObject(1,orderDetailPK.getOrderId());
        pst.setObject(2,orderDetailPK.getItemCode());
        return (pst.executeUpdate()>0) ;

    }

    @Override
    public List<OrderDetail> findAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM orderDetail");
        List<OrderDetail> orderDetails = new ArrayList<>();
        while (resultSet.next()){
            orderDetails.add(new OrderDetail(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            ));
        }
        return orderDetails;
    }

    @Override
    public OrderDetail find(OrderDetailPK orderDetailPK) throws Exception {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM orderDetail WHERE orderId=? AND itemCode=?",
                orderDetailPK.getOrderId(),
                orderDetailPK.getItemCode()
        );
        if(resultSet.next()){
            return new OrderDetail(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );
        }
        return null;
    }

    @Override
    public boolean save(OrderDetail entity) throws Exception {
        return CrudUtil.execute("INSERT INTO orderDetail VALUES (?,?,?,?)",
                entity.getOrderDetailPK().getOrderId(),
                entity.getOrderDetailPK().getItemCode(),
                entity.getUnitPrice(),
                entity.getQuantity()
        );

    }

    @Override
    public boolean update(OrderDetail entity) throws Exception {
        return CrudUtil.execute("UPDATE orderDetail SET unitPrice=?,quantity=? WHERE orderId=? AND itemCode =?",
                entity.getUnitPrice(),
                entity.getQuantity(),
                entity.getOrderDetailPK().getOrderId(),
                entity.getOrderDetailPK().getItemCode()
        );
    }

    @Override
    public boolean delete(OrderDetailPK orderDetailPK) throws Exception {

        return CrudUtil.execute("DELETE FROM orderDetail WHERE orderId=? AND itemCode=?",
                orderDetailPK.getOrderId(),
                orderDetailPK.getItemCode()
        );
    }

    @Override
    public boolean existsByItemCode(String ItemCode) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM orderDetail WHERE itemCode=?",ItemCode);
        return resultSet.next();
    }
}
