package com.softserve.edu.schedule.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dao.SubjectDAO;
import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dao.UserGroupDAO;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserForSubjectDTO;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserGroup;
import com.softserve.edu.schedule.entity.UserRole;
import com.softserve.edu.schedule.entity.UserStatus;
import com.softserve.edu.schedule.service.UserService;
import com.softserve.edu.schedule.service.implementation.dtoconverter.UserDTOConverter;
import com.softserve.edu.schedule.service.implementation.dtoconverter.UserForSubjectDTOConverter;

/**
 * An interface to provide service operations with User entity.
 *
 * @version 1.0 04 January 2017
 *
 * @author Serhiy Dudynsky
 *
 * @since 1.8
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    /**
     * UserDAO example to provide database operations.
     */
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserDTOConverter userDTOConverter;

    @Autowired
    private UserForSubjectDTOConverter userForSubjectDTOConverter;
    
    @Autowired
    private UserGroupDAO userGroupDAO;

    @Autowired
    private SubjectDAO subjectDAO;

    /**
     * Save new user entity into the database.
     *
     * @param user
     *            a new user to storage in database.
     */
    @Override
    @Transactional
    public void create(final UserDTO userDTO) {
        userDAO.create(userDTOConverter.getEntity(userDTO));
    }

    /**
     * Delete existed user entity from the database.
     *
     * @param id
     *            a user id to delete from database.
     */
    @Override
    @Transactional
    public void delete(final UserDTO userDTO) {
        userDAO.delete(userDTOConverter.getEntity(userDTO));
    }

    /**
     * Change field status at user entity in the database.
     *
     * @param id
     *            a user id in database.
     *
     * @param status
     *            a status from enum class UserStatus.
     */
    @Override
    @Transactional
    public void changeStatus(final Long id, final UserStatus status) {
        User user = userDAO.getById(id);
        user.setStatus(status);
        userDAO.update(user);
    }

    /**
     * Change field role at user entity in the database.
     *
     * @param id
     *            a user id in database.
     *
     * @param role
     *            a role from enum class UserRole.
     */
    @Override
    @Transactional
    public void changeRole(final Long id, final UserRole userRole) {
        User user = userDAO.getById(id);
        user.setRole(userRole);
        userDAO.update(user);
    }

    /**
     * Change field position at user entity in the database.
     *
     * @param id
     *            a user id in database.
     *
     * @param position
     *            a position field in User entity.
     */
    @Override
    @Transactional
    public void changePosition(final Long id, final String position) {
        User user = userDAO.getById(id);
        user.setPosition(position);
        userDAO.update(user);
    }

    /**
     * Change field at user entity in the database.
     *
     * @param userDTO
     *            a new user to storage in database.
     */
    @Override
    @Transactional
    public void update(final UserDTO userDTO) {
        User user = userDTOConverter.getEntity(userDTO);
        user.setStatus(userDAO.getById(user.getId()).getStatus());
        user.setRole(userDAO.getById(user.getId()).getRole());
        userDAO.update(user);
    }

    /**
     * Get all users by group what was selected.
     *
     * @param id
     *            a group id in database.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> searchByGroup(final String group) {
        // return userDAO.searchByGroup(group)
        return userDAO.searchByGroup(group).stream()
                .map(e -> userDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Get all users by role what was selected.
     *
     * @param userRole
     *            a value role from enum class UserRole.
     *
     * @return List of the userDTO objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getByRole(final UserRole role) {
        return userDAO.searchByRole(role).stream()
                .map(e -> userDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Get all usersDTO.
     *
     * @return List of the userDTO objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAll() {
        return userDAO.getAll().stream().map(e -> userDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Get all users.
     *
     * @return List of the userDTO objects for SubjectDTO.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserForSubjectDTO> getAllForSubject() {
        return userDAO.getAll().stream()
                .map(u -> userForSubjectDTOConverter.getDTO(u))
                .collect(Collectors.toList());
    }

    /**
     * Get all users by role what was selected.
     *
     * @param userRole
     *            a value role from enum class UserRole.
     *
     * @return List of the userDTO objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getByStatus(final UserStatus status) {
        return userDAO.searchByStatus(status).stream()
                .map(e -> userDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Get all users by position what was selected.
     *
     * @param position
     *            a value of position field in database.
     *
     * @return List of the userDTO objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> search(final String field, final String pattern) {
        return userDAO.search(field, pattern).stream()
                .map(e -> userDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Sort all users by last name.
     *
     * @return List of the userDTO objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> sort(final String field, final Order order) {
        return userDAO.sort(field, order).stream()
                .map(e -> userDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Return a User object if found.
     *
     * @param id
     *            of User transfer object
     * @return UserDTO transfer object
     */
    @Override
    @Transactional(readOnly = true)
    public UserDTO getById(final Long id) {
        return userDTOConverter.getDTO(userDAO.getById(id));
    }

    /**
     * Return a List of searched Users fetching Group.
     *
     * @return List of searched UserDTO transfer objects
     */
    @Override

    public List<UserDTO> getAllWithDetails() {
        return userDAO.getAllWithDetails().stream()
                .map(e -> userDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }
    
    /**
     * Delete existed transfer object from the database by id.
     *
     * @param id
     *            a user id to delete from database.
     */
    @Override
    @Transactional
    public void deleteById(final Long id) {
        User user = userDAO.getById(id);
        boolean isUserCurator = false;

        for (UserGroup group : user.getGroups()) {
            if (isUserCurator(id, group.getId())) {
                isUserCurator = true;
            } else {
                isUserCurator = false;
            }
        }

        if (!isUserCurator) {
            user.getGroups().forEach(
                    e -> deleteUserFromUserGroup(id, e.getId()));
            user.getSubjects().forEach(
                    e -> deleteUserFromSubject(id, e.getId()));
            userDAO.deleteById(id);
        }
    }
    
    @Transactional
    public void deleteUserFromSubject(Long userID, Long subjectID){
        subjectDAO.getById(subjectID).getUsers()
                .removeIf(e -> e.getId().equals(userID));
    }
    
    @Transactional
    public void deleteUserFromUserGroup(Long userID, Long userGroupID) {
        userGroupDAO.getById(userGroupID).getUsers().removeIf(e -> e.getId().equals(userID));
    }
    
    @Transactional
    public boolean isUserCurator(Long userID, Long userGroupID) {
        if (userGroupDAO.getById(userGroupID).getCurator().getId().equals(userID)) {
            return true;
        } else {
            return false;
        }
    }
}
