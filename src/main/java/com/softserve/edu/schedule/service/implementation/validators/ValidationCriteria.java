/* ValidationCriteria 1.0 01/20/2017 */
package com.softserve.edu.schedule.service.implementation.validators;

/**
 * An interface to storage validation criteria regular expressions.
 *
 * @version 1.0 20 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public interface ValidationCriteria {

    /**
     * Allowed characters for room name.
     */
    String PATTERN_FOR_ROOM_NAME = "^[а-яА-ЯёЁіІєЄїЇa-zA-Z0-9№'\\.\\,\\s\\-]{2,254}$";

    /**
     * Allowed maximal room capacity.
     */
    Integer MAX_ROOM_CAPACITY = 50000;

    /**
     * Allowed characters for name expression.
     */
    String CHARACTERS_FOR_USERNAME = "[а-яА-ЯёЁіІєЄїЇa-zA-Z]{2,25}";

    /**
     * Allowed only digits expression in specific order.
     */
    String CHARACTERS_FOR_PHONE = "\\d{3}-\\d{7}";

    /**
     * Allowed characters for email expression in specific order.
     */
    String CHARACTERS_FOR_MAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Allowed characters for password expression in specific order.
     * 
     * ^ # start-of-string (?=.*[0-9]) # a digit must occur at least once
     * (?=.*[a-z]) # a lower case letter must occur at least once (?=.*[A-Z]) #
     * an upper case letter must occur at least once (?=.*[@#$%^&+=]) # a
     * special character must occur at least once (?=\S+$) # no whitespace
     * allowed in the entire string .{8,} # anything, at least eight places
     * though $ # end-of-string
     */
    String CHARACTERS_FOR_PASSWORD = "\\A(?=\\S*[a-z])(?=\\S*[A-Z])\\S{8,}\\z";// (?=\\S*[0-9])(?=\\S*[!@#$%^&*()+=])

    /**
     * Allowed characters for position expression.
     */
    String CHARACTERS_FOR_POSITION = "[а-яА-ЯёЁіІєЄїЇa-zA-Z\\s]{1,250}";

    /**
     * Allowed characters for name expression.
     */
    String PATTERN_FOR_SUBJECT_NAME = "^[а-яА-ЯёЁіІєЄїЇa-zA-Z0-9№'@#$%^&+=\\.\\,\\s\\-]{1,254}$";

    /**
     * Allowed characters for description expression.
     */
    String PATTERN_FOR_SUBJECT_DESCRIPTION = "^[а-яА-ЯёЁіІєЄїЇa-zA-Z0-9№'@#$%^&+=\\.\\,\\s\\-]{1,1000}$";

    /**
     * Array size comparator.
     */
    Integer ZERO = 0;

    /**
     * Allowed characters for name expression.
     */
    String PATTERN_FOR_LOCATION_NAME = "^[а-яА-ЯёЁіІєЄїЇa-zA-Z0-9№'\\.\\,\\s\\-]{2,254}$";

    /**
     * Allowed characters for name expression.
     */
    String PATTERN_FOR_LOCATION_ADDRESS = "^[а-яА-ЯёЁіІєЄїЇa-zA-Z0-9№'\\.\\,\\s\\-]{10,254}$";

    /**
     * Allowed characters for Meeting description expression.
     */
    String PATTERN_FOR_MEETING_DESCRIPTION = "^[а-яА-ЯёЁіІєЄїЇa-zA-Z0-9№'@#$%^&!+=\\.\\,\\s\\-]{0,300}$";

    /**
     * Allowed characters for Meeting level expression.
     */
    String PATTERN_FOR_MEETING_LEVEL = "^[1-5]$";

}