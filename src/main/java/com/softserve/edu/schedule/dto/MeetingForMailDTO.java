/* MeetingForMailDTO 1.0 01/22/2017 */
package com.softserve.edu.schedule.dto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A DTO class to transport meetings data for mail sending services.
 *
 * @version 1.0 22 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public class MeetingForMailDTO {

    /**
     * Field for storage subject name of the meeting.
     */
    private String subjectName;

    /**
     * Field for storage room name of the meeting.
     */
    private String roomName;

    /**
     * Date of the meeting.
     */
    private LocalDate date;

    /**
     * Start time of the meeting.
     */
    private LocalTime startTime;

    /**
     * End time of the meeting.
     */
    private LocalTime endTime;

    /**
     * Field for storage fullName of the owner of the meeting.
     */
    private String ownerFullName;

    /**
     * Field for storage mail of the owner of the meeting.
     */
    private String ownerMail;

    /**
     * @return the subjectName
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * @return the roomName
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @return the startTime
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * @return the endTime
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * @return the ownerFullName
     */
    public String getOwnerFullName() {
        return ownerFullName;
    }

    /**
     * @return the ownerMail
     */
    public String getOwnerMail() {
        return ownerMail;
    }

    /**
     * @param subjectName
     *            the subjectName to set
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * @param roomName
     *            the roomName to set
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * @param ownerFullName
     *            the ownerFullName to set
     */
    public void setOwnerFullName(String ownerFullName) {
        this.ownerFullName = ownerFullName;
    }

    /**
     * @param ownerMail
     *            the ownerMail to set
     */
    public void setOwnerMail(String ownerMail) {
        this.ownerMail = ownerMail;
    }

}
