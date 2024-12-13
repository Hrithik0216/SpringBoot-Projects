package com.flipkartProduct.product.Service.CutomImplementationRepository;

import com.flipkartProduct.product.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    @Autowired
    @Qualifier("secondaryMongoTemplate")
    MongoTemplate secondaryMongoTemplate;


    @Override
    public User findByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        User userObj= secondaryMongoTemplate.findOne(query, User.class);
        System.out.println(userObj.getUsername().toString());
        return userObj;
    }

}
