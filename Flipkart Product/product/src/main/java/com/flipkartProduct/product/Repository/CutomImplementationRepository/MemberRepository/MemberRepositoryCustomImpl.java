package com.flipkartProduct.product.Repository.CutomImplementationRepository.MemberRepository;

import com.flipkartProduct.product.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    @Autowired
    @Qualifier("secondaryMongoTemplate")
    MongoTemplate secondaryMongoTemplate;


    @Override
    public User findByUsername(String clientRole, String username) {
        Query query = new Query(Criteria.where("username").is(username));
        User userObj = secondaryMongoTemplate.findOne(query, User.class);
        List<String> roleNames = userObj.getRoles().stream()
                .map(role -> role.getName().name())  // Convert Enum to string
                .collect(Collectors.toList());
        boolean match =roleNames.stream().anyMatch(client -> client.equals(clientRole));
        if(match) return userObj;
        return null;
    }

}
