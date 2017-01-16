package com.softserve.edu.schedule.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.schedule.entity.MeetingStatus;
import com.softserve.edu.schedule.entity.Room;
import com.softserve.edu.schedule.entity.Subject;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserGroup;

public class MeetingDTO {

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
     * Start time of the meeting.
     */
    private LocalDateTime startTime;

    /**
     * End time of the meeting.
     */
    private LocalDateTime endTime;

    /**
     * Field for storage user groups which participate in meeting.
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
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the subject
     */
    public SubjectDTO getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(SubjectDTO subject) {
        this.subject = subject;
    }

    /**
     * @return the room
     */
    public RoomDTO getRoom() {
        return room;
    }

    /**
     * @param room the room to set
     */
    public void setRoom(RoomDTO room) {
        this.room = room;
    }

    /**
     * @return the startTime
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the groups
     */
    public List<UserGroupDTO> getGroups() {
        return groups;
    }

    /**
     * @param groups the groups to set
     */
    public void setGroups(List<UserGroupDTO> groups) {
        this.groups = groups;
    }

    /**
     * @return the owner
     */
    public UserDTO getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

    /**
     * @return the status
     */
    public MeetingStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(MeetingStatus status) {
        this.status = status;
    }

    /**
     * @return the level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
}
