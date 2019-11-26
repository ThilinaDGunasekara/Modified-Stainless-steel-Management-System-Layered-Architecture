package lk.ijse.dep.akashStainlessSteel.tm;
import com.jfoenix.controls.JFXButton;


public class FrOrderTM {

    private String itemCode;
    private String description;
    private int quantity;
    private double unitPrice;
    private double total;
    private JFXButton delete;

    public FrOrderTM() {
    }

    public FrOrderTM(String itemCode, String description, int quantity, double unitPrice, double total, JFXButton delete) {
        this.itemCode = itemCode;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
        this.delete = delete;
    }

    public FrOrderTM(String itemCode, String description, int quantity, double unitPrice, double total) {
        this.itemCode = itemCode;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public JFXButton getDelete() {
        return delete;
    }

    public void setDelete(JFXButton delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "FrOrderTM{" +
                "itemCode='" + itemCode + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", total=" + total +
                ", delete=" + delete +
                '}';
    }
}
