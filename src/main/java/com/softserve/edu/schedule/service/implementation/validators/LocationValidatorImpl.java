/*
 * LocationValidatorImpl
 * 1.0
 * 28 Jan 2017
 * Copyright (c) Oleksandr Butyter
 */
package com.softserve.edu.schedule.service.implementation.validators;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.service.LocationService;

/**
 * The validator of checking location input data.
 * 
 * @version 1.0 28 Jan 2017
 * @author Oleksandr Butyter
 *
 */
public class LocationValidatorImpl implements ConstraintValidator<LocationValidator, Object> {

	/**
	 * An input parameter name
	 */
	private String name;

	/**
	 * An input parameter address
	 * 
	 */
	private String address;

	/**
	 * Messages source for internationalization purposes.
	 */
	@Autowired
	private ResourceBundleMessageSource messageSource;

	/**
	 * LocationService example to provide getting method for locations.
	 */
	@Autowired
	private LocationService locationService;

	/**
	 * Initializes the validator in preparation for calls. The constraint
	 * annotation for a given constraint declaration is passed. This method is
	 * guaranteed to be called before any use of this instance for validation.
	 *
	 * @param constraintAnnotation
	 *            annotation instance for a given constraint declaration
	 */
	@Override
	public void initialize(LocationValidator constraintAnnotation) {
		name = constraintAnnotation.name();
		address = constraintAnnotation.address();

	}

	/**
	 * Implements the validation logic.
	 *
	 * @param value
	 *            object to validate
	 * @param context
	 *            context in which the constraint is evaluated
	 *
	 * @return false if value does not pass the constraint
	 */
	@Override
	public boolean isValid(final Object value, ConstraintValidatorContext context) {
		LocationDTO locationDTO = (LocationDTO) value;
		boolean validName = isValidName(locationDTO);
		boolean validAddress = isValidAddress(locationDTO);
		boolean noDuplicate = isOriginName(locationDTO);
		printErrorMessages(validName, validAddress, noDuplicate, context);
		return (validName && validAddress && noDuplicate);

	}

	/**
	 * Method checks if the name of location is origin.
	 * 
	 * @param locationDTO
	 *            input data to check
	 * @return true if name of location is origin, else - otherwise.
	 */
	private boolean isOriginName(LocationDTO locationDTO) {
		List<LocationDTO> duplicates = locationService.getLocationsByName(locationDTO.getName().trim());
		return duplicates.isEmpty() || duplicates.stream().anyMatch(s -> s.getId().equals(locationDTO.getId()));
	}

	/**
	 * Method checks if name of location matches pattern.
	 * 
	 * @param locationDTO
	 *            input data to check
	 * @return true if name of location matches pattern, else - otherwise.
	 */
	private boolean isValidName(LocationDTO locationDTO) {
		return locationDTO.getName().matches(ValidationCriteria.PATTERN_FOR_LOCATION_NAME);
	}

	/**
	 * Method checks if address of location matches pattern.
	 * 
	 * @param locationDTO
	 *            input data to check
	 * @return true if address of location matches pattern, else - otherwise.
	 */
	private boolean isValidAddress(LocationDTO locationDTO) {
		return locationDTO.getAddress().matches(ValidationCriteria.PATTERN_FOR_LOCATION_ADDRESS);
	}

	/**
	 * Method localizes message.
	 * 
	 * @param field
	 * @param message
	 * @param context
	 */
	private void errorMessage(String field, String message, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(
				messageSource.getMessage(message, new String[0], LocaleContextHolder.getLocale()))
				.addPropertyNode(field).addConstraintViolation();
	}

	/**
	 * Method sets error messages if some verification fails.
	 * 
	 * @param validName
	 * @param validAddress
	 * @param noDuplicate
	 * @param context
	 */
	private void printErrorMessages(boolean validName, boolean validAddress, boolean noDuplicate,
			ConstraintValidatorContext context) {
		if (!validName) {
			errorMessage(name, ValidationMessages.INVALID_LOCATION_NAME, context);
		}
		if (!validAddress) {
			errorMessage(address, ValidationMessages.INVALID_LOCATION_ADDRESS, context);
		}
		if (!noDuplicate) {
			errorMessage(name, ValidationMessages.DUPLICATE_LOCATION, context);
		}
	}

}
