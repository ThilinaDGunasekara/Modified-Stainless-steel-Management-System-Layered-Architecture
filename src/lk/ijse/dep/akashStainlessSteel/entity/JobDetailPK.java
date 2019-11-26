package lk.ijse.dep.akashStainlessSteel.entity;

public class JobDetailPK {
    private String jobId;
    private String workerId;

    public JobDetailPK(String jobId, String workerId) {
        this.jobId = jobId;
        this.workerId = workerId;
    }

    public JobDetailPK() {
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }
}
