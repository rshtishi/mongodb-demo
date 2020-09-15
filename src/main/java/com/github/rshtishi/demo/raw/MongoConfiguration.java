package com.github.rshtishi.demo.raw;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfiguration {
	
	@Bean
	public MongoClient mongoClient() {
		MongoClient mongoClient = MongoClients.create();
		return mongoClient;
	}

}
