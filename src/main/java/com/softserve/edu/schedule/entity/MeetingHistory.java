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
 * @author Bohdan Melnyk, Oleksandr Butyter
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdMeeting() {
        return idMeeting;
    }

    public void setIdMeeting(String idMeeting) {
        this.idMeeting = idMeeting;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
