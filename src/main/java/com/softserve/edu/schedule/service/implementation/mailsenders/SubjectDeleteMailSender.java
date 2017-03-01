/*
 * DeleteLocationMailService
 * 1.0
 * 6 Feb 2017
 * Copyright (c) Ped'ko Volodymyr
 */
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

import com.softserve.edu.schedule.entity.Subject;
import com.softserve.edu.schedule.entity.User;

/**
 * A class to provide mail notification for tutors about deleting subject.
 *
 * @version 1.0 6 February 2017
 *
 * @author Ped'ko Volodymyr
 *
 * @since 1.8
 */
@Component
public class SubjectDeleteMailSender {

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
     * Field for import from message attribute from mail.properties.
     */
    @Value(MailConstants.DEFAULT_MESSAGE_FROM_ADDRESS)
    private String fromAddress;

    /**
     * Send mail notifications to all tutors when subject has been deleted.
     *
     * @param subject
     *            a Subject object that contains mail message parameters.
     * @param user
     *            user to inform
     * @param locale
     *            current locale.
     */
    @Async
    public void sendInfoMessageSubjectDelete(final Subject subject,
            final User user, final Locale locale) {
        Context ctx = new Context(locale);
        ctx.setVariable(MailConstants.SUBJECT_TUTOR_MODEL_NAME, user);
        ctx.setVariable(MailConstants.SUBJECT_MODEL_NAME, subject);
        try {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
                    MailConstants.DEFAULT_MESSAGE_ENCODING);
            message.setTo(new InternetAddress(user.getMail()));
            message.setFrom(new InternetAddress(fromAddress));
            message.setSubject(messageSource.getMessage(
                    MailConstants.SUBJECT_DELETED_MESSAGE, new String[0],
                    locale));
            String htmlContent = this.templateEngine
                    .process(MailConstants.SUBJECT_DELETED_TEMPLATE, ctx);
            message.setText(htmlContent, true);
            this.mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new MailPreparationException(e);
        }
    }
}
