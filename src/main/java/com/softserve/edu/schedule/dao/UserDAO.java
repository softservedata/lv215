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
     * Find all meetings in the DB by given date and roomId.
     *
     * @author Petro Zelyonka
     *
     * @param userMail
     *            user mail to find user in database
     *
     * @return User object with given mail or null if not finded.
     */
    User findByMail(String userMail);

    List<User> getUsersPageWithFilter(UserFilter userFilter,
            Paginator userPaginator);
    
    Long getCountOfUsersWithFilter(final UserFilter userFilter);
    
    /**
     * Method returns lists of users which have role - moderator
     * 
     * @return lists of users which have role - moderator
     */
    List<User> getModerators();
    
    /**
     * Method returns lists of users which have status - active
     * 
     * @return lists of users which have status - active
     */
    List<User> getAllActiveUsers();

}
