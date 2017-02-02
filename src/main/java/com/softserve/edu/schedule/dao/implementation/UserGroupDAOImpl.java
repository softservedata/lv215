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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dao.UserGroupDAO;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserGroup;
import com.softserve.edu.schedule.entity.UserGroup_;
import com.softserve.edu.schedule.entity.User_;

/**
 * A simple class to handle the database operation (CRUD).
 *
 * @version 1.0 09 Jan 2017
 * @author Zhydenko Andrii
 *
 */
/**
 * @author Andrew
 *
 */
/**
 * @author Andrew
 *
 */
@Repository("userGroupDAO")
public class UserGroupDAOImpl extends CrudDAOImpl<UserGroup> implements UserGroupDAO {

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
	 * 
	 * @see com.softserve.edu.schedule.dao.UserGroupDAO#sortByFields(java.lang.
	 * String, com.softserve.edu.schedule.dao.Order)
	 */
	@Override
	public List<UserGroup> sortByFields(final String field, final Order order) {
		CriteriaBuilder builder = getEm().getCriteriaBuilder();
		CriteriaQuery<UserGroup> cq = builder.createQuery(UserGroup.class);
		Root<UserGroup> root = cq.from(UserGroup.class);
		root.fetch(UserGroup_.curator, JoinType.LEFT);
		root.fetch(UserGroup_.users, JoinType.LEFT);
		cq.distinct(true);
		if (order == Order.ASC) {
			cq.orderBy(builder.asc(root.get(field)));
		} else {
			cq.orderBy(builder.desc(root.get(field)));
		}
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
	 * com.softserve.edu.schedule.dao.UserGroupDAO#searchGroupsByCurators(java.
	 * lang.String)
	 */
	@Override
	public List<UserGroup> searchGroupsByCurators(String pattern) {
		CriteriaBuilder builder = getEm().getCriteriaBuilder();
		CriteriaQuery<UserGroup> cq = builder.createQuery(UserGroup.class);
		Root<UserGroup> root = cq.from(UserGroup.class);
		Join<UserGroup, User> joinUser = root.join(UserGroup_.users);
		Predicate predicate = builder.like(joinUser.get(User_.lastName), SEARCH_MASK + pattern + SEARCH_MASK);
		cq.where(predicate);
		cq.distinct(true);
		return getEm().createQuery(cq).getResultList();
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
}