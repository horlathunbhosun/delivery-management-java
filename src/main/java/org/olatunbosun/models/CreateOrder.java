package org.olatunbosun.models;

public class CreateOrder {

    private String orderNumber;
    private String deliveryAddress;
    private String deliveryDate;
    private String orderStatus;

    private int customerId;

    private CreateOrderItem createOrderItem;


    public CreateOrder(String orderNumber, String deliveryAddress, String deliveryDate, String orderStatus, int customerId, CreateOrderItem createOrderItem) {
        this.orderNumber = orderNumber;
        this.deliveryAddress = deliveryAddress;
        this.deliveryDate = deliveryDate;
        this.orderStatus = orderStatus;
        this.customerId = customerId;
        this.createOrderItem = createOrderItem;
    }


    public CreateOrder() {

    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public CreateOrderItem getCreateOrderItem() {
        return createOrderItem;
    }

    public void setCreateOrderItem(CreateOrderItem createOrderItem) {
        this.createOrderItem = createOrderItem;
    }

    @Override
    public String toString() {
        return "CreateOrder{" +
                "orderNumber=" + orderNumber +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", customerId=" + customerId +
                ", createOrderItem=" + createOrderItem +
                '}';
    }
}
