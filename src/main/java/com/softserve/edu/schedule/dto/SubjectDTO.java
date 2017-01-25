/*
 * SubjectDTO.java
 * 1.0
 * 15 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.dto;

import java.util.ArrayList;
import java.util.List;

public class SubjectDTO {

    /**
     * SubjectDTO id.
     */
    private Long id;

    /**
     * SubjectDTO name.
     */
    private String name;

    /**
     * SubjectDTO description.
     */
    private String description;

    /**
     * List of subject tutors(users).
     */
    private List<UserForSubjectDTO> users = new ArrayList<>();

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
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
     * @param name
     *            the name to set
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
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the users
     */
    public List<UserForSubjectDTO> getUsers() {
        return users;
    }

    /**
     * @param users
     *            the users to set
     */
    public void setUsers(List<UserForSubjectDTO> users) {
        if (users == null) {
            this.users = new ArrayList<>();
        } else {
            this.users = users;
        }
    }
}
