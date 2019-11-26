package lk.ijse.dep.akashStainlessSteel.dao.custom.impl;
import lk.ijse.dep.akashStainlessSteel.dao.CrudUtil;
import lk.ijse.dep.akashStainlessSteel.dao.custom.WorkerDAO;
import lk.ijse.dep.akashStainlessSteel.db.DBConnection;
import lk.ijse.dep.akashStainlessSteel.entity.Worker;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WorkerDAOImpl implements WorkerDAO{

    public String getLastWorkerId() throws Exception{
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("SELECT workerId FROM worker ORDER BY  workerId DESC LIMIT 1");
        ResultSet resultSet = pst.executeQuery();
        if(resultSet.next()){
            return  resultSet.getString(1);
        }
        return null;
    }

    @Override
    public List<Worker> findAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM worker");
        List<Worker> workers = new ArrayList<>();
        while (resultSet.next()){
            workers.add(new Worker(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    Integer.parseInt(resultSet.getString(4)),
                    resultSet.getDouble(5)
            ));
        }
        return workers;
    }

    @Override
    public Worker find(String workerId) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM worker WHERE workerId=?",workerId);
        if(resultSet.next()){
            return new Worker(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    Integer.parseInt(resultSet.getString(4)),
                    resultSet.getDouble(5)
            );
        }
        return null;
    }

    @Override
    public boolean save(Worker entity) throws Exception {
        return CrudUtil.execute("INSERT INTO worker VALUES (?,?,?,?,?)",
                entity.getWorkerId(),
                entity.getName(),
                entity.getAddress(),
                entity.getContactNo(),
                entity.getSalary()
        );
    }

    @Override
    public boolean update(Worker entity) throws Exception {
        return CrudUtil.execute("UPDATE worker SET name=?,address=?,contactNo=?,salary=? WHERE workerId=?",
                entity.getName(),
                entity.getAddress(),
                entity.getContactNo(),
                entity.getSalary(),
                entity.getWorkerId()
        );
    }

    @Override
    public boolean delete(String workerId) throws Exception {
        return CrudUtil.execute("DELETE FROM worker WHERE workerId=?",workerId );
    }
}
