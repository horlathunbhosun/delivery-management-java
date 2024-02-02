package org.olatunbosun.models;

public class AssignOrderDelivery {

    private int id;
    private int orderId;
    private int deliveryPersonId;
    private int customerId;
    private int sequenceNumber;
    private String location;
    private String orderStatus;


    public AssignOrderDelivery( int orderId, int deliveryPersonId, int customerId, int sequenceNumber, String location, String orderStatus) {
//        this.id = id
        this.orderId = orderId;
        this.deliveryPersonId = deliveryPersonId;
        this.customerId = customerId;
        this.sequenceNumber = sequenceNumber;
        this.location = location;
        this.orderStatus = orderStatus;
    }


    public AssignOrderDelivery() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDeliveryPersonId() {
        return deliveryPersonId;
    }

    public void setDeliveryPersonId(int deliveryPersonId) {
        this.deliveryPersonId = deliveryPersonId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


    @Override
    public String toString() {
        return "AssignOrderDelivery{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", deliveryPersonId=" + deliveryPersonId +
                ", customerId=" + customerId +
                ", sequenceNumber='" + sequenceNumber + '\'' +
                ", location='" + location + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
