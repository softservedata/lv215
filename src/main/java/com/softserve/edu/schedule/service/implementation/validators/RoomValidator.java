/* RoomValidator 1.0 01/29/2017 */
package com.softserve.edu.schedule.service.implementation.validators;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.service.RoomService;

/**
 * A validator class to check whatever room DTO data is correct.
 *
 * @version 1.0 29 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public class RoomValidator implements ConstraintValidator<Validate, RoomDTO> {

    /**
     * Messages source for internationalization purposes.
     */
    @Autowired
    private ResourceBundleMessageSource messageSource;

    /**
     * RoomService example to provide search by location and room name.
     */
    @Autowired
    private RoomService roomService;

    /**
     * Initializes the validator in preparation for calls. The constraint
     * annotation for a given constraint declaration is passed. This method is
     * guaranteed to be called before any use of this instance for validation.
     *
     * @param constraintAnnotation
     *            annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(final Validate constraintAnnotation) {
    }

    /**
     * Implements the validation logic.
     *
     * @param roomDTO
     *            object to validate
     * @param context
     *            context in which the constraint is evaluated
     *
     * @return false if value does not pass the constraint
     */
    @Override
    public boolean isValid(final RoomDTO roomDTO,
            final ConstraintValidatorContext context) {
        boolean isNameValid = isNameValid(roomDTO);
        boolean isCapacityValid = isCapacityValid(roomDTO);
        boolean hasNoDuplicates = hasNoDuplicates(roomDTO);
        printErrorMessages(isNameValid, isCapacityValid, hasNoDuplicates,
                context);
        return isNameValid && isCapacityValid && hasNoDuplicates;
    }

    /**
     * Checks the rooms in the database with the same name and location as given
     * roomDTO parameter.
     *
     * @param roomDTO
     *            a RoomDTO object to check duplicates.
     *
     * @return true if there are no duplicates
     */
    private boolean hasNoDuplicates(final RoomDTO roomDTO) {
        List<RoomDTO> duplicates = roomService.getByNameAndLocation(
                roomDTO.getName().trim(), roomDTO.getLocation());
        return duplicates.isEmpty() || duplicates.stream()
                .anyMatch(s -> s.getId().equals(roomDTO.getId()));
    }

    /**
     * Checks the given roomDTO name contains only allowed characters.
     *
     * @param roomDTO
     *            a RoomDTO object to check name.
     *
     * @return true if name is valid
     */
    private boolean isNameValid(final RoomDTO roomDTO) {
        return roomDTO.getName().trim()
                .matches(ValidationCriteria.PATTERN_FOR_ROOM_NAME);
    }

    /**
     * Checks the given roomDTO capacity is greater than 0 and less than maximum
     * allowed capacity.
     *
     * @param roomDTO
     *            a RoomDTO object to check capacity.
     *
     * @return true if capacity is valid
     */
    private boolean isCapacityValid(final RoomDTO roomDTO) {
        return roomDTO.getCapacity() != null && roomDTO.getCapacity() > 0
                && roomDTO.getCapacity() < ValidationCriteria.MAX_ROOM_CAPACITY;
    }

    /**
     * Method localizes message.
     *
     * @param field
     *            message field
     * @param message
     *            message text
     * @param context
     *            validator context
     */
    private void errorMessage(final String field, final String message,
            final ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(messageSource.getMessage(
                message, new String[0], LocaleContextHolder.getLocale()))
                .addPropertyNode(field).addConstraintViolation();
    }

    /**
     * Method sets error messages if some verification fails.
     *
     * @param isNameValid
     *            true if room name is valid
     * @param isCapacityValid
     *            true if room capacity is valid
     * @param hasNoDuplicates
     *            true if there are no duplicates of room in this locstion
     * @param context
     *            validator context
     */
    private void printErrorMessages(final boolean isNameValid,
            final boolean isCapacityValid, final boolean hasNoDuplicates,
            final ConstraintValidatorContext context) {
        if (!isNameValid) {
            errorMessage(ValidationFields.NAME,
                    ValidationMessages.INVALID_CHARACTERS_OR_EMPTY_FIELD,
                    context);
        }
        if (!isCapacityValid) {
            errorMessage(ValidationFields.CAPACITY,
                    ValidationMessages.INVALID_ROOM_CAPACITY, context);
        }
        if (!hasNoDuplicates) {
            errorMessage(ValidationFields.NAME,
                    ValidationMessages.DUPLICATE_ROOM, context);
        }
    }

}
