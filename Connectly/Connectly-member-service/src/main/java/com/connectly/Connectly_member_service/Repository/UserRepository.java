package com.connectly.Connectly_member_service.Repository;

import com.connectly.Connectly_member_service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
//    boolean findByEmailCheck(String email);
}
