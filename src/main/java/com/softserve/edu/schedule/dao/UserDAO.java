package com.softserve.edu.schedule.dao;

import java.util.List;

import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserRole;
import com.softserve.edu.schedule.entity.UserStatus;

public interface UserDAO extends CrudDAO<User>{
    /**
     * Return a List of searched Transfer objects.
     *
     * @return List of searched Transfer objects
     */
    List<User> searchByRole(final UserRole role);

    /**
     * Return a List of searched Transfer objects.
     *
     * @return List of searched Transfer objects
     */
    List<User> searchByStatus(final UserStatus status);

    /**
     * Return a List of searched Transfer objects.
     *
     * @return List of searched Transfer objects
     */
    List<User> searchByGroup(final String group);

    /**
     * Delete existed user entity from the database by id.
     *
     * @param id
     *            a user id to delete from database.
     */
    void deleteById(final Long id);
}