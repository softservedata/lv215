package com.softserve.edu.sheduler.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "meetings")
    private List<UserGroup> groups = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @Enumerated
    private MeetingStatus status;

    private int level;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the subject
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * @return the room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * @return the startTime
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * @return the endTime
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * @return the groups
     */
    public List<UserGroup> getGroups() {
        return groups;
    }

    /**
     * @return the owner
     */
    public User getOwner() {
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
    public int getLevel() {
        return level;
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
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
     * @param room
     *            the room to set
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * @param groups
     *            the groups to set
     */
    public void setGroups(List<UserGroup> groups) {
        this.groups = groups;
    }

    /**
     * @param owner
     *            the owner to set
     */
    public void setOwner(User owner) {
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
    public void setLevel(int level) {
        this.level = level;
    }
}
