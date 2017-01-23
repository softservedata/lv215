package com.softserve.edu.schedule.service.mailsenders;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.entity.Meeting;

@Service
public class MeetingCanceledMailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendInfoMessageRoomDeletion(final Meeting meeting) {
        String reason = "the unavailability of the selected room";
        sendInfoMessageMeetingCanceled(meeting, reason);
    }

    private void sendInfoMessageMeetingCanceled(final Meeting meeting,
            final String reason) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {

                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(meeting.getOwner().getMail()));
                mimeMessage.setFrom(
                        new InternetAddress("scheduler-support@e-shop.com"));
                mimeMessage.setSubject("Meeting cancelled");
                mimeMessage.setText("Dear " + meeting.getOwner().getFirstName()
                        + " " + meeting.getOwner().getLastName()
                        + ", unfortunately your meeting " + meeting.getDate()
                        + " at " + meeting.getStartTime()
                        + "was canceled because of " + reason
                        + " Please correct this meeting.");
            }
        };
        try {
            this.mailSender.send(preparator);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
