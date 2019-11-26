package lk.ijse.dep.akashStainlessSteel.business.custom.impl;
import lk.ijse.dep.akashStainlessSteel.business.custom.JobBO;
import lk.ijse.dep.akashStainlessSteel.business.exception.AlreadyExistsInOrderAndEstimationException;
import lk.ijse.dep.akashStainlessSteel.dao.DAOFactory;
import lk.ijse.dep.akashStainlessSteel.dao.DAOTypes;
import lk.ijse.dep.akashStainlessSteel.dao.custom.EstimationDAO;
import lk.ijse.dep.akashStainlessSteel.dao.custom.FnrOrderDAO;
import lk.ijse.dep.akashStainlessSteel.dao.custom.JobDAO;
import lk.ijse.dep.akashStainlessSteel.dto.JobDTO;
import lk.ijse.dep.akashStainlessSteel.entity.Job;
import java.util.ArrayList;
import java.util.List;

public class JobBOImpl implements JobBO {

    JobDAO jobDAO = DAOFactory.getInstance().getDAO(DAOTypes.JOB);
    EstimationDAO estimationDAO = DAOFactory.getInstance().getDAO(DAOTypes.ESTIMATION);
    FnrOrderDAO fnrOrderDAO = DAOFactory.getInstance().getDAO(DAOTypes.FNR_ORDER);

    @Override
    public boolean saveJob(JobDTO job) throws Exception {
        return jobDAO.save(new Job(
                job.getJobId(),
                job.getCustomerId(),
                job.getDescription()
        ));
    }

    @Override
    public boolean updateJob(JobDTO job) throws Exception {
        return jobDAO.update(new Job(job.getJobId(),
                job.getCustomerId(),
                job.getDescription()
        ));
    }

    @Override
    public boolean deleteJob(String jobId) throws Exception {
        if(estimationDAO.existsByJobId(jobId) || fnrOrderDAO.existsByJobId(jobId)){
            throw new AlreadyExistsInOrderAndEstimationException("Job already have an Estimation, hence unable to delete");
        }
        return jobDAO.delete(jobId);
    }

    @Override
    public List<JobDTO> findAllJobs() throws Exception {
        List<Job> allJobs  = jobDAO.findAll();
        List<JobDTO> allBOJobs= new ArrayList<>();

        for (Job allJob : allJobs) {
            allBOJobs.add(new JobDTO(
                    allJob.getJobId(),
                    allJob.getCustomerId(),
                    allJob.getDescription()
            ));
        }
        return allBOJobs;
    }

    @Override
    public String getLastJobId() throws Exception {
        return jobDAO.getLastJobId();
    }

    @Override
    public JobDTO findJob(String jobId) throws Exception {
        Job job = jobDAO.find(jobId);
        return new JobDTO(job.getJobId(),
                job.getCustomerId(),
                job.getDescription()
        );
    }

    @Override
    public List<String> getAllJobIDs() throws Exception {
        List<Job> all = jobDAO.findAll();
        List<String> allJobIDs = new ArrayList<>();

        for (Job job : all) {
            allJobIDs.add(job.getJobId());
        }
        return allJobIDs;
    }
}
