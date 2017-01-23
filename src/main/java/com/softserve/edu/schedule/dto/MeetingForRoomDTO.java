/* MeetingForRoomDTO 1.0 01/22/2017 */
package com.softserve.edu.schedule.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.schedule.entity.MeetingStatus;

/**
 * A DTO class to transport meetings data for rooms view.
 *
 * @version 1.0 22 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public class MeetingForRoomDTO {

    /**
     * Id for database.
     */
    private Long id;

    /**
     * Meeting subject name.
     */
    private String subjectName;

    /**
     * Meeting owner full name.
     */
    private String ownerFullName;

    /**
     * List of meeting groups names.
     */
    private List<String> groupsNames = new ArrayList<>();

    /**
     * Start time of the meeting.
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
     * Status of the meeting.
     */
    private MeetingStatus status;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the subjectName
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * @return the ownerFullName
     */
    public String getOwnerFullName() {
        return ownerFullName;
    }

    /**
     * @return the groupsNames
     */
    public List<String> getGroupsNames() {
        return groupsNames;
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
     * @return the status
     */
    public MeetingStatus getStatus() {
        return status;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param subjectName
     *            the subjectName to set
     */
    public void setSubjectName(final String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * @param ownerFullName
     *            the ownerFullName to set
     */
    public void setOwnerFullName(final String ownerFullName) {
        this.ownerFullName = ownerFullName;
    }

    /**
     * @param groupsNames
     *            the groupsNames to set
     */
    public void setGroupsNames(final List<String> groupsNames) {
        this.groupsNames = groupsNames;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(final LocalDate date) {
        this.date = date;
    }

    /**
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(final LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(final LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(final MeetingStatus status) {
        this.status = status;
    }

}
