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

    APPROVED("ms.APPROVED"), DISAPPROVED("ms.DISAPPROVED"), 
    FINISHED("ms.FINISHED"), NOT_APPROVED("ms.NOTAPPROVED");
    private String messageCode;

    private MeetingStatus(final String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageCode() {
        return messageCode;
    }

}
