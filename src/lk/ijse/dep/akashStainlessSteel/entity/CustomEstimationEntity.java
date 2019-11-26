package lk.ijse.dep.akashStainlessSteel.entity;

public class CustomEstimationEntity {
    private String jobId;
    private  String description;

    public CustomEstimationEntity(String jobId, String description) {
        this.jobId = jobId;
        this.description = description;
    }

    public CustomEstimationEntity() {
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CustomEstimationEntity{" +
                "jobId='" + jobId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
