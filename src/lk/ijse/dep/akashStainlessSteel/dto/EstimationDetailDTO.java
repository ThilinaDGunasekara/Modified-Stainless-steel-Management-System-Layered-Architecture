package lk.ijse.dep.akashStainlessSteel.dto;

public class EstimationDetailDTO {
    private String estimationNo;
    private String itemCode;
    private int eiQuantity;
    private double unitPrice;

    public EstimationDetailDTO(String estimationNo, String itemCode, int eiQuantity, double unitPrice) {
        this.estimationNo = estimationNo;
        this.itemCode = itemCode;
        this.eiQuantity = eiQuantity;
        this.unitPrice = unitPrice;
    }

    public EstimationDetailDTO() {
    }

    public String getEstimationNo() {
        return estimationNo;
    }

    public void setEstimationNo(String estimationNo) {
        this.estimationNo = estimationNo;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getEiQuantity() {
        return eiQuantity;
    }

    public void setEiQuantity(int eiQuantity) {
        this.eiQuantity = eiQuantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "EstimationDetailDTO{" +
                "estimationNo='" + estimationNo + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", eiQuantity=" + eiQuantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
