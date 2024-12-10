package com.flipkartProduct.product.DTO;

public class ProductDTO {
    private String product;
    private String price;
    private int mobileCategoryTypeCount;
    private Double numericPrice;  // This will store the converted price

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
