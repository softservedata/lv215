package com.softserve.edu.schedule.service.implementation.mailsenders;

public interface MailConstants {

    /**
     * Meeting cancelled message subject.
     */
    String MEETING_CANCELLED_MESSAGE_SUBJECT = "Meeting cancelled";

    /**
     * Default message from attribute.
     */
    String DEFAULT_MESSAGE_FROM_ADDRESS = "${mail.from}";

    /**
     * Default velocity template encoding.
     */
    String DEFAULT_VELOCITY_ENCODING = "UTF-8";

    /**
     * Meeting cancelled message template location.
     */
    String MEETING_CANCELLED_TEMPLATE = "velocity/meetingCancelledDueRoomUnavailability.vm";

    /**
     * Model name for meeting cancelled template.
     */
    String MEETING_MODEL_NAME = "meeting";
}
