/* UserConnectionDAO 1.0 02/19/2017 */
package com.softserve.edu.schedule.dao;

import java.util.List;

import com.softserve.edu.schedule.entity.UserConnection;

/**
 * An interface to provide DAO operations with UserConnection entity.
 *
 * @version 1.0 19 February 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public interface UserConnectionDAO extends CrudDAO<UserConnection> {

    /**
     * Deleting user connections from database by given user id.
     *
     * @param userId
     *            - user id to delete connections
     */
    void deleteConnectionsByUserId(String userId);

    /**
     * Find user connections in database by given user id.
     *
     * @param userId
     *            - user id to find connections
     */
    List<UserConnection> findConnectionsByUserId(String userId);
}
