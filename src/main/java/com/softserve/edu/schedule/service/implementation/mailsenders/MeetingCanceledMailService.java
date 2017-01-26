/* MeetingCanceledMailService 1.0 01/23/2017 */
package com.softserve.edu.schedule.service.implementation.mailsenders;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

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
     * VelocityEngine example to provide mail templates using.
     */
    @Autowired
    private VelocityEngine velocityEngine;

    /**
     * Field for import from message attribute from mail.properties
     */
    @Value(DEFAULT_MESSAGE_FROM_ADDRESS)
    private String fromAddress;

    /**
     * Send mail notifications to the meetings owners if meeting is cancelled
     * because of room delete.
     * 
     * @param meetingCompactDTO
     *            a DTO object which contains mail message parameters.
     */
    @Async
    public void sendInfoMessageRoomDeletion(
            final MeetingCompactDTO meetingCompactDTO) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(
                        new InternetAddress(meetingCompactDTO.getOwnerMail()));
                message.setFrom(new InternetAddress(fromAddress));
                message.setSubject(MEETING_CANCELLED_MESSAGE_SUBJECT);
                Map<String, Object> model = new HashMap<>();
                model.put(MEETING_MODEL_NAME, meetingCompactDTO);
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, MEETING_CANCELLED_TEMPLATE,
                        DEFAULT_VELOCITY_ENCODING, model);
                message.setText(text, true);
            }
        };
        try {
            this.mailSender.send(preparator);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
