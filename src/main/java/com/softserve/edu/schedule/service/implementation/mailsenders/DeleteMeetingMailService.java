package com.softserve.edu.schedule.service.implementation.mailsenders;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserGroup;

/**
 * Provides mail notifications of group members when group has been deleted
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
     * Field for import from message attribute from mail.properties.
     */
    @Value(DEFAULT_MESSAGE_FROM_ADDRESS)
    private String fromAddress;

    /**
     * Send mail notifications to all group members when group has been deleted
     *
     * @param userGroup
     *            a UserGroup object that contains mail message parameters.
     *
     * @param locale
     *            current locale.
     */
    @Async
    public void sendInfoMessageAboutMeetingDeletetion(final Meeting meeting,
            final Locale locale) {
        
        System.out.println("Starts mail sender");
        
        Context ctx = new Context(locale);
        ctx.setVariable(MEETING_MODEL_NAME, meeting);

        List<User> curators = new ArrayList<User>();
        User owner = meeting.getOwner();
        curators.add(owner);
        
        for (UserGroup userGroup : meeting.getGroups()) {
            User userTemp = userGroup.getCurator();
            if (userTemp != owner) {
                curators.add(userGroup.getCurator());
            }
        }

        for (User member : curators) {
            ctx.setVariable(MEETING_GROUP_CURATOR, member);

            
            try {
                
                System.out.println("In try block");
                
                
                MimeMessage mimeMessage = this.mailSender.createMimeMessage();
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage,
                        true, DEFAULT_MESSAGE_ENCODING);
                
                System.out.println("Before sending");
                
                message.setTo(member.getMail());
                message.setFrom(new InternetAddress(fromAddress));
                message.setSubject(messageSource.getMessage(
                        MEETING_DELETED_MESSAGE, new String[0], locale));
                
                String htmlContent = this.templateEngine
                        .process(MEETING_DELETED_TEMPLATE, ctx);
                message.setText(htmlContent, true);
                this.mailSender.send(mimeMessage);
                
                System.out.println("After sending");
                
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

}
