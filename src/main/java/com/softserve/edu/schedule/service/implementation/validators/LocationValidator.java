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
public class LocationValidator implements ConstraintValidator<Validate, LocationDTO> {

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
	public void initialize(final Validate constraintAnnotation) {
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
	public boolean isValid(final LocationDTO locationDTO, final ConstraintValidatorContext context) {
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
	private boolean isOriginName(final LocationDTO locationDTO) {
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
	private boolean isValidName(final LocationDTO locationDTO) {
		return locationDTO.getName().matches(ValidationCriteria.PATTERN_FOR_LOCATION_NAME);
	}

	/**
	 * Method checks if address of location matches pattern.
	 * 
	 * @param locationDTO
	 *            input data to check
	 * @return true if address of location matches pattern, else - otherwise.
	 */
	private boolean isValidAddress(final LocationDTO locationDTO) {
		return locationDTO.getAddress().matches(ValidationCriteria.PATTERN_FOR_LOCATION_ADDRESS);
	}

	/**
	 * Method localizes message.
	 * 
	 * @param field
	 * @param message
	 * @param context
	 */
	private void errorMessage(final String field, final String message, final ConstraintValidatorContext context) {
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
	private void printErrorMessages(final boolean validName, final boolean validAddress, final boolean noDuplicate,
			final ConstraintValidatorContext context) {
		if (!validName) {
			errorMessage(ValidationFields.NAME, ValidationMessages.INVALID_LOCATION_NAME, context);
		}
		if (!validAddress) {
			errorMessage(ValidationFields.ADDRESS, ValidationMessages.INVALID_LOCATION_ADDRESS, context);
		}
		if (!noDuplicate) {
			errorMessage(ValidationFields.NAME, ValidationMessages.DUPLICATE_LOCATION, context);
		}
	}

}
