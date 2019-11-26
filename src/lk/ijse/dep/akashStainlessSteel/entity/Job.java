package lk.ijse.dep.akashStainlessSteel.entity;

public class Job implements SuperEntity {
    private String jobId;
    private String customerId;
    private String description;

    public Job(String jobId, String customerId, String description) {
        this.jobId = jobId;
        this.customerId = customerId;
        this.description = description;
    }

    public Job() {
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId='" + jobId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
