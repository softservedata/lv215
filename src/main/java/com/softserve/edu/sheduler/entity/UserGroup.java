package com.softserve.edu.sheduler.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private String description;

    private int level;

    @ManyToOne(fetch = FetchType.LAZY)
    private User curator;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Meeting> meetings = new ArrayList<>();

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return the curator
     */
    public User getCurator() {
        return curator;
    }

    /**
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * @return the meetings
     */
    public List<Meeting> getMeetings() {
        return meetings;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param level
     *            the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @param curator
     *            the curator to set
     */
    public void setCurator(User curator) {
        this.curator = curator;
    }

    /**
     * @param users
     *            the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * @param meetings
     *            the meetings to set
     */
    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

}
