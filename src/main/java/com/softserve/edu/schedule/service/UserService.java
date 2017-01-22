package com.softserve.edu.schedule.service;

import java.util.List;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserForSubjectDTO;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserRole;
import com.softserve.edu.schedule.entity.UserStatus;

/**
 * An interface to specify service operations with User entity.
 *
 * @version 1.0 04 January 2017
 *
 * @author Serhiy Dudynsky
 *
 * @since 1.8
 */
public interface UserService {

    /**
     * Save new user entity into the database.
     *
     * @param user
     *            a new user to storage in database.
     */
    public void create(final UserDTO userDTO);

    /**
     * Change field status at user entity in the database.
     *
     * @param id
     *            a user id in database.
     *
     * @param status
     *            a status from enum class UserStatus.
     */
    public void changeStatus(final Long id, final UserStatus status);

    /**
     * Change field role at user entity in the database.
     *
     * @param id
     *            a user id in database.
     *
     * @param role
     *            a role from enum class UserRole.
     */
    public void changeRole(final Long id, final UserRole role);

    /**
     * Change field position at user entity in the database.
     *
     * @param id
     *            a user id in database.
     *
     * @param position
     *            a position field in User entity.
     */
    public void changePosition(final Long id, final String position);

    /**
     * Change field at user entity in the database.
     *
     * @param id
     *            a user id in database.
     */
    public void update(final UserDTO userDTO);

    /**
     * Get all users.
     *
     * @return List of the user objects.
     */
    public List<UserDTO> getAll();

    /**
     * Get all users.
     *
     * @return List of the user objects for SubjectDTO.
     */
    public List<UserForSubjectDTO> getAllForSubject();

    /**
     * Get all users by last name what was selected.
     *
     * @param pattern
     *            a value of field in database.
     *
     * @return List of the user objects.
     */
    public List<UserDTO> searchByLastName(final String pattern);
    
    /**
     * Get all users by position what was selected.
     *
     * @param pattern
     *            a value of field in database.
     *
     * @return List of the user objects.
     */
    public List<UserDTO> searchByPosition(final String pattern);

    /**
     * Sort all users by first name.
     *
     * @param order
     *            a constant ASC or DESC.
     *
     * @return List of the user objects.
     */
    public List<UserDTO> sortByLastName(final Order order);
    
    /**
     * Sort all users by first name.
     *
     * @param order
     *            a constant ASC or DESC.
     *
     * @return List of the user objects.
     */
    public List<UserDTO> sortByPosition(final Order order);

    /**
     * Return a User object if found.
     *
     * @param id
     *            of User transfer object
     * @return User transfer object
     */
    public UserDTO getById(final Long id);

    /**
     * Delete existed transfer object from the database by id.
     *
     * @param id
     *            a user id to delete from database.
     */
    public void deleteById(final Long id);

}