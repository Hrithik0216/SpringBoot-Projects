package com.flipkartProduct.product.members.repository;

import com.flipkartProduct.product.members.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MemberRepository extends MongoRepository<User, String>, MemberRepositoryCustom {

}
