package com.flipkartProduct.product.Repository.CutomImplementationRepository.MemberRepository;

import com.flipkartProduct.product.model.User;

public interface MemberRepositoryCustom {
    User findByUsername(String clientRole ,String username);
}
