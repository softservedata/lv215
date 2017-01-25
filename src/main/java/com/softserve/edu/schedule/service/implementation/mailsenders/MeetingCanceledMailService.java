/* MeetingCanceledMailService 1.0 01/23/2017 */
package com.softserve.edu.schedule.service.implementation.mailsenders;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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
public class MeetingCanceledMailService {

    /**
     * JavaMailSender example to provide mail sending.
     */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * Send mail notifications to the meetings owners if meeting is cancelled
     * because of room delete.
     * 
     * @param meetingForMailDTO
     *            a DTO object which contains mail message parameters.
     */
    @Async
    public void sendInfoMessageRoomDeletion(
            final MeetingCompactDTO meetingForMailDTO) {
        String reason = "the unavailability of the selected room";
        sendInfoMessageMeetingCanceled(meetingForMailDTO, reason);
    }

    /**
     * Send mail notifications to the meetings owners if meeting is cancelled
     * because of provided reason.
     * 
     * @param meetingForMailDTO
     *            a DTO object which contains mail message parameters.
     * 
     * @param reason
     *            reason of meeting canceling.
     */
    private void sendInfoMessageMeetingCanceled(
            final MeetingCompactDTO meetingForMailDTO, final String reason) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {

                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(meetingForMailDTO.getOwnerMail()));
                mimeMessage.setFrom(
                        new InternetAddress("scheduler-support@e-shop.com"));
                mimeMessage.setSubject("Meeting cancelled");
                mimeMessage
                        .setText("Dear " + meetingForMailDTO.getOwnerFullName()
                                + ", unfortunately your meeting with subject "
                                + meetingForMailDTO.getSubjectName()
                                + " planned on " + meetingForMailDTO.getDate()
                                + " at " + meetingForMailDTO.getStartTime()
                                + " in " + meetingForMailDTO.getRoomName()
                                + " was canceled because of " + reason
                                + ". Please correct this meeting.");
            }
        };
        try {
            this.mailSender.send(preparator);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
