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

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "",
                "Field can not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capacity", "",
                "Field can not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location", "",
                "Field can not be empty.");

        if (!roomDTO.getName().matches("^[\\p{L}0-9]*$")) {
            errors.rejectValue("name", "", "Invalid characters in field.");
        }

        if (!roomDTO.getCapacity().matches("[0-9]+")) {
            errors.rejectValue("capacity", "", "Invalid characters in field.");
        } else if (Integer.parseInt(roomDTO.getCapacity()) <= 0) {
            errors.rejectValue("capacity", "",
                    "Capacity can not be zero or negative.");
        }

        if (hasDuplicates(roomDTO)) {
            errors.rejectValue("name", "",
                    "Room with this name already exist in specified location.");
        }

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
