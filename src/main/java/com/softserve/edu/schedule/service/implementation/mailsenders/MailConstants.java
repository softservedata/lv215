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
    String DEFAULT_VELOCITY_ENCODING = "UTF-8";

    /**
     * Meeting cancelled message template for EN locale location.
     */
    String MEETING_CANCELLED_TEMPLATE_EN = "velocity/meetingCancelledDueRoomUnavailability_en.vm";
    
    /**
     * Meeting cancelled message template for RU locale location.
     */
    String MEETING_CANCELLED_TEMPLATE_RU = "velocity/meetingCancelledDueRoomUnavailability_ru.vm";
    
    /**
     * Meeting cancelled message template for UA locale location.
     */
    String MEETING_CANCELLED_TEMPLATE_UA = "velocity/meetingCancelledDueRoomUnavailability_ua.vm";

    /**
     * Model name for meeting cancelled template.
     */
    String MEETING_MODEL_NAME = "meeting";
}
