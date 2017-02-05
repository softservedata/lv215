package com.softserve.edu.schedule.service.implementation.specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dto.filter.UserGroupFilter;
import com.softserve.edu.schedule.entity.UserGroup;
import com.softserve.edu.schedule.entity.UserGroup_;

public class UserGroupFilterSpecification implements Specification<UserGroup> {

	/**
	 * UserGroupFilter example which provides parameters to build predicate.
	 */
	private UserGroupFilter userGroupFilter;

	/**
	 * List of userGroups specifications based on which the predicate is built.
	 */
	private List<Specification<UserGroup>> list = new ArrayList<>();

	/**
	 * Constructor of UserGroupFilterSpecification.
	 * 
	 * @param filter
	 *            UserGroupFilter example which provides parameters to build
	 *            predicate.
	 * 
	 * @param userDAO
	 *            userDAO example to provide database operations.
	 * 
	 * @param userGroupDAO
	 *            userGroupDAO example to provide database operations.
	 */
	public UserGroupFilterSpecification(final UserGroupFilter userGroupFilter) {
		this.userGroupFilter = userGroupFilter;
	}

	/**
	 * Add userGroup name specification to specification list if filter contains
	 * userGroup name parameter.
	 */
	private void findByName() {
		if (userGroupFilter.getName() != null && !userGroupFilter.getName().trim().equals("")) {
			list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(UserGroup_.name),
					"%" + userGroupFilter.getName() + "%"));
		}
	}

	/**
	 * Add UserGroup userGroup specification to specification list if filter
	 * contains userGroup curator parameter.
	 */
	private void findByCuratorId() {
		if (userGroupFilter.getCuratorId() != null && userGroupFilter.getCuratorId() >= 0) {
			list.add((root, criteriaQuery, criteriaBuilder) -> root.get(UserGroup_.curator)
					.in(userGroupFilter.getCuratorId()));
		}
	}

	/**
	 * Add usergroup userGroup specification to specification list if filter
	 * contains userGroup level parameter.
	 */
	private void findByLevelId() {
		if (userGroupFilter.getLevelId() != null && userGroupFilter.getLevelId() >= 0) {
			list.add((root, criteriaQuery, criteriaBuilder) -> root.get(UserGroup_.level)
					.in(userGroupFilter.getLevelId()));
		}
	}

	@Override
	public Predicate toPredicate(Root<UserGroup> root, CriteriaQuery<?> criteriaQuery,
			CriteriaBuilder criteriaBuilder) {
		root.join(UserGroup_.users, JoinType.LEFT);
		setSortingParameters(root, criteriaQuery, criteriaBuilder);
		if (userGroupFilter != null) {
			findByLevelId();
			findByCuratorId();
			findByName();
		}
		if (list.size() == 0) {
			return null;
		}
		Specifications<UserGroup> spec = Specifications.where(list.get(0));
		for (int i = 1; i < list.size(); i++) {
			spec = spec.and(list.get(i));
		}
		return spec.toPredicate(root, criteriaQuery, criteriaBuilder);
	}

	private void setSortingParameters(final Root<UserGroup> root, final CriteriaQuery<?> criteriaQuery,
			final CriteriaBuilder criteriaBuilder) {
		if (userGroupFilter.getSortOrder() == Order.ASC.ordinal() && userGroupFilter.getFieldForSorting() == 1) {
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get(UserGroup_.name.getName())));
		}
		if (userGroupFilter.getSortOrder() == Order.DESC.ordinal() && userGroupFilter.getFieldForSorting() == 1) {
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get(UserGroup_.name.getName())));
		}
		if (userGroupFilter.getSortOrder() == Order.ASC.ordinal() && userGroupFilter.getFieldForSorting() == 3) {
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get(UserGroup_.level.getName())));
		}
		if (userGroupFilter.getSortOrder() == Order.DESC.ordinal() && userGroupFilter.getFieldForSorting() == 3) {
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get(UserGroup_.level.getName())));
		}
		if (userGroupFilter.getSortOrder() == Order.ASC.ordinal() && userGroupFilter.getFieldForSorting() == 4) {
			criteriaQuery.orderBy(criteriaBuilder.asc(criteriaBuilder.size(root.<Collection>get("users"))));
		}
		if (userGroupFilter.getSortOrder() == Order.DESC.ordinal() && userGroupFilter.getFieldForSorting() == 4) {
			criteriaQuery.orderBy(criteriaBuilder.desc(criteriaBuilder.size(root.<Collection>get("users"))));
		}

	}

}
