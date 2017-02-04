/* MeetingCanceledMailService 1.0 01/23/2017 */
package com.softserve.edu.schedule.service.implementation.mailsenders;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.softserve.edu.schedule.dto.MeetingCompactDTO;

/**
 * A class to provide mail notifications of the meetings owners about cancelled
 * meetings.
 *
 * @version 1.0 23 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Service
public class MeetingCanceledMailService implements MailConstants {

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
    @Value(DEFAULT_MESSAGE_FROM_ADDRESS)
    private String fromAddress;

    /**
     * Send mail notifications to the meetings owners if meeting is cancelled
     * because of room delete.
     *
     * @param meetingCompactDTO
     *            a DTO object which contains mail message parameters.
     *
     * @param locale
     *            current locale.
     */
    @Async
    public void sendInfoMessageRoomDeletion(
            final MeetingCompactDTO meetingCompactDTO, final Locale locale) {

        Context ctx = new Context(locale);
        ctx.setVariable(MEETING_MODEL_NAME, meetingCompactDTO);

        try {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
                    DEFAULT_MESSAGE_ENCODING);
            message.setTo(
                    new InternetAddress(meetingCompactDTO.getOwnerMail()));
            message.setFrom(new InternetAddress(fromAddress));
            message.setSubject(messageSource.getMessage(
                    MEETING_CANCELLED_MESSAGE_SUBJECT, new String[0], locale));
            String htmlContent = this.templateEngine
                    .process(MEETING_CANCELLED_TEMPLATE, ctx);
            message.setText(htmlContent, true);
            this.mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
