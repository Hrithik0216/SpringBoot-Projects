package com.flipkartProduct.product.cart.DTO;

import java.util.Optional;

public class CartDto {

    public CartDto(int quantity, String price, String productName) {
        this.quantity = quantity;
        this.price = price;
        this.productName = productName;
    }

    private int quantity;
    private String price;
    private String productName;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


}
