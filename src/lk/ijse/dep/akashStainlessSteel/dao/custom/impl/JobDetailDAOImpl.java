package lk.ijse.dep.akashStainlessSteel.dao.custom.impl;
import lk.ijse.dep.akashStainlessSteel.dao.CrudUtil;
import lk.ijse.dep.akashStainlessSteel.dao.custom.JobDetailDAO;
import lk.ijse.dep.akashStainlessSteel.entity.JobDetail;
import lk.ijse.dep.akashStainlessSteel.entity.JobDetailPK;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JobDetailDAOImpl implements JobDetailDAO{

    @Override
    public List<JobDetail> findAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM jobDetail");
        List<JobDetail> jobDetails = new ArrayList<>();
        while (resultSet.next()){
            jobDetails.add(new JobDetail(
                    resultSet.getString(1),
                    resultSet.getString(2)
            ));
        }
        return jobDetails;
    }

    @Override
    public JobDetail find(JobDetailPK jobDetailPK) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM jobDetail WHERE jobId=? AND workerId=?",
                jobDetailPK.getJobId(),
                jobDetailPK.getJobId()
        );
        if(resultSet.next()){
            return new JobDetail(
                    resultSet.getString(1),
                    resultSet.getString(2)
            );
        }
        return null;
    }

    @Override
    public boolean save(JobDetail entity) throws Exception {
        return CrudUtil.execute("INSERT INTO jobDetail VALUES (?,?)",
                entity.getJobDetailPK().getJobId(),
                entity.getJobDetailPK().getJobId()
        );
    }

    @Override
    public boolean update(JobDetail entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(JobDetailPK jobDetailPK) throws Exception {
        return CrudUtil.execute("DELETE FROM jobDetail WHERE jobId=? AND workerId=?",
                jobDetailPK.getJobId(),
                jobDetailPK.getWorkerId()
        );
    }

    @Override
    public boolean existsByWorkerId(String WorkerId) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM jobDetail WHERE workerId=?",WorkerId);
        return resultSet.next();
    }

    @Override
    public boolean existsByJobId(String JobId) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM jobDetail WHERE jobId=?",JobId);
        return resultSet.next();
    }
}
