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
    public List<User> searchByRole(final UserRole role);

    /**
     * Return a List of searched Transfer objects.
     *
     * @return List of searched Transfer objects
     */
    public List<User> searchByStatus(final UserStatus status);

    /**
     * Return a List of searched Transfer objects.
     *
     * @return List of searched Transfer objects
     */
    public List<User> searchByGroup(final String group);

    /**
     * Delete existed user entity from the database by id.
     *
     * @param id
     *            a user id to delete from database.
     */
    public void deleteById(final Long id);

    /**
     * Return a List of searched Users fetching Group.
     *
     * @return List of searched Users transfer objects
     */
    public List<User> getAllWithDetails();

}