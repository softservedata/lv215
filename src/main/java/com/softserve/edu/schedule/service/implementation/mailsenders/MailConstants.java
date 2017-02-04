/* ControllerConst 1.0 01/29/2017 */
package com.softserve.edu.schedule.service.implementation.mailsenders;

/**
 * An interface to storage mail senders attributes.
 *
 * @version 1.0 29 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
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

}
