/* MongoConfig 1.0 02/23/2017 */
package com.softserve.edu.schedule.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * Configuration of MongoDB data source of application.
 *
 * @version 1.0 23 February 2017
 *
 * @author Volodymyr Ped'ko
 *
 * @since 1.8
 */
@Configuration
@PropertySource({"/WEB-INF/mongo.properties"})
public class MongoConfig extends AbstractMongoConfiguration {

    /**
     * Environment instance to get properties.
     */
    @Autowired
    private Environment env;

    /**
     * Return the name of the database to connect to.
     *
     * @return Mongo database name.
     */
    @Override
    public String getDatabaseName() {
        return env.getProperty("mongo.dbName");
    }

    /**
     * Return the Mongo instance to connect to.
     *
     * @return the Mongo instance
     *
     * @throws Exception
     *             if can not connect
     */
    @Override
    @Bean
    public Mongo mongo() throws Exception {
        MongoClientURI uri = new MongoClientURI(
                env.getProperty("mongo.dbConnectionURI"));
        return new MongoClient(uri);

    }

    /**
     * Return the GridFsTemplate instance to storage files in MongoDB.
     *
     * @return the GridFsTemplate instance
     *
     * @throws Exception
     *             if something unexpected was happened
     */
    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }
}