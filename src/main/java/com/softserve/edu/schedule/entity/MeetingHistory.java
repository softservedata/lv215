/* MeetingHistory entity 1.0 22/02/2017 */
package com.softserve.edu.schedule.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * An entity class for meetingHistories.
 *
 * @version 1.0 22 02 2017.
 *
 * @author Bohdan Melnyk
 *
 * @since 1.8
 */
@Entity
public class MeetingHistory {

    /**
     * Id for database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Lob
    private String description;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the idMeeting
     */
    public String getIdMeeting() {
        return idMeeting;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @return the room
     */
    public String getRoom() {
        return room;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
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
     * @return the groups
     */
    public String getGroups() {
        return groups;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @param idMeeting
     *            the idMeeting to set
     */
    public void setIdMeeting(final String idMeeting) {
        this.idMeeting = idMeeting;
    }

    /**
     * @param subject
     *            the subject to set
     */
    public void setSubject(final String subject) {
        this.subject = subject;
    }

    /**
     * @param room
     *            the room to set
     */
    public void setRoom(final String room) {
        this.room = room;
    }

    /**
     * @param location
     *            the location to set
     */
    public void setLocation(final String location) {
        this.location = location;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(final String address) {
        this.address = address;
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
     * @param groups
     *            the groups to set
     */
    public void setGroups(final String groups) {
        this.groups = groups;
    }

    /**
     * @param owner
     *            the owner to set
     */
    public void setOwner(final String owner) {
        this.owner = owner;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

}
