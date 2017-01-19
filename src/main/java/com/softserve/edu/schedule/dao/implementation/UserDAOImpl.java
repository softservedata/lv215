package com.softserve.edu.schedule.dao.implementation;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dao.UserGroupDAO;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserGroup;
import com.softserve.edu.schedule.entity.UserGroup_;
import com.softserve.edu.schedule.entity.UserRole;
import com.softserve.edu.schedule.entity.UserStatus;
import com.softserve.edu.schedule.entity.User_;

@Repository("userDAO")
public class UserDAOImpl extends CrudDAOImpl<User> implements UserDAO {

    @Autowired
    private UserGroupDAO userGroupDAO;

    /**
     * Constructor for UserDAOImpl class.
     */
    public UserDAOImpl() {
        super(User.class);
    }

    /**
     * Return a List of User object by UserRole.
     *
     * @return List of searched Transfer objects
     */
    @Override
    public List<User> searchByRole(final UserRole role) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<User> cq = builder.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);
        cq.where(builder.equal(root.get(User_.role), role.ordinal()));
        List<User> users = getEm().createQuery(cq).getResultList();
        return users;

    }

    /**
     * Return a List of User object by UserStatus.
     *
     * @return List of searched Transfer objects
     */
    @Override
    public List<User> searchByStatus(final UserStatus status) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<User> cq = builder.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);
        cq.where(builder.equal(root.get(User_.status), status.ordinal()));
        List<User> users = getEm().createQuery(cq).getResultList();
        return users;
    }

    /**
     * Return a List of searched Transfer objects.
     *
     * @return List of searched Transfer objects
     */
    @Override
    public List<User> searchByGroup(final String group) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<User> cq = builder.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        Join<User, UserGroup> joinUserGroup = root.join(User_.groups);
        Predicate predicate = builder.like(joinUserGroup.get(UserGroup_.name),
                SEARCH_MASK + group + SEARCH_MASK);
        cq.where(predicate);
        return getEm().createQuery(cq).getResultList();
    }

    /**
     * Delete existed user entity from the database by id.
     *
     * @param id
     *            a user id to delete from database.
     */
    @Override
    public void deleteById(final Long id) throws IllegalArgumentException {
        User user = getById(id);
        user.getGroups().forEach(
                e -> userGroupDAO.deleteUserFromUserGroup(id, e.getId()));
        update(user);
        delete(user);
    }

    /**
     * Return a List of searched Users fetching Groups.
     *
     * @return List of searched Users transfer objects
     */
    @Override
    public List<User> getAllWithDetails() {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<User> cq = builder.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        root.fetch(User_.groups, JoinType.LEFT);
        cq.distinct(true);
        return getEm().createQuery(cq).getResultList();
    }
}