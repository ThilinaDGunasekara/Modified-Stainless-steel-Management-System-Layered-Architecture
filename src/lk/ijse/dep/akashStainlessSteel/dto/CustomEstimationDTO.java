package lk.ijse.dep.akashStainlessSteel.dto;

public class CustomEstimationDTO {
    private String jobId;
    private  String description;

    public CustomEstimationDTO(String jobId, String description) {
        this.setJobId(jobId);
        this.setDescription(description);
    }

    public CustomEstimationDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        return "CustomEstimationDTO{" +
                "jobId='" + jobId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
