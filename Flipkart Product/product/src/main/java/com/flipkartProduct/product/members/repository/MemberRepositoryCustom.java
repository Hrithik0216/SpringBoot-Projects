package com.flipkartProduct.product.members.repository;

import com.flipkartProduct.product.members.model.User;

public interface MemberRepositoryCustom {
    User findByUsername(String clientRole ,String username);
    User findByID(String id, String clientRole);
    User findByUserId(String id);
}
