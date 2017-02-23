/*
 * DeleteMeetingMailService 23.02.2017.
 */
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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.softserve.edu.schedule.dto.MeetingDTO;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.service.implementation.dtoconverter.MeetingDTOConverter;
import com.softserve.edu.schedule.service.implementation.dtoconverter.UserDTOConverter;

/**
 * Provides mail notifications of meeting deletion.
 *
 * @version 1.0 04 February 2017
 *
 * @author Bohdan Melnyk
 */
@Component
public class DeleteMeetingMailService implements MailConstants {

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
     * MeetingDTOConverter example to provide ability to convert from meeting to
     * meetingDTO.
     */
    @Autowired
    MeetingDTOConverter meetingDTOconverter;

    /**
     * UserDTOConverter example to provide ability to convert from meeting to
     * meetingDTO.
     */
    @Autowired
    UserDTOConverter userDTOConverter;

    /**
     * Field for import from message attribute from mail.properties.
     */
    @Value(DEFAULT_MESSAGE_FROM_ADDRESS)
    private String fromAddress;

    /**
     * Send mail notifications to all group members when group has been deleted
     *
     * @param Meeting
     *            a Meeting object that contains mail message parameters.
     *
     * @param locale
     *            current locale.
     */
    public void sendInfoMessageAboutMeetingDeletetion(final Meeting meeting,
            final Locale locale) {
        Set<User> curators = new HashSet<User>();
        curators.add(meeting.getOwner());

        meeting.getGroups()
                .forEach(e -> e.getUsers().forEach(u -> curators.add(u)));
        curators.forEach(e -> sendmail(userDTOConverter.getDTO(e),
                meetingDTOconverter.getDTO(meeting), locale));
    }

    /**
     * Send mail notifications to all group members when group has been deleted
     *
     * @param Meeting
     *            a Meeting object that contains mail message parameters.
     *
     * @param locale
     *            current locale.
     */
    @Async
    @Transactional(readOnly = true)
    private void sendmail(final UserDTO userDTO, final MeetingDTO meetingDTO,
            final Locale locale) {
        Context ctx = new Context(locale);
        ctx.setVariable(MEETING_MODEL_NAME, meetingDTO);
        ctx.setVariable(MEETING_GROUP_CURATOR, userDTO);
        try {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
                    DEFAULT_MESSAGE_ENCODING);
            message.setTo(userDTO.getMail());
            message.setFrom(new InternetAddress(fromAddress));
            message.setSubject(messageSource.getMessage(MEETING_DELETED_MESSAGE,
                    new String[0], locale));

            String htmlContent = this.templateEngine
                    .process(MEETING_DELETED_TEMPLATE, ctx);
            message.setText(htmlContent, true);
            this.mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
