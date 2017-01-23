//package com.softserve.edu.schedule.service.implementation.validators;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.i18n.LocaleContextHolder;
//import org.springframework.context.support.ResourceBundleMessageSource;
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//import org.springframework.validation.Validator;
//
//import com.softserve.edu.schedule.dto.RoomDTO;
//import com.softserve.edu.schedule.dto.UserDTO;
//import com.softserve.edu.schedule.service.RoomService;
//import com.softserve.edu.schedule.service.UserService;
//
//public class UserValidator  implements Validator {
//    
//    /**
//     * UserService example to provide search DTO operations.
//     */
//    @Autowired
//    private UserService userService;
//
//    /**
//     * Messages source for internationalization purposes.
//     */
//    @Autowired
//    private ResourceBundleMessageSource messageSource;
//
//    /**
//     * Check if this validator can validate instances of the supplied class.
//     * 
//     * @param clazz
//     *            a Class to check
//     * 
//     * @return true if this validator can validate instances of the supplied
//     *         class.
//     */
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return UserDTO.class.isAssignableFrom(clazz);
//    }
//
//    /**
//     * Validate the supplied object, which must be of a Class for which the
//     * support() method typically has (or would) return true. The supplied
//     * errors instance can be used to report any resulting validation errors.
//     * 
//     * @param target
//     *            the object that is to be validated.
//     * @param errors
//     *            contextual state about the validation process
//     */
//    @Override
//    public void validate(Object target, Errors errors) {
//        UserDTO userDTO = (UserDTO) target;
//
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, ValidationFields.FIRSTNAME,
//                ValidationMessages.NO_ERROR_CODE,
//                messageSource.getMessage(ValidationMessages.EMPTY_FIELD,
//                        new String[0], LocaleContextHolder.getLocale()));
//        
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, ValidationFields.LASTNAME,
//                ValidationMessages.NO_ERROR_CODE,
//                messageSource.getMessage(ValidationMessages.EMPTY_FIELD,
//                        new String[0], LocaleContextHolder.getLocale()));
//        
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, ValidationFields.POSITION,
//                ValidationMessages.NO_ERROR_CODE,
//                messageSource.getMessage(ValidationMessages.EMPTY_FIELD,
//                        new String[0], LocaleContextHolder.getLocale()));
//        
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, ValidationFields.MAIL,
//                ValidationMessages.NO_ERROR_CODE,
//                messageSource.getMessage(ValidationMessages.EMPTY_FIELD,
//                        new String[0], LocaleContextHolder.getLocale()));
//
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
//                ValidationFields.CAPACITY, ValidationMessages.NO_ERROR_CODE,
//                messageSource.getMessage(ValidationMessages.EMPTY_FIELD,
//                        new String[0], LocaleContextHolder.getLocale()));
//
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
//                ValidationFields.LOCATION, ValidationMessages.NO_ERROR_CODE,
//                messageSource.getMessage(ValidationMessages.EMPTY_FIELD,
//                        new String[0], LocaleContextHolder.getLocale()));
//
//        if (!isRoomNameValid(userDTO)) {
//            errors.rejectValue(ValidationFields.NAME,
//                    ValidationMessages.NO_ERROR_CODE,
//                    messageSource.getMessage(
//                            ValidationMessages.INVALID_CHARACTERS,
//                            new String[0], LocaleContextHolder.getLocale()));
//        }
//
//        if (!isRoomCapacityValid(roomDTO)) {
//            errors.rejectValue(ValidationFields.CAPACITY,
//                    ValidationMessages.NO_ERROR_CODE,
//                    messageSource.getMessage(
//                            ValidationMessages.INVALID_CHARACTERS,
//                            new String[0], LocaleContextHolder.getLocale()));
//        }
//
//        if (hasDuplicates(roomDTO)) {
//            errors.rejectValue(ValidationFields.NAME,
//                    ValidationMessages.NO_ERROR_CODE,
//                    messageSource.getMessage(ValidationMessages.DUPLICATE_ROOM,
//                            new String[0], LocaleContextHolder.getLocale()));
//        }
//
//    }
//
//    /**
//     * Checks the given userDTO firstName contains only allowed characters.
//     * 
//     * @param userDTO
//     *            a UserDTO object to check firstName.
//     * 
//     * @return true if FirstName is valid
//     */
//    private boolean isUserFirstNameValid(UserDTO userDTO) {
//        return userDTO.getFirstName().matches(ValidationCriteria.CHARACTERS_FOR_NAME);
//    }
//    
//    /**
//     * Checks the given userDTO lastName contains only allowed characters.
//     * 
//     * @param userDTO
//     *            a UserDTO object to check lastName.
//     * 
//     * @return true if lastName is valid
//     */
//    private boolean isUserLastNameValid(UserDTO userDTO) {
//        return userDTO.getLastName().matches(ValidationCriteria.CHARACTERS_FOR_NAME);
//    }
//    
//    /**
//     * Checks the given userDTO position contains only allowed characters.
//     * 
//     * @param userDTO
//     *            a UserDTO object to check position.
//     * 
//     * @return true if position is valid
//     */
//    private boolean isPositionValid(UserDTO userDTO) {
//        return userDTO.getPosition().matches(ValidationCriteria.CHARACTERS_FOR_NAME);
//    }
//    
//    /**
//     * Checks the given userDTO phone number contains only allowed characters.
//     * 
//     * @param userDTO
//     *            a UserDTO object to check phone.
//     * 
//     * @return true if phone is valid
//     */
//    private boolean isPhoneValid(UserDTO userDTO) {
//        return userDTO.getPhone().matches(ValidationCriteria.CHARACTERS_FOR_PHONE);
//    }
//    
//    /**
//     * Checks the given userDTO phone number contains only allowed characters.
//     * 
//     * @param userDTO
//     *            a UserDTO object to check phone.
//     * 
//     * @return true if phone is valid
//     */
//    private boolean isMailValid(UserDTO userDTO) {
//        return userDTO.getMail().matches(ValidationCriteria.CHARACTERS_FOR_MAIL);
//    }
//    
//    /**
//     * Checks the given userDTO password contains only allowed characters.
//     * 
//     * @param userDTO
//     *            a UserDTO object to check password.
//     * 
//     * @return true if password is valid
//     */
//    private boolean isPaswordValid(UserDTO userDTO) {
//        return userDTO.getMail().matches(ValidationCriteria.CHARACTERS_FOR_PASSWORD);
//    }
//
//    /**
//     * Checks the user in the database with the same mail as given
//     * userDTO parameter.
//     * 
//     * @param userDTO
//     *            a UserDTO object to check duplicates.
//     * 
//     * @return true if there are duplicates
//     */
//    private boolean hasDuplicates(UserDTO userDTO) {
//        UserDTO dublicate = userService.getByMail(userDTO.getMail());
//        if (dublicate == null) {
//            return false;
//        }
//        return true;
//    }
//
//}
