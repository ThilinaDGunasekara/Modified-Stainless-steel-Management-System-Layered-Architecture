package lk.ijse.dep.akashStainlessSteel.entity;

public class OrderDetail implements SuperEntity {
    private OrderDetailPK orderDetailPK;
    private double unitPrice;
    private int quantity;

    public OrderDetail(OrderDetailPK orderDetailPK, double unitPrice, int quantity) {
        this.orderDetailPK = orderDetailPK;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public OrderDetail(String orderId,String itemCode, double unitPrice, int quantity) {
        this.orderDetailPK = new OrderDetailPK(orderId,itemCode);
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public OrderDetail() {
    }

    public OrderDetailPK getOrderDetailPK() {
        return orderDetailPK;
    }

    public void setOrderDetailPK(OrderDetailPK orderDetailPK) {
        this.orderDetailPK = orderDetailPK;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailPK=" + orderDetailPK +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                '}';
    }
}
