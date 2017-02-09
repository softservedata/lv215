/* MailSendingConfig 1.0 02/07/2017 */
package com.softserve.edu.schedule.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * Configuration of mail sending service of application.
 *
 * @version 1.0 07 February 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Configuration
@PropertySource({ConfigConstants.MAIL_CONFIG_PROPERTIES})
public class MailSendingConfig {

    /**
     * Mail host property name.
     */
    @Value(ConfigConstants.MAIL_HOST)
    private String mailHost;

    /**
     * Mail port property name.
     */
    @Value(ConfigConstants.MAIL_PORT)
    private String mailPort;

    /**
     * Mail username property name.
     */
    @Value(ConfigConstants.MAIL_USERNAME)
    private String mailUserName;

    /**
     * Mail password property name.
     */
    @Value(ConfigConstants.MAIL_PASSWORD)
    private String mailPassword;

    /**
     * Mail SMTP authorization property name.
     */
    @Value(ConfigConstants.MAIL_SMTP_AUTH)
    private String smtpAuth;

    /**
     * Mail start TLS enable property name.
     */
    @Value(ConfigConstants.MAIL_START_TLS)
    private String smtpStartTLS;

    /**
     * Set configured mail sender service for application.
     *
     * @return JavaMailSenderImpl example
     */
    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(Integer.parseInt(mailPort));
        mailSender.setUsername(mailUserName);
        mailSender.setPassword(mailPassword);
        Properties mailProperties = new Properties();
        mailProperties.setProperty(ConfigConstants.MAIL_SMTP_AUTH_NAME,
                smtpAuth);
        mailProperties.setProperty(ConfigConstants.MAIL_START_TLS_NAME,
                smtpStartTLS);
        mailSender.setJavaMailProperties(mailProperties);
        return mailSender;
    }

    /**
     * Set configured Spring Thymeleaf template engine for application.
     *
     * @return SpringTemplateEngine example
     */
    @Bean
    public SpringTemplateEngine emailTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(htmlTemplateResolver());
        return templateEngine;
    }

    /**
     * Set configured Thymeleaf template resolver for application.
     *
     * @return ITemplateResolver example
     */
    private ITemplateResolver htmlTemplateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix(ConfigConstants.THYMELEAF_PREFIX);
        templateResolver.setSuffix(ConfigConstants.THYMELEAF_SUFFIX);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(ConfigConstants.UTF8);
        templateResolver.setCacheable(false);
        return templateResolver;
    }
}
