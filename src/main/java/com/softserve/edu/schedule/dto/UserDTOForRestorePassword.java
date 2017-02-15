package com.softserve.edu.schedule.dto;

/**
 * A data transfer object for user entity.
 *
 * @version 1.0 15 February 2017
 *
 * @author Serhiy Dudynsky
 *
 * @since 1.8
 */
public class UserDTOForRestorePassword {
    
    private String mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}