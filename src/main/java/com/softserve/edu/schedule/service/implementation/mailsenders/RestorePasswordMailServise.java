package com.softserve.edu.schedule.service.implementation.mailsenders;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.softserve.edu.schedule.dto.UserDTOForRestorePassword;
import com.softserve.edu.schedule.entity.User;

/**
 * A class to provide mail notifications with new password.
 *
 * @version 1.0 15 February 2017
 *
 * @author Serhiy Dudynsky
 *
 * @since 1.8
 */
@Component
public class RestorePasswordMailServise {

    /**
     * JavaMailSender example to provide mail sending.
     */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * Messages source for internationalization purposes.
     */
    @Autowired
    private ResourceBundleMessageSource messageSource;

    /**
     * SpringTemplateEngine example for templates processing.
     */
    @Autowired
    private SpringTemplateEngine templateEngine;

    /**
     * Field for import from message attribute from mail properties.
     */
    @Value(MailConstants.DEFAULT_MESSAGE_FROM_ADDRESS)
    private String fromAddress;

    /**
     * Send mail notifications to the new user when user has been registered.
     *
     * @param userDTO
     *            UserDTOForRestorePassword instance
     * @param newPassword
     *            a new password
     * @param user
     *            a new user parameters.
     * @param locale
     *            current locale.
     */
    @Async
    public void sendInfoMessageRestorePassword(
            final UserDTOForRestorePassword userDTO, final Locale locale,
            final String newPassword, final User user) {
        Context ctx = new Context(locale);
        ctx.setVariable(MailConstants.USER_MODEL_NAME, user);
        ctx.setVariable(MailConstants.PASSWORD, newPassword);

        try {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
                    MailConstants.DEFAULT_MESSAGE_ENCODING);
            message.setTo(new InternetAddress(user.getMail()));
            message.setFrom(new InternetAddress(fromAddress));
            message.setSubject(messageSource.getMessage(
                    MailConstants.RESTORE_PASSWORD_MESSAGE_SUBJECT,
                    new String[0], locale));
            String htmlContent = this.templateEngine
                    .process(MailConstants.RESTORE_TEMPLATE, ctx);
            message.setText(htmlContent, true);
            this.mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new MailPreparationException(e);
        }
    }
}
