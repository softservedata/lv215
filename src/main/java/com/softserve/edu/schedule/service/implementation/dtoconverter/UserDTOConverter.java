package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dao.SubjectDAO;
import com.softserve.edu.schedule.dao.UserGroupDAO;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.entity.User;

/**
 * A class to provide conversion operations between UserDTO and User entity.
 *
 * @version 1.0 18 January 2017
 *
 * @author Serhiy Dudynsky
 *
 * @since 1.8
 */
@Service("userDTOConverter")
public class UserDTOConverter {

    /**
     * SubjectDAO example to provide database operations.
     */
    @Autowired
    private SubjectDAO subjectDAO;

    /**
     * UserGroupDAO example to provide database operations.
     */
    @Autowired
    private UserGroupDAO userGroupDAO;

    /**
     * UserGroupDTOConverter example to provide to DTO user groups conversion.
     */
    @Autowired
    private UserGroupFouUserDTOConverter userGroupFUDTOConverter;

    /**
     * SubjectDTOConverter example to provide to DTO subjects conversion.
     */
    @Autowired
    private SubjectForUserDTOConverter subjectFUDTOConverter;

    /**
     * Convert given UserDTO object to User object
     *
     * @param userDTO
     *            a userDTO object to convert.
     *
     * @return a User object or null if given @param userDTO is null.
     */
    public User getEntity(final UserDTO userDTO) {

        User user = new User();

        user.setId(userDTO.getId());

        user.setFirstName(userDTO.getFirstName());

        user.setLastName(userDTO.getLastName());

        user.setMail(userDTO.getMail());

        if (userDTO.getPassword() != null) {
            user.setPassword(userDTO.getPassword());
        }

        user.setPhone(userDTO.getPhone());

        user.setPosition(userDTO.getPosition());

        if (userDTO.getStatus() != null) {
            user.setStatus(userDTO.getStatus());
        }

        if (userDTO.getRole() != null) {
            user.setRole(userDTO.getRole());
        }

        userDTO.getSubjects().forEach(
                e -> user.getSubjects().add(subjectDAO.getById(e.getId())));

        userDTO.getGroups().forEach(
                e -> user.getGroups().add(userGroupDAO.getById(e.getId())));

        return user;

    }

    /**
     * Convert given User object to UserDTO object.
     *
     * @param user
     *            a User object to convert.
     *
     * @return a UserDTO object or null if given @param user is null.
     */
    public UserDTO getDTO(final User user) {

        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());

        userDTO.setFirstName(user.getFirstName());

        userDTO.setLastName(user.getLastName());

        userDTO.setMail(user.getMail());

        if (user.getPassword() != null) {
            userDTO.setPassword(user.getPassword());
        }

        userDTO.setPhone(user.getPhone());

        userDTO.setPosition(user.getPosition());

        if (user.getStatus() != null) {
            userDTO.setStatus(user.getStatus());
        }

        if (user.getRole() != null) {
            userDTO.setRole(user.getRole());
        }

        user.getSubjects().forEach(e -> userDTO.getSubjects()
                .add(subjectFUDTOConverter.getDTO(e)));

        user.getGroups().forEach(e -> userDTO.getGroups()
                .add(userGroupFUDTOConverter.getDTO(e)));

        return userDTO;

    }
}
