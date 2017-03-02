package com.softserve.edu.schedule.service.implementation.mailsenders;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

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

import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.User;

/**
 * Provides mail notifications about meeting changing status: from APPROVED - to
 * DISAPPROVED or NOT APPROVED.
 *
 * @version 1.0 04 February 2017
 *
 * @author Bohdan Melnyk
 */
@Component
public class MeetingChangeStatusMailService {

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
     * Send mail notifications to all group members when group has been deleted.
     *
     * @param meeting
     *            meeting to message.
     *
     * @param locale
     *            current locale.
     */
    @Async
    public void sendInfoMessageAboutMeetingStatusChanging(final Meeting meeting,
            final Locale locale) {
        Context ctx = new Context(locale);
        ctx.setVariable(MailConstants.MEETING_MODEL_NAME, meeting);

        Set<User> curators = new HashSet<User>();
        curators.add(meeting.getOwner());
        meeting.getGroups()
                .forEach(e -> e.getUsers().forEach(u -> curators.add(u)));

        for (User member : curators) {
            ctx.setVariable(MailConstants.MEETING_GROUP_CURATOR, member);

            try {
                MimeMessage mimeMessage = this.mailSender.createMimeMessage();
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage,
                        true, MailConstants.DEFAULT_MESSAGE_ENCODING);
                message.setTo(member.getMail());
                message.setFrom(new InternetAddress(fromAddress));
                message.setSubject(messageSource.getMessage(
                        MailConstants.MEETING_CHANGEDSTATUS_MESSAGE,
                        new String[0], locale));
                String htmlContent = this.templateEngine.process(
                        MailConstants.MEETING_CHANGESTATUS_TEMPLATE, ctx);
                message.setText(htmlContent, true);
                this.mailSender.send(mimeMessage);
            } catch (MessagingException e) {
                throw new MailPreparationException(e);
            }
        }
    }
}
