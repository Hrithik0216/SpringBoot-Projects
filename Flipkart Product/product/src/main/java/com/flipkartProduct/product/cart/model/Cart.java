package com.flipkartProduct.product.cart.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("cart")
public class Cart {
    @Id
    private String id;
    private String userId;

    private List<CartItem> productList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public Cart(String userId, List<CartItem> productList) {
        this.userId = userId;
        this.productList = productList;
    }
    public Cart(List<CartItem> productList) {
        this.productList = productList;
    }
    public Cart(){

    }


    public String getUser() {
        return userId;
    }

    public void setUser(String userId) {
        this.userId = userId;
    }

    public List<CartItem> getProductList() {
        return productList;
    }

    public void setProductList(List<CartItem> productList) {
        this.productList = productList;
    }


}
