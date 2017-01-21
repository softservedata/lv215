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
     * Empty field message bundle mapping.
     */
    String EMPTY_FIELD = "vm.emptyField";

    /**
     * Invalid characters in field message bundle mapping.
     */
    String INVALID_CHARACTERS = "vm.ivalidCharacters";

    /**
     * Duplicate room in location message bundle mapping.
     */
    String DUPLICATE_ROOM = "vm.duplicateRoom";

}
