package com.flipkartProduct.product.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MultipleMongoConfig {

    @Primary
    @Bean(name = "primary")
    @ConfigurationProperties(prefix = "spring.data.mongodb")
    public MongoProperties getPrimary() {
        return new MongoProperties();
    }

    @Bean(name = "secondary")
    @ConfigurationProperties(prefix = "mongodb")
    public MongoProperties getSecondary() {
        return new MongoProperties();
    }

    @Primary
    @Bean(name = "primaryMongoTemplate")
    public MongoTemplate primaryMongoTemplate() {
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(primaryMongoClient(), getPrimary().getDatabase()));
    }

    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate() {
        // Alias the primaryMongoTemplate as the default mongoTemplate
        return primaryMongoTemplate();
    }

    @Bean(name = "secondaryMongoTemplate")
    public MongoTemplate secondaryMongoTemplate() {
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(secondaryMongoClient(), getSecondary().getDatabase()));
    }


    private MongoClient primaryMongoClient() {
        return MongoClients.create(getPrimary().getUri());
    }

    private MongoClient secondaryMongoClient() {
        return MongoClients.create(getSecondary().getUri());
    }
}