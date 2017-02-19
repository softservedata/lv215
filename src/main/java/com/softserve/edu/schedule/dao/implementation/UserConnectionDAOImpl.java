/* UserConnectionDAOImpl 1.0 02/19/2017 */
package com.softserve.edu.schedule.dao.implementation;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.UserConnectionDAO;
import com.softserve.edu.schedule.entity.UserConnection;
import com.softserve.edu.schedule.entity.UserConnection_;

/**
 * A class to provide DAO operations with UserConnection entity.
 *
 * @version 1.0 19 February 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Repository
public class UserConnectionDAOImpl extends CrudDAOImpl<UserConnection>
        implements UserConnectionDAO {

    /**
     * Default constructor to provide entity class for DAO.
     *
     */
    public UserConnectionDAOImpl() {
        super(UserConnection.class);
    }

    /**
     * Deleting user connections from database by given user id.
     *
     * @param userId
     *            - user id to delete connections
     */
    @Override
    public void deleteConnectionsByUserId(final String userId) {
        findConnectionsByUserId(userId)
                .forEach(e -> getEm().remove(getEm().merge(e)));
    }

    /**
     * Find user connections in database by given user id.
     *
     * @param userId
     *            - user id to find connections
     */
    @Override
    public List<UserConnection> findConnectionsByUserId(final String userId) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<UserConnection> cq = builder
                .createQuery(UserConnection.class);
        Root<UserConnection> root = cq.from(UserConnection.class);
        Predicate predicate = builder.like(root.get(UserConnection_.userId),
                userId);
        cq.where(predicate);
        return getEm().createQuery(cq).getResultList();
    }

}
