package com.flipkartProduct.product.orders.model;

import com.flipkartProduct.product.Enumeration.OrderStatus;
import com.flipkartProduct.product.cart.model.CartItem;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Document("orders")
public class OrderModel {
    @Id
    private String orderId;  // ✅ Made sure getter/setter exists
    private String userId;
    private String cartId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date orderDate;

    private OrderStatus orderStatus;
    private long totalAmount;


    public OrderModel() {
    }

    public OrderModel(String userId, String cartId, Date orderDate,
                      OrderStatus orderStatus, long totalAmount) {

        this.userId = userId;
        this.cartId = cartId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "The order  has been placed with status: " + orderStatus;
    }
}
