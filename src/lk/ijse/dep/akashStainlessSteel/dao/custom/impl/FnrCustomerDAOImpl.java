package lk.ijse.dep.akashStainlessSteel.dao.custom.impl;

import lk.ijse.dep.akashStainlessSteel.dao.CrudUtil;
import lk.ijse.dep.akashStainlessSteel.dao.custom.FnrCustomerDAO;
import lk.ijse.dep.akashStainlessSteel.db.DBConnection;
import lk.ijse.dep.akashStainlessSteel.entity.FnrCustomer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FnrCustomerDAOImpl implements FnrCustomerDAO {

    public String getLastCustomerId() throws Exception{
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("SELECT customerId FROM fnrCustomer ORDER BY  customerId DESC LIMIT 1");
        ResultSet resultSet = pst.executeQuery();
        if(resultSet.next()){
            return  resultSet.getString(1);
        }
        return null;
    }

    @Override
    public List<FnrCustomer> findAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM fnrCustomer");
        List<FnrCustomer> customers = new ArrayList<>();
        while (resultSet.next()){
            customers.add(new FnrCustomer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return customers;
    }

    @Override
    public FnrCustomer find(String customerId) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM fnrCustomer WHERE customerId=?",customerId);
        if(resultSet.next()){
            return new FnrCustomer(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4));
        }
        return null;
    }

    @Override
    public boolean save(FnrCustomer entity) throws Exception {
        return CrudUtil.execute("INSERT INTO fnrCustomer VALUES (?,?,?,?)",
                entity.getCustomerId(),
                entity.getName(),
                entity.getAddress(),
                entity.getContactNo()
        );
    }

    @Override
    public boolean update(FnrCustomer entity) throws Exception {
        return CrudUtil.execute("UPDATE fnrCustomer SET name=?,address=?,contactNo=? WHERE customerId=?",
                entity.getName(),
                entity.getAddress(),
                entity.getContactNo(),
                entity.getCustomerId()
        );
    }

    @Override
    public boolean delete(String customerId) throws Exception {
        return CrudUtil.execute("DELETE FROM fnrCustomer WHERE customerId=?",customerId);
    }
}
