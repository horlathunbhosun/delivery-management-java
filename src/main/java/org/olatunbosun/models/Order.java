package org.olatunbosun.models;

public class Order {
    private int orderId;

    private int customerId;
    private String orderNumber;
    private String deliveryAddress;
    private String deliveryDate;
    private String orderStatus;
    private String productName;
    private int quantity;
    private String dateCreated;


    public Order(int orderId, int customerId, String orderNumber, String deliveryAddress, String deliveryDate, String orderStatus, String productName, int quantity, String dateCreated) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderNumber = orderNumber;
        this.deliveryAddress = deliveryAddress;
        this.deliveryDate = deliveryDate;
        this.orderStatus = orderStatus;
        this.productName = productName;
        this.quantity = quantity;
        this.dateCreated = dateCreated;
    }





    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Order() {

    }

    @Override
public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", orderNumber='" + orderNumber + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", dateCreated='" + dateCreated + '\'' +
                '}';
    }
}
