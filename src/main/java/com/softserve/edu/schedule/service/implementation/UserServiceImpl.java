package com.softserve.edu.schedule.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserRole;
import com.softserve.edu.schedule.entity.UserStatus;
import com.softserve.edu.schedule.service.UserService;

/**
 * An interface to provide service operations with User entity.
 *
 * @version 1.0 04 January 2017
 *
 * @author Serhiy Dudynsky
 *
 * @since 1.8
 */
@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

    /**
     * UserDAO example to provide database operations.
     */
    @Autowired
    private UserDAO userDAO;

    /**
     * Save new user entity into the database.
     *
     * @param user
     *            a new user to storage in database.
     */
    @Override
    public void create(final User user) {
        userDAO.create(user);
    }

    /**
     * Delete existed user entity from the database.
     *
     * @param id
     *            a user id to delete from database.
     */
    @Override
    public void delete(final Long id) {
        User user = userDAO.getById(id);
        if (user != null) {
            userDAO.delete(user);
        }
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
    public void changePosition(final Long id, final String position) {
        User user = userDAO.getById(id);
        user.setPosition(position);
        userDAO.update(user);
    }

    /**
     * Change field at user entity in the database.
     *
     * @param user
     *            a new user to storage in database.
     */
    @Override
    public void update(final User user) {
        List<User> allUsers = userDAO.getAll();
        for (User userExample : allUsers) {
            if(userExample.getId()==(user.getId())){
                user.setStatus(userDAO.getById(user.getId()).getStatus());
                user.setRole(userDAO.getById(user.getId()).getRole());
                userDAO.update(user);
            }
        }
    }

    /**
     * Get all users by group what was selected.
     *
     * @param id
     *            a group id in database.
     */
     @Override
     @Transactional(readOnly = true)
     public List<User> searchByGroup(final String group) {
     return userDAO.searchByGroup(group);
     }

    /**
     * Get all users by role what was selected.
     *
     * @param userRole
     *            a value role from enum class UserRole.
     *
     * @return List of the user objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getByRole(final UserRole role) {
        return userDAO.searchByRole(role);
    }

    /**
     * Get all users.
     *
     * @return List of the user objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userDAO.getAll();
    }

    /**
     * Get all users by role what was selected.
     *
     * @param userRole
     *            a value role from enum class UserRole.
     *
     * @return List of the user objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getByStatus(final UserStatus status) {
        return userDAO.searchByStatus(status);
    }

    /**
     * Get all users by position what was selected.
     *
     * @param position
     *            a value of position field in database.
     *
     * @return List of the user objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> search(final String field, final String pattern) {
        return userDAO.search(field, pattern);
    }

    /**
     * Sort all users by last name.
     *
     * @return List of the user objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> sort(final String field, final Order order) {
        return userDAO.sort(field, order);
    }

    /**
     * Return a User object if found.
     *
     * @param id
     *            of User transfer object
     * @return User transfer object
     */
    @Override
    public User getById(final Long id){
        return userDAO.getById(id);
    }

    /**
     * Delete existed transfer object from the database by id.
     *
     * @param id
     *            a user id to delete from database.
     */
    @Override
    public void deleteById(final Long id){
        userDAO.deleteById(id);
    }

    /**
     * Return a List of searched Users fetching Group.
     *
     * @return List of searched Users transfer objects
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getAllWithDetails() {
        return userDAO.getAllWithDetails();
    }
}
