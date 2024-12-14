package com.flipkartProduct.product.Repository;

import com.flipkartProduct.product.Repository.CutomImplementationRepository.MemberRepository.MemberRepositoryCustom;
import com.flipkartProduct.product.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepository extends MongoRepository<User, String>, MemberRepositoryCustom {
}
