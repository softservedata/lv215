/* ApplicationConfig 1.0 02/07/2017 */
package com.softserve.edu.schedule.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main root configuration of application. Configure data sources, resource
 * bundles and aspects.
 *
 * @version 1.0 07 February 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Configuration
@ComponentScan("com.softserve.edu.schedule")
@PropertySource({"/WEB-INF/db.properties"})
@EnableJpaRepositories
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class ApplicationConfig {

    /**
     * Data source connection pool initial size.
     */
    private static final int DATA_SOURCE_CON_POOL_INIT_SIZE = 5;

    /**
     * Data source connection pool maximum connections.
     */
    private static final int DATA_SOURCE_CON_POOL_MAX_SIZE = 50;

    /**
     * Data source connection pool maximum idle connections.
     */
    private static final int DATA_SOURCE_CON_POOL_MAX_IDLE = 50;

    /**
     * Environment instance to get properties.
     */
    @Autowired
    private Environment env;

    /**
     * Resolves ${...} placeholders within bean definition property values and
     * Value annotations against the current Spring Environment and its set of
     * PropertySources.
     *
     * @return PropertySourcesPlaceholderConfigurer example
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * Set configured JPA vendor adapter for application.
     *
     * @return HibernateJpaVendorAdapter example
     */
    @Bean
    public HibernateJpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        return hibernateJpaVendorAdapter;
    }

    /**
     * Set configured data source for application.
     *
     * @return DataSource example
     */
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName(env.getProperty("db.driverClassName"));
        dataSource.setUrl(env.getProperty("db.databaseurl"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        dataSource.setInitialSize(DATA_SOURCE_CON_POOL_INIT_SIZE);
        dataSource.setMaxActive(DATA_SOURCE_CON_POOL_MAX_SIZE);
        dataSource.setMaxIdle(DATA_SOURCE_CON_POOL_MAX_IDLE);
        return dataSource;
    }

    /**
     * Set configured entity manager factory for application.
     *
     * @return EntityManagerFactory example
     */
    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(jpaVendorAdapter());
        factory.setPackagesToScan("com.softserve.edu.schedule.entity");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();
        factory.setPersistenceProvider(new HibernatePersistenceProvider());
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect",
                env.getProperty("db.dialect"));
        jpaProperties.setProperty("hibernate.show_sql",
                env.getProperty("db.showSQL"));
        jpaProperties.setProperty("hibernate.hbm2ddl.auto",
                env.getProperty("db.hbm2ddl.auto"));
        jpaProperties.setProperty("hibernate.connection.SetBigStringTryClob",
                env.getProperty("db.hbSetBigStringTryClob"));
        jpaProperties.setProperty("hibernate.jdbc.batch_size",
                env.getProperty("db.hbJdbcBatchSize"));
        factory.setJpaProperties(jpaProperties);
        return factory.getObject();
    }

    /**
     * Set configured platform transaction manager for application.
     *
     * @return PlatformTransactionManager example
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setDataSource(dataSource());
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

}
