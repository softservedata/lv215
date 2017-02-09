package com.softserve.edu.schedule.dao.implementation;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.UserFilter;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserRole;
import com.softserve.edu.schedule.entity.User_;
import com.softserve.edu.schedule.service.implementation.specification.UserFilterSpecification;

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

    /**
     * Find user in the DB by given mail.
     *
     * @param userMail
     *            user mail to find user in database
     *
     * @return User object with given mail or null if not finded.
     */
	@Override
	public User findByMail(final String userMail) {
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

    /**
     * Find all user entities in the database with applied filter
     * 
     * @param userFilter
     *            a filter to apply.
     * 
     * @return List of the user objects.
     */
    @Override
    public List<User> getUsersPageWithFilter(UserFilter userFilter,
            Paginator userPaginator) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        Predicate predicate = new UserFilterSpecification(userFilter)
                .toPredicate(root, criteriaQuery, builder);
        if (predicate != null) {
            criteriaQuery.where(predicate);
        }
        userPaginator.setPagesCount(getCountOfUsersWithFilter(userFilter));
        return getEm().createQuery(criteriaQuery)
                .setFirstResult(userPaginator.getOffset())
                .setMaxResults(userPaginator.getPageSize()).getResultList();
    }
    
    /**
     * Count user entities in the database with specified filter
     *
     * @param userFilter
     *            a filter to apply.
     *
     * @return Count of the user entities in the database with specified
     *         predicate.
     */
    @Override
    public Long getCountOfUsersWithFilter(final UserFilter userFilter) {
        CriteriaBuilder qb = getEm().getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        Root<User> root = cq.from(User.class);
        cq.select(qb.countDistinct(root));
        Predicate predicate = new UserFilterSpecification(userFilter)
                .toPredicate(root, cq, qb);
        if (predicate != null) {
            cq.where(predicate);
        }
        return getEm().createQuery(cq).getSingleResult();
    }
    
    /**
     * Method returns lists of users which have role - moderator
     * 
     * @return lists of users which have role - moderator
     */
    @Override
    public List<User> getModerators() {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<User> cq = builder.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.where(builder.equal(root.get(User_.role), UserRole.ROLE_MODERATOR.ordinal()));
        return getEm().createQuery(cq).getResultList();
    }
}
