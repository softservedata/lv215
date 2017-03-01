/* MeetingHistoryDTO 1.0 02/21/2017 */
package com.softserve.edu.schedule.dto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A DTO class to transport MeetingHistory data.
 *
 * @version 1.0 17 January 2017
 *
 * @author Bohdan Melnyk.
 *
 * @since 1.8
 */
public class MeetingHistoryDTO {

    /**
     * Id for database.
     */
    private Long id;

    /**
     * Id for the real meeting from meeting entity.
     */
    private String idMeeting;

    /**
     * The name of subject of this meetingHistory.
     */
    private String subject;

    /**
     * The name of room of this meetingHistory.
     */
    private String room;

    /**
     * The name of location of this meetingHistory.
     */
    private String location;

    /**
     * Address of subject of this meetingHistory.
     */
    private String address;

    /**
     * Date of subject of this meetingHistory.
     */
    private LocalDate date;

    /**
     * StartTime of subject of this meetingHistory.
     */
    private LocalTime startTime;

    /**
     * EndTime of subject of this meetingHistory.
     */
    private LocalTime endTime;

    /**
     * The list of groups of this meetingHistory.
     */
    private String groups;

    /**
     * The name of meeting owner of this meetingHistory.
     */
    private String owner;

    /**
     * The description of this meetingHistory.
     */
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getIdMeeting() {
        return idMeeting;
    }

    public void setIdMeeting(final String idMeeting) {
        this.idMeeting = idMeeting;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(final String subject) {
        this.subject = subject;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(final String room) {
        this.room = room;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(final LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(final LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(final String groups) {
        this.groups = groups;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(final String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

}
