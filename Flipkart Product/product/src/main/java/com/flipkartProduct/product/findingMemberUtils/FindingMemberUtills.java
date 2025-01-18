package com.flipkartProduct.product.findingMemberUtils;

import com.flipkartProduct.product.members.model.User;
import com.flipkartProduct.product.members.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindingMemberUtills implements FindingMemberUtilsInterface{
   @Autowired
   MemberRepository memberRepository;

    @Override
    public User findByUserId(String userId) {
        return memberRepository.findByUserId(userId);
    }
}
