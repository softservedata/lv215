package com.softserve.edu.schedule.dto;

import java.time.LocalDate;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import com.softserve.edu.schedule.entity.MeetingStatus;
import com.softserve.edu.schedule.service.implementation.validators.MeetingValidator;
import com.softserve.edu.schedule.service.implementation.validators.SubjectValidator;


public class MeetingDTO {

    /**
     * Id for database.
     */
    private Long id;

    /**
     * Field for storage subject of the meeting.
     */
    private SubjectDTO subject;

    /**
     * Field for storage room of the meeting.
     */
    private RoomDTO room;

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
     * User groups which participate in meeting.
     */
    private List<UserGroupDTO> groups = new ArrayList<>();

    /**
     * Field for storage user owner of the meeting.
     */
    private UserDTO owner;

    /**
     * Status of the meeting.
     */
    private MeetingStatus status;

    /**
     * Level of the meeting. The higher the value - the more important meeting.
     */
    @Size(min=1)
    private Integer level;

    /**
     * Description of the meeting.
     */
  
    private String description;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the subject
     */
    public SubjectDTO getSubject() {
        return subject;
    }

    /**
     * @return the room
     */
    public RoomDTO getRoom() {
        return room;
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
    public List<UserGroupDTO> getGroups() {
        return groups;
    }

    /**
     * @return the owner
     */
    public UserDTO getOwner() {
        return owner;
    }

    /**
     * @return the status
     */
    public MeetingStatus getStatus() {
        return status;
    }

    /**
     * @return the level
     */
    public Integer getLevel() {
        return level;
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
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param subject
     *            the subject to set
     */
    public void setSubject(SubjectDTO subject) {
        this.subject = subject;
    }

    /**
     * @param room
     *            the room to set
     */
    public void setRoom(RoomDTO room) {
        this.room = room;
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
     * @param groups
     *            the groups to set
     */
    public void setGroups(List<UserGroupDTO> groups) {
        this.groups = groups;
    }

    /**
     * @param owner
     *            the owner to set
     */
    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(MeetingStatus status) {
        this.status = status;
    }

    /**
     * @param level
     *            the level to set
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
