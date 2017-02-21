package com.softserve.edu.schedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
	/*	return "schedule-lv215";*/
		return "demo";
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
/*		return new MongoClient("mongodb://schedule:schedule@ds157839.mlab.com:57839/");*/
		return new MongoClient("127.0.0.1");
	}

	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
		return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
	}
}
