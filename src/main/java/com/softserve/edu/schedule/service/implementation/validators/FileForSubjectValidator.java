/*
 * SubjectValidatorImpl.java
 * 1.0
 * 2 Feb 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.service.implementation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.mongodb.gridfs.GridFSDBFile;
import com.softserve.edu.schedule.dto.FileForSubjectDTO;
import com.softserve.edu.schedule.service.SubjectService;

/**
 * A class to provide validation logic.
 *
 * @version 1.0 27 January 2017
 *
 * @author Ped'ko Volodymyr
 *
 * @since 1.8
 */
public class FileForSubjectValidator
        implements ConstraintValidator<Validate, FileForSubjectDTO> {

	/**
	 * Messages source for internationalization purposes.
	 */
	@Autowired
	private ResourceBundleMessageSource messageSource;

	/**
	 * SubjectService example to provide search DTO operations.
	 */
	@Autowired
	private SubjectService subjectService;

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
		return (validSize && validExpansion && noDuplicate);
	}

	/**
	 * This method print error messages if some verification fails.
	 * 
	 * @param noDuplicate
	 * 
	 * @param validSize
	 * 
	 * @param validExpansion
	 * 
	 * @param context
	 */
	private void printErrorMessages(boolean noDuplicate, boolean validSize,
	        boolean validExpansion, ConstraintValidatorContext context) {
		if (!validSize) {
			errorMessage(ValidationFields.FILE,
			        ValidationMessages.FILE_TO_LARGE, context);
		}
		if (!validExpansion) {
			errorMessage(ValidationFields.FILE,
			        ValidationMessages.FILE_WRONG_CONTENT_TYPE, context);
		}
		if (!noDuplicate) {
			System.out.println(ValidationFields.FILE);
			System.out.println(ValidationMessages.FILE_DUPLICATE);
			errorMessage(ValidationFields.FILE,
			        ValidationMessages.FILE_DUPLICATE, context);
		}

	}

	/**
	 * Method to convert messages for localization.
	 * 
	 * @param field
	 * 
	 * @param message
	 * 
	 * @param context
	 */
	private void errorMessage(final String field, final String message,
	        final ConstraintValidatorContext context) {
		System.out.println("IN ERROR MESSAGE");
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(messageSource.getMessage(
		        message, new String[0], LocaleContextHolder.getLocale()))
		        .addPropertyNode(field).addConstraintViolation();
		System.out.println("OUT OF ERROR MESSAGE");
	}

	/**
	 * Method for checking content type.
	 * 
	 * @param fileForSubjectDTO
	 * 
	 * @return true if validation is succes
	 */
	private boolean isValidExpansion(FileForSubjectDTO fileForSubjectDTO) {
		return isValidIMG(fileForSubjectDTO) || isValidMSW(fileForSubjectDTO)
		        || isValidMSE(fileForSubjectDTO)
		        || isValidMSP(fileForSubjectDTO)
		        || isValidPDF(fileForSubjectDTO);
	}

	/**
	 * Method for checking file size.
	 * 
	 * @param fileForSubjectDTO
	 * 
	 * @return true if validation is succes
	 */
	private boolean isValidSize(FileForSubjectDTO fileForSubjectDTO) {
		return fileForSubjectDTO.getFile()
		        .getSize() <= ValidationCriteria.MAX_FILE_SIZE;
	}

	/**
	 * Method for checking duplicates.
	 * 
	 * @param fileForSubjectDTO
	 * 
	 * @return true if validation is succes
	 */
	private boolean isNoDuplicate(FileForSubjectDTO fileForSubjectDTO) {
		System.out.println("IN DUPLICATE CHECK");
		GridFSDBFile file = subjectService.retriveSubjectFileById(
		        Long.toString(fileForSubjectDTO.getId()),
		        fileForSubjectDTO.getFile().getOriginalFilename());
		return (file == null);
	}

	/**
	 * Method for checking content IMG.
	 * 
	 * @param fileForSubjectDTO
	 * 
	 * @return true if validation is succes
	 */
	private boolean isValidIMG(FileForSubjectDTO fileForSubjectDTO) {
		return fileForSubjectDTO.getFile().getContentType()
		        .equals(ValidationCriteria.IMAGE_JPG)
		        || fileForSubjectDTO.getFile().getContentType()
		                .equals(ValidationCriteria.IMAGE_GIF);
	}

	/**
	 * Method for checking content Microsoft Word.
	 * 
	 * @param fileForSubjectDTO
	 * 
	 * @return true if validation is succes
	 */
	private boolean isValidMSW(FileForSubjectDTO fileForSubjectDTO) {
		return fileForSubjectDTO.getFile().getContentType()
		        .equals(ValidationCriteria.MSWORD)
		        || fileForSubjectDTO.getFile().getContentType()
		                .equals(ValidationCriteria.MSWORD_2007);
	}

	/**
	 * Method for checking content Microsoft Exel.
	 * 
	 * @param fileForSubjectDTO
	 * 
	 * @return true if validation is succes
	 */
	private boolean isValidMSE(FileForSubjectDTO fileForSubjectDTO) {
		return fileForSubjectDTO.getFile().getContentType()
		        .equals(ValidationCriteria.MSEXEL)
		        || fileForSubjectDTO.getFile().getContentType()
		                .equals(ValidationCriteria.MSEXEL_2007);
	}

	/**
	 * Method for checking content Microsoft Powerpoint.
	 * 
	 * @param fileForSubjectDTO
	 * 
	 * @return true if validation is succes
	 */
	private boolean isValidMSP(FileForSubjectDTO fileForSubjectDTO) {
		return fileForSubjectDTO.getFile().getContentType()
		        .equals(ValidationCriteria.MSPOWER)
		        || fileForSubjectDTO.getFile().getContentType()
		                .equals(ValidationCriteria.MSPOWER_2007);
	}

	/**
	 * Method for checking content PDF.
	 * 
	 * @param fileForSubjectDTO
	 * 
	 * @return true if validation is succes
	 */
	private boolean isValidPDF(FileForSubjectDTO fileForSubjectDTO) {
		return fileForSubjectDTO.getFile().getContentType()
		        .equals(ValidationCriteria.PDF);
	}

}
