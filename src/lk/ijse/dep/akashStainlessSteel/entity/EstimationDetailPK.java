package lk.ijse.dep.akashStainlessSteel.entity;

public class EstimationDetailPK {
    private String estimationNo;
    private String itemCode;

    public EstimationDetailPK(String estimationNo, String itemCode) {
        this.estimationNo = estimationNo;
        this.itemCode = itemCode;
    }

    public EstimationDetailPK() {
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
}
