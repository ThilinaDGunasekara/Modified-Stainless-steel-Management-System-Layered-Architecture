package lk.ijse.dep.akashStainlessSteel.entity;

public class Estimation implements SuperEntity {
    private String estimationNo;
    private String jobId;
    private String workerId;
    private double price;

    public Estimation(String estimationNo, String jobId, String workerId, double price) {
        this.estimationNo = estimationNo;
        this.jobId = jobId;
        this.workerId = workerId;
        this.price = price;
    }

    public Estimation() {
    }

    public String getEstimationNo() {
        return estimationNo;
    }

    public void setEstimationNo(String estimationNo) {
        this.estimationNo = estimationNo;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Estimation{" +
                "estimationNo='" + estimationNo + '\'' +
                ", jobId='" + jobId + '\'' +
                ", workerId='" + workerId + '\'' +
                ", price=" + price +
                '}';
    }
}
