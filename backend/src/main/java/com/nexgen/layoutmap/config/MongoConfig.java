package com.nexgen.layoutmap.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;


import org.springframework.beans.factory.annotation.Value;

@Configuration
@EnableMongoRepositories(basePackages = "com.nexgen.layoutmap.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private String password;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port:27017}")
    private String port;

    @Value("${spring.data.mongodb.authentication-database:admin}")
    private String authenticationDatabase;

    @SuppressWarnings("null")
    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @SuppressWarnings("null")
    @Override
    public MongoClient mongoClient() {
        String connectionString = String.format("mongodb://%s:%s@%s:%s/%s?authSource=%s", 
            username, password, host, port, databaseName, authenticationDatabase);
        return MongoClients.create(connectionString);
    }
}