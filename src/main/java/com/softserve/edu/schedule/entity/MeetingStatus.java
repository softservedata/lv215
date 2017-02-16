/* MeetingStatus 1.0 12/25/2016 */
package com.softserve.edu.schedule.entity;

/**
 * An enumeration of meeting statuses.
 *
 * @version 1.0 25 December 2016
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public enum MeetingStatus {

    /**
     * Meeting status values.
     */
    APPROVED("ms.APPROVED"), DISAPPROVED("ms.DISAPPROVED"),
    FINISHED("ms.FINISHED"), NOT_APPROVED("ms.NOTAPPROVED");

    /**
     * Meeting status message code for localization purposes.
     */
    private String messageCode;

    /**
     * Meeting status constructor.
     *
     * @param messageCode
     *            message code
     */
    MeetingStatus(final String messageCode) {
        this.messageCode = messageCode;
    }

    /**
     * Message code getter.
     *
     * @return message code
     */
    public String getMessageCode() {
        return messageCode;
    }

}
