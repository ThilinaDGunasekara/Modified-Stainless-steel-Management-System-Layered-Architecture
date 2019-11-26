package lk.ijse.dep.akashStainlessSteel.tm;

import org.omg.CORBA.PRIVATE_MEMBER;

public class EstimationTM {
    private String itemCode;
    private String description;
    private int quantity;
    private double total;

    public EstimationTM(String itemCode, String description, int quantity, double total) {
        this.itemCode = itemCode;
        this.description = description;
        this.quantity = quantity;
        this.total = total;
    }

    public EstimationTM(String itemCode, String description, int quantity) {
        this.itemCode = itemCode;
        this.description = description;
        this.quantity = quantity;
    }

    public EstimationTM() {
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "EstimationTM{" +
                "itemCode='" + itemCode + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", total=" + total +
                '}';
    }
}
