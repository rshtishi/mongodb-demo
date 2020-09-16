package com.github.rshtishi.demo.raw;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfiguration {
	
	@Value("${spring.data.mongodb.database}")
	private String databaseName;
	
	@Bean
	public MongoClient mongoClient() {
		MongoClient mongoClient = MongoClients.create();
		return mongoClient;
	}
	
	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), databaseName);
	}

}
