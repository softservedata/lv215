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
     * Allowed characters for name expression.
     */
    String CHARACTERS_FOR_NAME = "^[а-яА-ЯёЁіІєЄїЇa-zA-Z0-9\\-№ ]+$";

    /**
     * Allowed only digits expression.
     */
    String DIGITS_ONLY = "[0-9]+";
    
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
     * ^                 # start-of-string
     * (?=.*[0-9])       # a digit must occur at least once
     * (?=.*[a-z])       # a lower case letter must occur at least once
     * (?=.*[A-Z])       # an upper case letter must occur at least once
     * (?=.*[@#$%^&+=])  # a special character must occur at least once
     * (?=\S+$)          # no whitespace allowed in the entire string
     * .{8,}             # anything, at least eight places though
     * $                 # end-of-string
     */
    String CHARACTERS_FOR_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{8,}$";

}
