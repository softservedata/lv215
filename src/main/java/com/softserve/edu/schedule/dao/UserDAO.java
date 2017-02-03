package com.softserve.edu.schedule.dao;

import java.util.List;

import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.UserFilter;
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
    
    public List<User> getUsersPageWithFilter(final UserFilter userFilter,
            final Paginator userPaginator);

}