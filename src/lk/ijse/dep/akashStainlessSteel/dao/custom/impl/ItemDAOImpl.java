package lk.ijse.dep.akashStainlessSteel.dao.custom.impl;

import lk.ijse.dep.akashStainlessSteel.dao.CrudUtil;
import lk.ijse.dep.akashStainlessSteel.dao.custom.ItemDAO;
import lk.ijse.dep.akashStainlessSteel.db.DBConnection;
import lk.ijse.dep.akashStainlessSteel.entity.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    public String getLastItemCode() throws Exception{
        ResultSet resultSet = CrudUtil.execute("SELECT itemCode FROM item ORDER BY  itemCode DESC LIMIT 1");
        if(resultSet.next()){
            return  resultSet.getString(1);
        }
        return null;
    }

    @Override
    public List<Item> findAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM item");
        List<Item> items = new ArrayList<>();
        while (resultSet.next()){
            items.add(new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            ));
        }
        return items;
    }

    @Override
    public Item find(String itemCode) throws Exception {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM item WHERE itemCode=?",itemCode);
        if(resultSet.next()){
            return new Item(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4));
        }
        return null;
    }

    @Override
    public boolean save(Item entity) throws Exception {
        return CrudUtil.execute("INSERT INTO item VALUES (?,?,?,?)",
                entity.getItemCode(),
                entity.getDescription(),
                entity.getUnitPrice(),
                entity.getQtyOnHand()
        );
    }

    @Override
    public boolean update(Item entity) throws Exception {
        return CrudUtil.execute("UPDATE item SET description=?,unitPrice=?,qtyOnHand=? WHERE itemCode=?",
                entity.getDescription(),
                entity.getUnitPrice(),
                entity.getQtyOnHand(),
                entity.getItemCode()
        );

    }

    @Override
    public boolean delete(String itemCode) throws Exception {
        return CrudUtil.execute("DELETE FROM item WHERE itemCode=?",itemCode );
    }
}
