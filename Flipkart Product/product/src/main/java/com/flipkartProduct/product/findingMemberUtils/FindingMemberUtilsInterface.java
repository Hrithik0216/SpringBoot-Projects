package com.flipkartProduct.product.findingMemberUtils;

import com.flipkartProduct.product.members.model.User;

public interface FindingMemberUtilsInterface {
    User findByUserId(String userId);
}
