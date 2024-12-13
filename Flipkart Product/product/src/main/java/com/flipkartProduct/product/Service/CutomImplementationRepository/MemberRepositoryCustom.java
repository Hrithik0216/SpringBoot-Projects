package com.flipkartProduct.product.Service.CutomImplementationRepository;

import com.flipkartProduct.product.model.User;

public interface MemberRepositoryCustom {
    User findByUsername(String username);
}
