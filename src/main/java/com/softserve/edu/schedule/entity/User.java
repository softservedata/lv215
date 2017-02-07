/* User 1.0 12/25/2016 */
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.softserve.edu.schedule.entitylisteners.UserEntityListener;

/**
 * An entity class for users.
 *
 * @version 1.0 25 December 2016
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Entity
@EntityListeners(UserEntityListener.class)
public class User {

    /**
     * Id for database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User first name.
     */
    private String firstName;

    /**
     * User last name.
     */
    private String lastName;

    /**
     * User e-mail. Also as login usage.
     */
    private String mail;

    /**
     * User password. Stored encrypted.
     */
    private String password;

    /**
     * User phone.
     */
    private String phone;

    /**
     * User position. For general info purpose.
     */
    private String position;

    private String pathImage;

    /**
     * User status in system.
     */
    @Enumerated
    private UserStatus status = UserStatus.NEW_USER;

    /**
     * User role in system.
     */
    @Enumerated
    private UserRole role = UserRole.ROLE_USER;

    /**
     * List of subjects available for this user.
     */
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Subject.class)
    @JoinTable(name = "subject_user",
            joinColumns = {@JoinColumn(name = "users_id")},
            inverseJoinColumns = {@JoinColumn(name = "subjects_id")})
    private List<Subject> subjects = new ArrayList<>();

    /**
     * List of groups this user participates.
     */
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = UserGroup.class)
    @JoinTable(name = "usergroup_user",
            joinColumns = {@JoinColumn(name = "users_id")},
            inverseJoinColumns = {@JoinColumn(name = "groups_id")})
    private List<UserGroup> groups = new ArrayList<>();

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @return the status
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * @return the role
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * @return the subjects
     */
    public List<Subject> getSubjects() {
        return subjects;
    }

    /**
     * @return the groups
     */
    public List<UserGroup> getGroups() {
        return groups;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * @param mail
     *            the mail to set
     */
    public void setMail(final String mail) {
        this.mail = mail;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * @param phone
     *            the phone to set
     */
    public void setPhone(final String phone) {
        this.phone = phone;
    }

    /**
     * @param position
     *            the position to set
     */
    public void setPosition(final String position) {
        this.position = position;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(final UserStatus status) {
        this.status = status;
    }

    /**
     * @param role
     *            the role to set
     */
    public void setRole(final UserRole role) {
        this.role = role;
    }

    /**
     * @param subjects
     *            the subjects to set
     */
    public void setSubjects(final List<Subject> subjects) {
        this.subjects = subjects;
    }

    /**
     * @param groups
     *            the groups to set
     */
    public void setGroups(final List<UserGroup> groups) {
        this.groups = groups;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

}
