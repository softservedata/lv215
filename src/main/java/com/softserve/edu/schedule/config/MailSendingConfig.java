/* MailSendingConfig 1.0 02/07/2017 */
package com.softserve.edu.schedule.config;

import java.util.Properties;

import org.apache.commons.codec.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
@PropertySource({"/WEB-INF/mail.properties"})
public class MailSendingConfig {

    /**
     * Environment instance to get properties.
     */
    @Autowired
    private Environment env;

    /**
     * Set configured mail sender service for application.
     *
     * @return JavaMailSenderImpl example
     */
    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("mail.host"));
        mailSender.setPort(Integer.parseInt(env.getProperty("mail.port")));
        mailSender.setUsername(env.getProperty("mail.userName"));
        mailSender.setPassword(env.getProperty("mail.password"));
        Properties mailProperties = new Properties();
        mailProperties.setProperty("mail.smtp.auth",
                env.getProperty("mail.smtp.auth"));
        mailProperties.setProperty("mail.smtp.starttls.enable",
                env.getProperty("mail.smtp.starttls.enable"));
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
        templateResolver.setPrefix("/thymeleaf/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(CharEncoding.UTF_8);
        templateResolver.setCacheable(false);
        return templateResolver;
    }
}
