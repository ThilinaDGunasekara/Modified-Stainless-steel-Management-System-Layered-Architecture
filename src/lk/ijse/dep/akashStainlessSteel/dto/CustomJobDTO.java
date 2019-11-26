package lk.ijse.dep.akashStainlessSteel.dto;

public class CustomJobDTO {
    private String jobId;
    private String customerId;
    private String name;
    private String description;

    public CustomJobDTO(String jobId, String customerId, String name, String description) {
        this.jobId = jobId;
        this.customerId = customerId;
        this.name = name;
        this.description = description;
    }

    public CustomJobDTO() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CustomJobDTO{" +
                "jobId='" + jobId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
