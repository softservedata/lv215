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
    void deleteById(Long id);

    /**
     * Find user in the DB by given mail.
     *
     * @param userMail
     *            user mail to find user in database
     *
     * @return User object with given mail or null if not finded.
     */
    User findByMail(String userMail);

    /**
     * Find all user entities in the database with applied filter
     * 
     * @param userFilter
     *            a filter to apply.
     * 
     * @return List of the user objects.
     */
    List<User> getUsersPageWithFilter(UserFilter userFilter,
            Paginator userPaginator);
    
    /**
     * Count user entities in the database with specified filter
     *
     * @param userFilter
     *            a filter to apply.
     *
     * @return Count of the user entities in the database with specified
     *         predicate.
     */
    Long getCountOfUsersWithFilter(final UserFilter userFilter);
    
    /**
     * Method returns lists of users which have role - moderator
     * 
     * @return lists of users which have role - moderator
     */
    List<User> getModerators();

}
