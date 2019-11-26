package lk.ijse.dep.akashStainlessSteel.entity;

public class EstimationDetail implements SuperEntity {
    private EstimationDetailPK estimationDetailPK;
    private int eiQuantity;
    private double unitPrice;

    public EstimationDetail(EstimationDetailPK estimationDetailPK, int eiQuantity, double unitPrice) {
        this.estimationDetailPK = estimationDetailPK;
        this.eiQuantity = eiQuantity;
        this.unitPrice = unitPrice;
    }

    public EstimationDetail(String estimationNo,String itemCode, int eiQuantity, double unitPrice) {
        this.estimationDetailPK = new EstimationDetailPK(estimationNo,itemCode);
        this.eiQuantity = eiQuantity;
        this.unitPrice = unitPrice;
    }

    public EstimationDetail() {
    }

    public EstimationDetailPK getEstimationDetailPK() {
        return estimationDetailPK;
    }

    public void setEstimationDetailPK(EstimationDetailPK estimationDetailPK) {
        this.estimationDetailPK = estimationDetailPK;
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
}
