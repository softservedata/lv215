package com.softserve.edu.schedule.entity;

/**
 * An enumeration of usergroup level.
 *
 * @version 1.0 01 February 2017
 *
 * @author Andrii Zhydenko
 *
 */
public enum UserGroupLevel {

    /**
     * User group level values.
     */
    STUDENTS("stCode"), TEACHERS("tchCode"), UNKNOWN("unkCode");

    /**
     * User group level message code for localization purposes.
     */
    private String code;

    /**
     * User group level constructor.
     *
     * @param code
     *            message code
     */
    UserGroupLevel(final String code) {
        this.code = code;
    }

    /**
     * Message code getter.
     *
     * @return message code
     */
    public String getCode() {
        return code;
    }
}
