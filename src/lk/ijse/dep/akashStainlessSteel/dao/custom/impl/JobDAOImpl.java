package lk.ijse.dep.akashStainlessSteel.dao.custom.impl;
import lk.ijse.dep.akashStainlessSteel.dao.CrudUtil;
import lk.ijse.dep.akashStainlessSteel.dao.custom.JobDAO;
import lk.ijse.dep.akashStainlessSteel.db.DBConnection;
import lk.ijse.dep.akashStainlessSteel.entity.Job;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JobDAOImpl implements JobDAO {

    public String getLastJobId() throws Exception{
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("SELECT jobId FROM job ORDER BY jobId DESC LIMIT 1");
        ResultSet resultSet = pst.executeQuery();
        if(resultSet.next()){
            return  resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean existsByCustomerId(String fnrCustomerId) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM job WHERE customerId=?",fnrCustomerId);
        return resultSet.next();
    }

    @Override
    public List<Job> findAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM job");
        List<Job> jobs = new ArrayList<>();
        while (resultSet.next()){
            jobs.add(new Job(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)

            ));
        }
        return jobs;
    }

    @Override
    public Job find(String jobId) throws Exception {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM job WHERE jobId=?",jobId);
        if(resultSet.next()){
            return new Job(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
        }
        return null;
    }

    @Override
    public boolean save(Job entity) throws Exception {
        return CrudUtil.execute("INSERT INTO job VALUES (?,?,?)",
                entity.getJobId(),
                entity.getCustomerId(),
                entity.getDescription()
        );
    }

    @Override
    public boolean update(Job entity) throws Exception {
        return CrudUtil.execute("UPDATE job SET description=? WHERE jobId=?",
                entity.getDescription(),
                entity.getJobId()
        );
    }

    @Override
    public boolean delete(String jobId) throws Exception {
        return CrudUtil.execute("DELETE FROM job WHERE jobId=?",jobId);
    }
}
