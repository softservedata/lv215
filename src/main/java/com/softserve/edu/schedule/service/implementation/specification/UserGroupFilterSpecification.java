package com.softserve.edu.schedule.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.softserve.edu.schedule.dto.filter.UserGroupFilter;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserGroup;
import com.softserve.edu.schedule.entity.UserGroup_;
import com.softserve.edu.schedule.service.UserGroupService;
import com.softserve.edu.schedule.service.UserService;
import com.softserve.edu.schedule.service.implementation.dtoconverter.UserDTOConverter;

public class UserGroupFilterSpecification implements Specification<UserGroup> {

	/**
	 * UserGroupFilter example which provides parameters to build predicate.
	 */
	private UserGroupFilter userGroupFilter;

	/**
	 * UserDAO example to provide database operations.
	 */
	private UserService userService;

	/**
	 * UserGroupDAO example to provide database operations.
	 */
	private UserGroupService userGroupService;

	/**
	 * List of userGroups specifications based on which the predicate is built.
	 */
	private List<Specification<UserGroup>> list = new ArrayList<>();

	/**
	 * UserDTO into entity converter
	 */
	@Autowired
	private UserDTOConverter userConverter;

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
	public UserGroupFilterSpecification(final UserGroupFilter filter, final UserService userService,
			final UserGroupService userGroupService) {
		this.userGroupFilter = filter;
		this.userService = userService;
		this.userGroupService = userGroupService;
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
		if (userGroupFilter.getCuratorId() != null && userGroupFilter.getCuratorId() > 0) {
			User user = userConverter.getEntity(userService.getById(userGroupFilter.getCuratorId()));
			list.add((root, criteriQuery, criteriaBuilder) -> criteriaBuilder.isMember(user,
					root.get(UserGroup_.users)));
		}
	}

	/**
	 * Add usergroup userGroup specification to specification list if filter
	 * contains userGroup level parameter.
	 */
	// TODO
	private void findByLevelId() {
		if (userGroupFilter.getLevelId() != null && userGroupFilter.getLevelId() > 0) {
			list.add((root, criteriaQuery, criteriaBuilder) -> root.get(UserGroup_.level)
					.in(userGroupFilter.getLevelId()));
		}
	}

	@Override
	public Predicate toPredicate(Root<UserGroup> root, CriteriaQuery<?> criteriaQuery,
			CriteriaBuilder criteriaBuilder) {
		root.join(UserGroup_.users, JoinType.LEFT);
		// setSortingParameters(root, criteriaQuery, criteriaBuilder);
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

}
