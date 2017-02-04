package com.softserve.edu.schedule.entitylisteners;

import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;

import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.service.implementation.mailsenders.RegistrationMailServise;

public class UserRegistEntityListener {

    /**
     * MeetingCompactDTOConverter example to provide to DTO and from DTO
     * conversion.
     */
//    @Autowired
//    private UserDTOConverter userDTOConverter;

    /**
     * MeetingCanceledMailService example to provide send mail to user
     * operations.
     */
    @Autowired
    private RegistrationMailServise registrationMailService;

    /**
     * Send mail messages to new user after registration
     * 
     * @param user
     *            User just registed.
     * 
     */
    @PrePersist
    public void processingBeforUserRegistration(UserDTO userDTO) {
        registrationMailService.sendInfoMessageRegistration(
                userDTO, LocaleContextHolder.getLocale());
    }
}
