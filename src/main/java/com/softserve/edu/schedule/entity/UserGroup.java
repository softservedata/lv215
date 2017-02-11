/* UserGroup 1.0 12/25/2016 */
package com.softserve.edu.schedule.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.softserve.edu.schedule.entitylisteners.UserGroupEntityListener;

/**
 * An entity class for user groups.
 *
 * @version 1.0 25 December 2016
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Entity
@EntityListeners(UserGroupEntityListener.class)
@Table(indexes = {@Index(columnList = "name")})
public class UserGroup {

    /**
     * Id for database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User group name.
     */
    private String name;

    /**
     * User group description.
     */
    @Lob
    private String description;

    /**
     * Level of the group. The higher the value - the more important group.
     */
    @Enumerated
    private UserGroupLevel level;

    /**
     * User group manager.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private User curator;

    /**
     * List of users in this group.
     */
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinTable(name = "usergroup_user",
            joinColumns = {@JoinColumn(name = "groups_id")},
            inverseJoinColumns = {@JoinColumn(name = "users_id")})
    private List<User> users = new ArrayList<>();

    /**
     * List of meetings of this group.
     */
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Meeting.class)
    @JoinTable(name = "usergroup_meeting",
            joinColumns = {@JoinColumn(name = "groups_id")},
            inverseJoinColumns = {@JoinColumn(name = "meetings_id")})
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
    public UserGroupLevel getLevel() {
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
        if (users == null) {
            return new ArrayList<>();
        }
        return users;
    }

    /**
     * @return the meetings
     */
    public List<Meeting> getMeetings() {
        if (meetings == null) {
            return new ArrayList<>();
        }
        return meetings;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @param level
     *            the level to set
     */
    public void setLevel(final UserGroupLevel level) {
        this.level = level;
    }

    /**
     * @param curator
     *            the curator to set
     */
    public void setCurator(final User curator) {
        this.curator = curator;
    }

    /**
     * @param users
     *            the users to set
     */
    public void setUsers(final List<User> users) {
        this.users = users;
    }

    /**
     * @param meetings
     *            the meetings to set
     */
    public void setMeetings(final List<Meeting> meetings) {
        this.meetings = meetings;
    }

}
