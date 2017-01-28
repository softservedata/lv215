package com.softserve.edu.schedule.dao.implementation;

import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.entity.User;

@Repository("userDAO")
public class UserDAOImpl extends CrudDAOImpl<User> implements UserDAO {

    /**
     * Constructor for UserDAOImpl class.
     */
    public UserDAOImpl() {
        super(User.class);
    }

    /**
     * Delete existed user entity from the database by id.
     *
     * @param id
     *            a user id to delete from database.
     */
    @Override
    public void deleteById(final Long id) {
        delete(getById(id));
    }
}