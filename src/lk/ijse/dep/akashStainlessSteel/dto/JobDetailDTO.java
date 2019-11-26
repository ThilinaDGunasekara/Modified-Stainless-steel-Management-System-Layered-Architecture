package lk.ijse.dep.akashStainlessSteel.dto;

public class JobDetailDTO {
    private String jobId;
    private String workerId;

    public JobDetailDTO(String jobId, String workerId) {
        this.jobId = jobId;
        this.workerId = workerId;
    }

    public JobDetailDTO() {
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

    @Override
    public String toString() {
        return "JobDetailDTO{" +
                "jobId='" + jobId + '\'' +
                ", workerId='" + workerId + '\'' +
                '}';
    }
}
