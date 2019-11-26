package lk.ijse.dep.akashStainlessSteel.business.custom;

import lk.ijse.dep.akashStainlessSteel.business.SuperBO;
import lk.ijse.dep.akashStainlessSteel.business.exception.AlreadyExistsInOrderAndEstimationException;
import lk.ijse.dep.akashStainlessSteel.dto.JobDTO;

import java.util.List;

public interface JobBO  extends SuperBO {
    boolean saveJob(JobDTO job)throws Exception;

    boolean updateJob(JobDTO job)throws Exception;

    boolean deleteJob(String jobId) throws Exception, AlreadyExistsInOrderAndEstimationException;

    List<JobDTO> findAllJobs() throws Exception;

    String getLastJobId()throws Exception;

    JobDTO findJob(String jobId) throws Exception;

    List<String> getAllJobIDs() throws Exception;
}
