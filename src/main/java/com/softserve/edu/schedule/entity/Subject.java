/* Subject 1.0 12/25/2016 */
package com.softserve.edu.schedule.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * An entity class for subjects of meetings.
 *
 * @version 1.0 25 December 2016
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Entity
public class Subject {

    /**
     * Id for database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Subject name.
     */
    private String name;

    /**
     * Subject description.
     */
    @Lob
    private String description;

    /**
     * List of users who can create meetings with this subject.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    /**
     * List of meetings with this subject.
     */
    @OneToMany(mappedBy = "subject")
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
