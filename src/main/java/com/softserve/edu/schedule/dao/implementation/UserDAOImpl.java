package com.softserve.edu.schedule.dao.implementation;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.User_;

@Repository
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

    @Override
    public User findByMail(String userMail) {
        try {
            CriteriaBuilder builder = getEm().getCriteriaBuilder();
            CriteriaQuery<User> cq = builder.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.where(builder.like(root.get(User_.mail), userMail));
            return getEm().createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}