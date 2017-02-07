/*
 * UserGroupDAOImpl.java
 * 1.0
 * 09 Jan 2017
 * Copyright (c) Zhydenko Andrii
 */
package com.softserve.edu.schedule.dao.implementation;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dao.UserGroupDAO;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.UserGroupFilter;
import com.softserve.edu.schedule.entity.UserGroup;
import com.softserve.edu.schedule.entity.UserGroup_;
import com.softserve.edu.schedule.service.implementation.specification.UserGroupFilterSpecification;

/**
 * A simple class to handle the database operation (CRUD).
 *
 * @version 1.0 09 Jan 2017
 * @author Zhydenko Andrii
 *
 */
@Repository("userGroupDAO")
public class UserGroupDAOImpl extends CrudDAOImpl<UserGroup> implements UserGroupDAO {

	@Autowired
	UserDAO userDAO;

	/**
	 * Constructor of UserGroupDAOImpl
	 */
	public UserGroupDAOImpl() {
		super(UserGroup.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserve.edu.schedule.dao.implementation.CrudDAOImpl#getAll()
	 */

	@Override

	public List<UserGroup> getAll() {
		CriteriaBuilder builder = getEm().getCriteriaBuilder();
		CriteriaQuery<UserGroup> cq = builder.createQuery(UserGroup.class);
		Root<UserGroup> root = cq.from(UserGroup.class);
		root.fetch(UserGroup_.curator, JoinType.LEFT);
		root.fetch(UserGroup_.users, JoinType.LEFT);
		cq.distinct(true);
		return getEm().createQuery(cq).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.dao.implementation.CrudDAOImpl#getById(java.
	 * lang.Long)
	 */
	@Override
	public UserGroup getById(Long id) {
		CriteriaBuilder builder = getEm().getCriteriaBuilder();
		CriteriaQuery<UserGroup> cq = builder.createQuery(UserGroup.class);
		Root<UserGroup> root = cq.from(UserGroup.class);
		root.fetch(UserGroup_.users, JoinType.LEFT);
		cq.where(root.get(UserGroup_.id).in(id));
		return getEm().createQuery(cq).getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.dao.UserGroupDAO#getGroupsByLevel(java.lang.
	 * Long)
	 */
	public List<UserGroup> getGroupsByLevel(final Long levelId) {
		CriteriaBuilder builder = getEm().getCriteriaBuilder();
		CriteriaQuery<UserGroup> cq = builder.createQuery(UserGroup.class);
		Root<UserGroup> root = cq.from(UserGroup.class);
		root.fetch(UserGroup_.users, JoinType.LEFT);
		cq.where(root.get(UserGroup_.level).in(levelId));
		return getEm().createQuery(cq).getResultList();
	}

	@Override
	public List<UserGroup> getUserGroupPageWithFilter(UserGroupFilter userGroupFilter, Paginator userGroupPaginator) {
		CriteriaBuilder builder = getEm().getCriteriaBuilder();
		CriteriaQuery<UserGroup> criteriaQuery = builder.createQuery(UserGroup.class);
		Root<UserGroup> root = criteriaQuery.from(UserGroup.class);

		Predicate predicate = new UserGroupFilterSpecification(userGroupFilter).toPredicate(root, criteriaQuery,
				builder);
		if (predicate != null) {
			criteriaQuery.where(predicate);
		}
		criteriaQuery.distinct(true);
		userGroupPaginator.setPagesCount(getEm().createQuery(criteriaQuery).getResultList().size());
		return getEm().createQuery(criteriaQuery).setFirstResult(userGroupPaginator.getOffset())
				.setMaxResults(userGroupPaginator.getPageSize()).getResultList();
	}
}