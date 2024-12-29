package com.flipkartProduct.product.product.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {


    @Id
    String id;
    String product;
    String category;
    String price;
    long quantity;
    String dataProductName;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getDataProductName() {
        return dataProductName;
    }

    public void setDataProductName(String dataProductName) {
        this.dataProductName = dataProductName;
    }


    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }


    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Product(String product, String category, String price) {
        this.product = product;
        this.category = category;
        this.price = price;
    }
}
