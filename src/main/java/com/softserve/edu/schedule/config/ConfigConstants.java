/* ConfigConstants 1.0 02/07/2017 */
package com.softserve.edu.schedule.config;

/**
 * Interface to storage configuration parameters values.
 *
 * @version 1.0 07 February 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public interface ConfigConstants {

    /* General constants */
    /**
     * Package scanning general path.
     */
    String PACKAGES_TO_SCAN = "com.softserve.edu.schedule";

    /**
     * UTF-8 encoding.
     */
    String UTF8 = "UTF-8";

    /**
     * Start page URL.
     */
    String START_URL = "/";

    /* Application config */
    /**
     * DataBase properties file path.
     */
    String DB_CONFIG_PROPERTIES = "/WEB-INF/db.properties";

    /**
     * DataBase driver class property mapping.
     */
    String DB_DRIVER_CLASS_NAME = "${db.driverClassName}";

    /**
     * DataBase URL property mapping.
     */
    String DB_URL = "${db.databaseurl}";

    /**
     * DataBase username property mapping.
     */
    String DB_USERNAME = "${db.username}";

    /**
     * DataBase user password property mapping.
     */
    String DB_PASS = "${db.password}";

    /**
     * DataBase dialect property mapping.
     */
    String DB_DIALECT = "${db.dialect}";

    /**
     * DataBase hbm2ddl property mapping.
     */
    String HBM2DDL_AUTO = "${db.hbm2ddl.auto}";

    /**
     * DataBase show SQL property mapping.
     */
    String DB_SHOW_SQL = "${db.showSQL}";

    /**
     * Hibernate set big string try clob property mapping.
     */
    String DB_HB_SET_BIG_STRING_TC = "${db.hbSetBigStringTryClob}";

    /**
     * Hibernate JDBC batch size property mapping.
     */
    String DB_HB_JDBC_BATCH_SIZE = "${db.hbJdbcBatchSize}";

    /**
     * Data source destroy method.
     */
    String DATA_SOURCE_DESTROY_METHOD = "close";

    /**
     * Data source connection pool initial size.
     */
    int DATA_SOURCE_CON_POOL_INIT_SIZE = 5;

    /**
     * Data source connection pool maximum connections.
     */
    int DATA_SOURCE_CON_POOL_MAX_SIZE = 10;

    /**
     * Data source connection pool maximum idle connections.
     */
    int DATA_SOURCE_CON_POOL_MAX_IDLE = 10;

    /**
     * Entity package location.
     */
    String ENTITY_LOCATION = "com.softserve.edu.schedule.entity";

    /**
     * Hibernate dialect property name.
     */
    String HIBERNATE_DIALECT = "hibernate.dialect";

    /**
     * Hibernate show SQL property name.
     */
    String HIBERNATE_SHOW_SQL = "hibernate.show_sql";

    /**
     * Hibernate hbm2ddl auto property name.
     */
    String HIBERNATE_HBM2DDL = "hibernate.hbm2ddl.auto";

    /**
     * Hibernate connection set big string try clob property name.
     */
    String HIBERNATE_SBSTC = "hibernate.connection.SetBigStringTryClob";

    /**
     * Hibernate JDBC batch size property name.
     */
    String HIBERNATE_JDBC_BATCH_SIZE = "hibernate.jdbc.batch_size";

    /* Mail sending config */
    /**
     * Mail sending properties file path.
     */
    String MAIL_CONFIG_PROPERTIES = "/WEB-INF/mail.properties";

    /**
     * Mail host property mapping.
     */
    String MAIL_HOST = "${mail.host}";

    /**
     * Mail port property mapping.
     */
    String MAIL_PORT = "${mail.port}";

    /**
     * Mail username property mapping.
     */
    String MAIL_USERNAME = "${mail.userName}";

    /**
     * Mail password property mapping.
     */
    String MAIL_PASS = "${mail.password}";

    /**
     * Mail SMTP authorization property mapping.
     */
    String MAIL_SMTP_AUTH = "${mail.smtp.auth}";

    /**
     * Mail start TLS enable property mapping.
     */
    String MAIL_START_TLS = "${mail.smtp.starttls.enable}";

    /**
     * Mail SMTP authorization property name.
     */
    String MAIL_SMTP_AUTH_NAME = "mail.smtp.auth";

    /**
     * Mail start TLS enable property name.
     */
    String MAIL_START_TLS_NAME = "mail.smtp.starttls.enable";

    /**
     * Thymeleaf template resolver prefix.
     */
    String THYMELEAF_PREFIX = "/thymeleaf/";

    /**
     * Thymeleaf template resolver suffix.
     */
    String THYMELEAF_SUFFIX = ".html";

    /* Task executors config */
    /**
     * Async task executor core pool size.
     */
    int ASYNC_EXECUTOR_CORE_POOL_SIZE = 10;

    /**
     * Async task executor max pool size.
     */
    int ASYNC_EXECUTOR_MAX_POOL_SIZE = 20;

    /**
     * Async task executor queue capacity.
     */
    int ASYNC_EXECUTOR_QUEUE_CAPACITY = 1000;

    /**
     * Async task executor thread name prefix.
     */
    String ASYNC_EXECUTOR_THREAD_PREFIX = "AsyncExecutor-";

    /**
     * Scheduled task executor destroy method.
     */
    String SCHEDULED_EXECUTOR_DESTROY_METHOD = "shutdown";

    /**
     * Scheduled task executor pool size.
     */
    int SCHEDULED_EXECUTOR_POOL_SIZE = 10;

    /* Web config */
    /**
     * Tiles Definitions location.
     */
    String TILES_DEFINITIONS_LOCATION = "/WEB-INF/defs/general.xml";

    /**
     * Message bundle source base name.
     */
    String MESSAGE_BOUNDLE_BASE_NAME = "/i118n/messages";

    /**
     * Locale change intercepter param name.
     */
    String LOCALE_CHANGE_INTERCEPTER_PARAM = "lang";

    /**
     * Web resources handler pattern.
     */
    String WEB_RESOURCES_HANDLER_PATTERN = "/resources/**";

    /**
     * Web resources handler location.
     */
    String WEB_RESOURCES_HANDLER_LOCATION = "/resources/";

    /**
     * Image resources handler pattern.
     */
    String IMAGE_RESOURCES_HANDLER_PATTERN = "/images/**";

    /**
     * Image resources handler location.
     */
    String IMAGE_RESOURCES_HANDLER_LOCATION = "file:${catalina.home}/images/";

    /**
     * Resources handlers cache period.
     */
    int RESOURCES_HANDLERS_CACHE_PERIOD = 3600;

    /* Web security config */
    /**
     * BCrypt password encoder password strength.
     */
    int BC_CRYPT_PASS_STRENGTH = 10;

    /**
     * Login page URL.
     */
    String LOGIN_URL = "/login";

    /**
     * Login failure page URL.
     */
    String LOGIN_FAILURE_URL = "/login?fail=true";

    /**
     * Remember me key.
     */
    String REMEMBER_ME_KEY = "Scheduler_app";

    /**
     * Remember me token validity period.
     */
    int REMEMBER_ME_TOKEN_LIFE_TIME = 2592000;
}
