package com.softserve.edu.schedule.service.implementation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.softserve.edu.schedule.dto.FileForSubjectDTO;

public class FileForSubjectValidator
        implements ConstraintValidator<Validate, FileForSubjectDTO> {

	/**
	 * Messages source for internationalization purposes.
	 */
	@Autowired
	private ResourceBundleMessageSource messageSource;

	/**
	 * Initializes the validator in preparation for calls. The constraint
	 * annotation for a given constraint declaration is passed. This method is
	 * guaranteed to be called before any use of this instance for validation.
	 *
	 * @param constraintAnnotation
	 *            annotation instance for a given constraint declaration
	 */
	@Override
	public void initialize(Validate constraintAnnotation) {
	}

	/**
	 * Implements the validation logic.
	 *
	 * @param fileForSubjectDTO
	 *            object to validate
	 * @param context
	 *            context in which the constraint is evaluated
	 *
	 * @return false if value does not pass the constraint
	 */
	@Override
	public boolean isValid(FileForSubjectDTO fileForSubjectDTO,
	        ConstraintValidatorContext context) {
		boolean noDuplicate = isNoDuplicate(fileForSubjectDTO);
		boolean validSize = isValidSize(fileForSubjectDTO);
		boolean validExpansion = isValidExpansion(fileForSubjectDTO);
		printErrorMessages(noDuplicate, validSize, validExpansion, context);
		return validSize;
	}

	/**
	 * This method print error messages if some verification fails.
	 * 
	 * @param noDuplicate
	 * @param validSize
	 * @param validExpansion
	 * @param context
	 */
	private void printErrorMessages(boolean noDuplicate, boolean validSize,
	        boolean validExpansion, ConstraintValidatorContext context) {
		if (!validSize) {
			errorMessage(ValidationFields.FILE,
			        ValidationMessages.FILE_TO_LARGE, context);
		}

	}

	/**
	 * Method to convert messages for localization.
	 * 
	 * @param field
	 * @param message
	 * @param context
	 */
	private void errorMessage(final String field, final String message,
	        final ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(messageSource.getMessage(
		        message, new String[0], LocaleContextHolder.getLocale()))
		        .addPropertyNode(field).addConstraintViolation();
	}

	private boolean isValidExpansion(FileForSubjectDTO fileForSubjectDTO) {

		return false;
	}

	private boolean isValidSize(FileForSubjectDTO fileForSubjectDTO) {
		return fileForSubjectDTO.getFile()
		        .getSize() <= ValidationCriteria.MAX_FILE_SIZE;
	}

	private boolean isNoDuplicate(FileForSubjectDTO fileForSubjectDTO) {
		// TODO Auto-generated method stub
		return false;
	}

}
