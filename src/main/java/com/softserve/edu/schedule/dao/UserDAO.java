package com.softserve.edu.schedule.dao;

import com.softserve.edu.schedule.entity.User;

public interface UserDAO extends CrudDAO<User> {

    /**
     * Delete existed user entity from the database by id.
     *
     * @param id
     *            a user id to delete from database.
     */
    public void deleteById(final Long id);

    public User findByMail(String userMail);

}