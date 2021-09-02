package com.fiap.sociallearn.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configurable
@EnableMongoRepositories(basePackages = "com.fiap.sociallearn.repository")
public class MogoConfig {
    @Bean
    public MongoClient mongo() throws Exception {
        final ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/sociallearn");
        final MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "sociallearn");
    }
}
