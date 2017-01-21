/* RoomValidator 1.0 01/20/2017 */
package com.softserve.edu.schedule.service.implementation.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.service.RoomService;

/**
 * A validator class to check whatever room DTO data is correct.
 *
 * @version 1.0 20 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public class RoomValidator implements Validator {

    /**
     * RoomService example to provide search DTO operations.
     */
    private final RoomService roomService;

    /**
     * Constructor of RoomValidator.
     * 
     * @param roomService
     *            RoomService example
     */
    public RoomValidator(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Check if this validator can validate instances of the supplied class.
     * 
     * @param clazz
     *            a Class to check
     * 
     * @return true if this validator can validate instances of the supplied
     *         class.
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return RoomDTO.class.isAssignableFrom(clazz);
    }

    /**
     * Validate the supplied object, which must be of a Class for which the
     * support() method typically has (or would) return true. The supplied
     * errors instance can be used to report any resulting validation errors.
     * 
     * @param target
     *            the object that is to be validated.
     * @param errors
     *            contextual state about the validation process
     */
    @Override
    public void validate(Object target, Errors errors) {
        RoomDTO roomDTO = (RoomDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, ValidationFields.NAME,
                ValidationMessages.NO_ERROR_CODE,
                ValidationMessages.EMPTY_FIELD);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                ValidationFields.CAPACITY, ValidationMessages.NO_ERROR_CODE,
                ValidationMessages.EMPTY_FIELD);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                ValidationFields.LOCATION, ValidationMessages.NO_ERROR_CODE,
                ValidationMessages.EMPTY_FIELD);

        if (!isRoomNameValid(roomDTO)) {
            errors.rejectValue(ValidationFields.NAME,
                    ValidationMessages.NO_ERROR_CODE,
                    ValidationMessages.INVALID_CHARACTERS);
        }

        if (!isRoomCapacityValid(roomDTO)) {
            errors.rejectValue(ValidationFields.CAPACITY,
                    ValidationMessages.NO_ERROR_CODE,
                    ValidationMessages.INVALID_CHARACTERS);
        }

        if (hasDuplicates(roomDTO)) {
            errors.rejectValue(ValidationFields.NAME,
                    ValidationMessages.NO_ERROR_CODE,
                    ValidationMessages.DUPLICATE_ROOM);
        }

    }

    /**
     * Checks the given roomDTO capacity contains only digits.
     * 
     * @param roomDTO
     *            a RoomDTO object to check capacity.
     * 
     * @return true if capacity is valid
     */
    private boolean isRoomCapacityValid(RoomDTO roomDTO) {
        return roomDTO.getCapacity().matches(ValidationCriteria.DIGITS_ONLY);
    }

    /**
     * Checks the given roomDTO name contains only allowed characters.
     * 
     * @param roomDTO
     *            a RoomDTO object to check name.
     * 
     * @return true if name is valid
     */
    private boolean isRoomNameValid(RoomDTO roomDTO) {
        return roomDTO.getName()
                .matches(ValidationCriteria.CHARACTERS_FOR_NAME);
    }

    /**
     * Checks the rooms in the database with the same name and location as given
     * roomDTO parameter.
     * 
     * @param roomDTO
     *            a RoomDTO object to check duplicates.
     * 
     * @return true if there are duplicates
     */
    private boolean hasDuplicates(RoomDTO roomDTO) {
        RoomDTO dublicate = roomService.getByNameAndLocation(roomDTO.getName(),
                roomDTO.getLocation());
        if (dublicate == null) {
            return false;
        }
        if (dublicate.getId() == roomDTO.getId()) {
            return false;
        }
        return true;
    }

}
