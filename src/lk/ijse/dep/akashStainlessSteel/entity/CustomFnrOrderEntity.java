package lk.ijse.dep.akashStainlessSteel.entity;

public class CustomFnrOrderEntity {

    private String jobId;
    private String customerId;
    private String name;
    private double price;

    public CustomFnrOrderEntity(String jobId, String customerId, String name, double price) {
        this.jobId = jobId;
        this.customerId = customerId;
        this.name = name;
        this.price = price;
    }

    public CustomFnrOrderEntity() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CustomFnrOrderEntity{" +
                "jobId='" + jobId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
