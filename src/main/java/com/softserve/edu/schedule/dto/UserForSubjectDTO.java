/*
 * SubjectDTO.java
 * 1.0
 * 15 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.dto;

public class UserForSubjectDTO {

    /**
     * UserForSubjectDTO id.
     */
    private Long id;
    
    /**
     * UserForSubjectDTO firstName.
     */
    private String firstName;
    
    /**
     * UserForSubjectDTO lastName.
     */
    private String lastName;

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
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }
    
    
}
