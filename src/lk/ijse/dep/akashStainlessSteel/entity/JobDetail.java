package lk.ijse.dep.akashStainlessSteel.entity;

public class JobDetail implements SuperEntity {
    private JobDetailPK jobDetailPK;

    public JobDetail(JobDetailPK jobDetailPK) {
        this.jobDetailPK = jobDetailPK;
    }

    public JobDetail() {
    }

    public JobDetail(String jobId,String workerId) {
        this.jobDetailPK = new JobDetailPK(jobId,workerId);
    }

    public JobDetailPK getJobDetailPK() {
        return jobDetailPK;
    }

    public void setJobDetailPK(JobDetailPK jobDetailPK) {
        this.jobDetailPK = jobDetailPK;
    }

    @Override
    public String toString() {
        return "JobDetail{" +
                "jobDetailPK=" + jobDetailPK +
                '}';
    }
}
