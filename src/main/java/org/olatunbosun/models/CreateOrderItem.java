package org.olatunbosun.models;

public class CreateOrderItem {

    private int productId;
    private int quantity;


    public CreateOrderItem( int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public CreateOrderItem() {

    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "CreateOrderItem{" +
                " productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
