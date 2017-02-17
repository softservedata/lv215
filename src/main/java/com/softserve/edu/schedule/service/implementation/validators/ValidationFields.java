/* ValidationFields 1.0 01/20/2017 */
package com.softserve.edu.schedule.service.implementation.validators;

/**
 * An interface to storage validation fields names.
 *
 * @version 1.0 20 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public interface ValidationFields {

    /**
     * Id field.
     */
    String ID = "id";

    /**
     * Name field.
     */
    String NAME = "name";

    /**
     * Capacity field.
     */
    String CAPACITY = "capacity";

    /**
     * Location field.
     */
    String LOCATION = "location";

    /**
     * FirstName field.
     */
    String FIRSTNAME = "firstName";

    /**
     * LastName field.
     */
    String LASTNAME = "lastName";

    /**
     * Mail field.
     */
    String MAIL = "mail";

    /**
     * Phone field.
     */
    String PHONE = "phone";

    /**
     * Position field.
     */
    String POSITION = "position";

    /**
     * Password field.
     */
    String PASSWORD = "password";

    /**
     * Description field.
     */
    String DESCRIPTION = "description";

    /**
     * Level field.
     */
    String LEVEL = "level";

    /**
     * Users field.
     */
    String USERS = "users";

    /**
     * Address field.
     */
    String ADDRESS = "address";

    /**
     * Date for the meeting.
     */
    String DATE = "date";

    /**
     * Start time of the meeting.
     */
    String STARTTIME = "startTime";

    /**
     * End time of the meeting.
     */
    String ENDTIME = "endTime";

    /**
     * Groups of the meeting.
     */
    String GROUPS = "groups";

    /**
     * Subject of the meeting.
     */
    String SUBJECT = "subject";

    /**
     * Status of the meeting. Status of meeting.
     */
    String STATUS = "status";

    /**
     * First new password.
     */
    String FIRST_NEW_PASSWORD = "firstNewPassword";

    /**
     * Second new password.
     */
    String SECOND_NEW_PASSWORD = "secondNewPassword";

    /**
     * Old password.
     */
    String OLD_PASSWORD = "oldPassword";
    
    String CONFIRM_PASSWORD = "confirmPassword";

}
