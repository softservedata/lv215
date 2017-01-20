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

}
