package com.softserve.edu.schedule.dto;

import com.softserve.edu.schedule.service.implementation.validators.Validate;

/**
 * A data transfer object for user entity.
 *
 * @version 1.0 31 January 2017
 *
 * @author Serhiy Dudynsky
 *
 * @since 1.8
 */
@Validate
public class UserDTOForChangePassword {

    private Long id;
    private String password;
    private String oldPassword;
    private String firstNewPassword;
    private String secondNewPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getFirstNewPassword() {
        return firstNewPassword;
    }

    public void setFirstNewPassword(String firstNewPassword) {
        this.firstNewPassword = firstNewPassword;
    }

    public String getSecondNewPassword() {
        return secondNewPassword;
    }

    public void setSecondNewPassword(String secondNewPassword) {
        this.secondNewPassword = secondNewPassword;
    }

}
