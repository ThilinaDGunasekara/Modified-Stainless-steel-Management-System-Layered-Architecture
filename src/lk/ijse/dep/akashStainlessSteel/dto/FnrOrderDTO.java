package lk.ijse.dep.akashStainlessSteel.dto;

import java.sql.Date;

public class FnrOrderDTO {

    private String orderId;
    private Date date;
    private String jobId;
    private double price;
    private double advance;

    public FnrOrderDTO(String orderId, Date date, String jobId, double price, double advance) {
        this.orderId = orderId;
        this.date = date;
        this.jobId = jobId;
        this.price = price;
        this.advance = advance;
    }

    public FnrOrderDTO(String orderId, Date date, String jobId, double price) {
        this.orderId = orderId;
        this.date = date;
        this.jobId = jobId;
        this.price = price;
    }

    public FnrOrderDTO() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAdvance() {
        return advance;
    }

    public void setAdvance(double advance) {
        this.advance = advance;
    }

    @Override
    public String toString() {
        return "FnrOrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", date=" + date +
                ", jobId='" + jobId + '\'' +
                ", price=" + price +
                ", advance=" + advance +
                '}';
    }
}
