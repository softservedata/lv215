package com.softserve.edu.schedule.service;

import java.util.List;

import com.softserve.edu.schedule.dao.Order;
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
    void create(final User user);

    /**
     * Delete existed user entity from the database.
     *
     * @param id
     *            a user id to delete from database.
     */
    void delete(final User user);

    /**
     * Change field status at user entity in the database.
     *
     * @param id
     *            a user id in database.
     *
     * @param status
     *            a status from enum class UserStatus.
     */
    void changeStatus(final Long id, final UserStatus status);

    /**
     * Change field role at user entity in the database.
     *
     * @param id
     *            a user id in database.
     *
     * @param role
     *            a role from enum class UserRole.
     */
    void changeRole(final Long id, final UserRole role);

    /**
     * Change field position at user entity in the database.
     *
     * @param id
     *            a user id in database.
     *
     * @param position
     *            a position field in User entity.
     */
    void changePosition(final Long id, final String position);

    /**
     * Change field at user entity in the database.
     *
     * @param id
     *            a user id in database.
     */
    void update(final User user);

    /**
     * Get all users by group what was selected.
     *
     * @param id
     *            a group id in database.
     */
    List<User> searchByGroup(final String group);

    /**
     * Get all users by role what was selected.
     *
     * @param userRole
     *            a value role from enum class UserRole.
     *
     * @return List of the user objects.
     */
    List<User> getByRole(final UserRole userRole);

    /**
     * Get all users.
     *
     * @return List of the user objects.
     */
    public List<User> getAll();

    /**
     * Get all users by role what was selected.
     *
     * @param userStatus
     *            a value status from enum class UserRole.
     *
     * @return List of the user objects.
     */
    List<User> getByStatus(final UserStatus userStatus);

    /**
     * Get all users by position what was selected.
     *
     * @param field
     *            a name of column in database.
     *
     * @param pattern
     *            a value of field in database.
     *
     * @return List of the user objects.
     */
    List<User> search(final String field, final String pattern);

    /**
     * Sort all users by first name.
     *
     * @param field
     *            a name of column in database.
     *
     * @param pattern
     *            a value of field in database.
     *
     * @return List of the user objects.
     */
    List<User> sort(final String field, final Order order);

    /**
     * Return a User object if found.
     *
     * @param id
     *            of User transfer object
     * @return User transfer object
     */
    User getById(final Long id);

    /**
     * Delete existed transfer object from the database by id.
     *
     * @param id
     *            a user id to delete from database.
     */
    void deleteById(final Long id);

    /**
     * Return a List of searched Users fetching Groups.
     *
     * @return List of searched Users transfer objects
     */
    List<User> getAllWithDetails();


}