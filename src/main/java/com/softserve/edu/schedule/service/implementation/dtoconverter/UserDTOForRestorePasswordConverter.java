package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dto.UserDTOForRestorePassword;
import com.softserve.edu.schedule.entity.User;

/**
 * A class to provide conversion operations between UserDTOForRestorePassword and User entity.
 *
 * @version 1.0 15 February 2017
 *
 * @author Serhiy Dudynsky
 *
 * @since 1.8
 */
@Component("userDTOForRestorePasswordConverter")
public class UserDTOForRestorePasswordConverter {

    @Autowired
    UserDAO userDAO;
    
    public User getEntity(final UserDTOForRestorePassword userDTO) {

        User user = userDAO.findByMail(userDTO.getMail());

        return user;
    }
}
