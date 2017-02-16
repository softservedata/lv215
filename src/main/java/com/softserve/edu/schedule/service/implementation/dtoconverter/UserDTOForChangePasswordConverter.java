package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dto.UserDTOForChangePassword;
import com.softserve.edu.schedule.entity.User;

/**
 * A class to provide conversion operations between UserDTO and User entity.
 *
 * @version 1.0 31 January 2017
 *
 * @author Serhiy Dudynsky
 *
 * @since 1.8
 */
@Service("userDTOForPasswordConverter")
public class UserDTOForChangePasswordConverter {

    @Autowired
    UserDAO userDAO;

    /**
     * Convert given UserDTO object to User object
     *
     * @param userDTO
     *            a userDTO object to convert.
     *
     * @return a User object or null if given @param userDTO is null.
     */
    public User getEntity(final UserDTOForChangePassword userDTO) {

        User user = userDAO.getById(userDTO.getId());

        return user;

    }

    /**
     * Convert given User object to UserDTO object
     *
     * @param user
     *            a user object to convert.
     *
     * @return a UserDTO object.
     */
    public UserDTOForChangePassword getDTOForPassword(final User user) {

        UserDTOForChangePassword userDTO = new UserDTOForChangePassword();

        userDTO.setId(user.getId());
        
        userDTO.setPassword(user.getPassword());

        userDTO.setOldPassword("");

        userDTO.setFirstNewPassword("");

        userDTO.setSecondNewPassword("");

        return userDTO;

    }
}
