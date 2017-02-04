package com.softserve.edu.schedule.service.implementation.mailsenders;

public interface MailConstants {

    /**
     * Meeting cancelled message subject.
     */
    String MEETING_CANCELLED_MESSAGE_SUBJECT = "meetingCanceled.messageSubject";

    /**
     * Default message from attribute.
     */
    String DEFAULT_MESSAGE_FROM_ADDRESS = "${mail.from}";

    /**
     * Default velocity template encoding.
     */
    String DEFAULT_MESSAGE_ENCODING = "UTF-8";

    /**
     * Meeting cancelled message template for EN locale location.
     */
    String MEETING_CANCELLED_TEMPLATE = "meetingCancelledDueRoomUnavailability";
    
    /**
     * Model name for meeting cancelled template.
     */
    String MEETING_MODEL_NAME = "meeting";
    
    String USER_MODEL_NAME = "user";
    
    String REGISTRATION_MESSAGE_SUBJECT = "userRegistrated.messageSubject";
    
    String USER_REGISTRATED_TEMPLATE = "userRegistrated";
    
}
