package lk.ijse.dep.akashStainlessSteel.dto;

public class JobDTO {
    private String jobId;
    private String customerId;
    private String description;

    public JobDTO(String jobId, String customerId, String description) {
        this.jobId = jobId;
        this.customerId = customerId;
        this.description = description;
    }

    public JobDTO() {
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
        return "JobDTO{" +
                "jobId='" + jobId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
