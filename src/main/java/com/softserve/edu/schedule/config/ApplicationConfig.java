/* ApplicationConfig 1.0 02/07/2017 */
package com.softserve.edu.schedule.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
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
@ComponentScan(ConfigConstants.PACKAGES_TO_SCAN)
@PropertySource({ConfigConstants.DB_CONFIG_PROPERTIES})
@EnableJpaRepositories
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class ApplicationConfig {

    /**
     * DataBase driver class property name.
     */
    @Value(ConfigConstants.DB_DRIVER_CLASS_NAME)
    private String driverClassName;

    /**
     * DataBase URL property name.
     */
    @Value(ConfigConstants.DB_URL)
    private String databaseurl;

    /**
     * DataBase username property name.
     */
    @Value(ConfigConstants.DB_USERNAME)
    private String dbUserName;

    /**
     * DataBase user password property name.
     */
    @Value(ConfigConstants.DB_PASS)
    private String dbPassword;

    /**
     * DataBase dialect property name.
     */
    @Value(ConfigConstants.DB_DIALECT)
    private String dbDialect;

    /**
     * DataBase hbm2ddl property name.
     */
    @Value(ConfigConstants.HBM2DDL_AUTO)
    private String hbm2ddlAuto;

    /**
     * DataBase show SQL property name.
     */
    @Value(ConfigConstants.DB_SHOW_SQL)
    private String showSQL;

    /**
     * Hibernate set big string try clob property name.
     */
    @Value(ConfigConstants.DB_HB_SET_BIG_STRING_TC)
    private String hbSetBigStringTryClob;

    /**
     * Hibernate JDBC batch size property mapping.
     */
    @Value(ConfigConstants.DB_HB_JDBC_BATCH_SIZE)
    private String hbJdbcBatchSize;

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
    @Bean(destroyMethod = ConfigConstants.DATA_SOURCE_DESTROY_METHOD)
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(databaseurl);
        dataSource.setUsername(dbUserName);
        dataSource.setPassword(dbPassword);
        dataSource
                .setInitialSize(ConfigConstants.DATA_SOURCE_CON_POOL_INIT_SIZE);
        dataSource.setMaxActive(ConfigConstants.DATA_SOURCE_CON_POOL_MAX_SIZE);
        dataSource.setMaxIdle(ConfigConstants.DATA_SOURCE_CON_POOL_MAX_IDLE);
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
        factory.setPackagesToScan(ConfigConstants.ENTITY_LOCATION);
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();
        factory.setPersistenceProvider(new HibernatePersistenceProvider());
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty(ConfigConstants.HIBERNATE_DIALECT, dbDialect);
        jpaProperties.setProperty(ConfigConstants.HIBERNATE_SHOW_SQL, showSQL);
        jpaProperties.setProperty(ConfigConstants.HBM2DDL_AUTO, hbm2ddlAuto);
        jpaProperties.setProperty(ConfigConstants.HIBERNATE_SBSTC,
                hbSetBigStringTryClob);
        jpaProperties.setProperty(ConfigConstants.HIBERNATE_JDBC_BATCH_SIZE,
                hbJdbcBatchSize);
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
