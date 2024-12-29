package com.flipkartProduct.product.product.dto;

public class ProductDTO {
    private String product;
    private String price;
    private int mobileCategoryTypeCount;
    private Double numericPrice;  // This will store the converted price
    private long quantity;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    private String productId;

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }


    // Getters and Setters
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getMobileCategoryTypeCount() {
        return mobileCategoryTypeCount;
    }

    public void setMobileCategoryTypeCount(int mobileCategoryTypeCount) {
        this.mobileCategoryTypeCount = mobileCategoryTypeCount;
    }

    public Double getNumericPrice() {
        return numericPrice;
    }

    public void setNumericPrice(Double numericPrice) {
        this.numericPrice = numericPrice;
    }
}
