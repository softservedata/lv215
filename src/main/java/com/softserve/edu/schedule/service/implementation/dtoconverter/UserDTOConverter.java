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
     * UserGroupDTOConverter example to provide to DTO user groups
     * conversion.
     */
//    @Autowired
//    private UserGroupDTOConverter userGroupDTOConverter;

    /**
     * SubjectDTOConverter example to provide to DTO subjects
     * conversion.
     */
//    @Autowired
//    private SubjectDTOConverter subjectDTOConverter;

    /**
     * Convert given UserDTO object to User object
     *
     * @param userDTO
     *            a userDTO object to convert.
     *
     * @return a User object or null if given @param userDTO is null.
     */
    public User getEntity(final UserDTO userDTO) {
        if (userDTO != null) {
            User user = new User();

            if (userDTO.getId() != null) {
                user.setId(userDTO.getId());
            }

            if (userDTO.getFirstName() != null) {
                user.setFirstName(userDTO.getFirstName());
            }

            if (userDTO.getLastName() != null) {
                user.setLastName(userDTO.getLastName());
            }

            if (userDTO.getMail() != null) {
                user.setMail(userDTO.getMail());
            }

            if (userDTO.getPassword() != null) {
                user.setPassword(userDTO.getPassword());
            }

            if (userDTO.getPhone() != null) {
                user.setPhone(userDTO.getPhone());
            }

            if (userDTO.getPosition() != null) {
                user.setPosition(userDTO.getPosition());
            }

            if (userDTO.getStatus() != null) {
                user.setStatus(userDTO.getStatus());
            }

            if (userDTO.getRole() != null) {
                user.setRole(userDTO.getRole());
            }

            if (userDTO.getSubjects() != null) {
                userDTO.getSubjects().forEach(e -> user.getSubjects()
                        .add(subjectDAO.getById(e.getId())));
            }

            if (userDTO.getGroups() != null) {
                userDTO.getGroups().forEach(e -> user.getGroups()
                        .add(userGroupDAO.getById(e.getId())));
            }
            return user;
        }
        return null;
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
        if (user != null) {
            UserDTO userDTO = new UserDTO();

            if (user.getId() != null) {
                userDTO.setId(user.getId());
            }

            if (user.getFirstName() != null) {
                userDTO.setFirstName(user.getFirstName());
            }

            if (user.getLastName() != null) {
                userDTO.setLastName(user.getLastName());
            }

            if (user.getMail() != null) {
                userDTO.setMail(user.getMail());
            }

            if (user.getPassword() != null) {
                userDTO.setPassword(user.getPassword());
            }

            if (user.getPhone() != null) {
                userDTO.setPhone(user.getPhone());
            }

            if (user.getPosition() != null) {
                userDTO.setPosition(user.getPosition());
            }

            if (user.getStatus() != null) {
                userDTO.setStatus(user.getStatus());
            }

            if (user.getRole() != null) {
                userDTO.setRole(user.getRole());
            }

//            if (user.getSubjects() != null) {
//                user.getSubjects().forEach(e -> userDTO.getSubjects()
//                        .add(subjectDTOConverter.getDTO(e)));
//            }
//
//            if (user.getGroups() != null) {
//                user.getGroups().forEach(e -> userDTO.getGroups()
//                        .add(userGroupDTOConverter.getDTO(e)));
//            }
            return userDTO;
        }
        return null;
    }
}
