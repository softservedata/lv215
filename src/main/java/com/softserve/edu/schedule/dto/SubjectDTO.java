package com.softserve.edu.schedule.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.User;

public class SubjectDTO {
    
    private Long id;

    /**
     * Subject name.
     */
    private String name;

    /**
     * Subject description.
     */
    private String description;

    /**
     * List of users who can create meetings with this subject.
     */
    private List<UserDTO> users = new ArrayList<>();

    /**
     * List of meetings with this subject.
     */
    private List<MeetingDTO> meetings = new ArrayList<>();

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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

    /**
     * @return the users
     */
    public List<UserDTO> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    /**
     * @return the meetings
     */
    public List<MeetingDTO> getMeetings() {
        return meetings;
    }

    /**
     * @param meetings the meetings to set
     */
    public void setMeetings(List<MeetingDTO> meetings) {
        this.meetings = meetings;
    }

}
