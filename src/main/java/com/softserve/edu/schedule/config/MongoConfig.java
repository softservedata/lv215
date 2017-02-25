/* MongoConfig 1.0 02/23/2017 */
package com.softserve.edu.schedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class MongoConfig extends AbstractMongoConfiguration {

    /**
     * Return the name of the database to connect to.
     * 
     * @return Mongo database name.
     */
    @Override
    public String getDatabaseName() {
        return "schedule-lv215";
        // return "demo";
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
//                "mongodb://schedule:schedule@ds157839.mlab.com:57839/schedule-lv215"
                "mongodb://schedule:schedule@ds157809.mlab.com:57809/schedule-lv215");
        return new MongoClient(uri);

//         return new MongoClient("127.0.0.1");
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
