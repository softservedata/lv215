/* ValidationMessages 1.0 01/20/2017 */
package com.softserve.edu.schedule.service.implementation.validators;

/**
 * An interface to storage validation messages.
 *
 * @version 1.0 20 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public interface ValidationMessages {

    /**
     * No Error Code.
     */
    String NO_ERROR_CODE = "";

    /**
     * Empty field message.
     */
    String EMPTY_FIELD = "Field can not be empty.";

    /**
     * Invalid characters in field message.
     */
    String INVALID_CHARACTERS = "Invalid characters in field.";

    /**
     * Duplicate room in location message.
     */
    String DUPLICATE_ROOM = "Room with this name already exist in specified location.";

}
