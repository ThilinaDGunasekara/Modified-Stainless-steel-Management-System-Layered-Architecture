package lk.ijse.dep.akashStainlessSteel.entity;

import java.sql.Date;

public class SearchOrderEntity {
    private String orderId;
    private Date date;
    private String customerId;
    private String name;
    private double total;
    private double remainingPayment;

    public SearchOrderEntity(String orderId, Date date, String customerId, String name, double total, double remainingPayment) {
        this.orderId = orderId;
        this.date = date;
        this.customerId = customerId;
        this.name = name;
        this.total = total;
        this.remainingPayment = remainingPayment;
    }

    public SearchOrderEntity(String orderId, Date date, String customerId, String name, double total) {
        this.orderId = orderId;
        this.date = date;
        this.customerId = customerId;
        this.name = name;
        this.total = total;
    }
    public SearchOrderEntity(String string, Date date, String string1, String string2) {
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getRemainingPayment() {
        return remainingPayment;
    }

    public void setRemainingPayment(double remainingPayment) {
        this.remainingPayment = remainingPayment;
    }

    @Override
    public String toString() {
        return "SearchOrderEntity{" +
                "orderId='" + orderId + '\'' +
                ", date='" + date + '\'' +
                ", customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", total=" + total +
                ", remainingPayment=" + remainingPayment +
                '}';
    }
}
