/*
 * LocationFilterSpecification
 * 1.0
 * 1 Feb 2017
 * Copyright (c) Oleksandr Butyter
 */
package com.softserve.edu.schedule.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.softserve.edu.schedule.dto.filter.LocationFilter;
import com.softserve.edu.schedule.entity.Location;
import com.softserve.edu.schedule.entity.Location_;

/**
 * A class to provide predicate based on given LocationFilter example. This
 * predicate is used to search locations in database by given filter parameters.
 *
 * @version 1.0 1 February 2017
 *
 * @author Oleksandr Butyter
 */
public class LocationFilterSpecification implements Specification<Location> {
	
    /**
     * Field for sorting (name).
     */
    private static final int SORT_BY_NAME = 1;

    /**
     * Field for sorting (address).
     */
    private static final int SORT_BY_ADDRESS = 2;

    /**
     * Sort order ascending.
     */
    private static final int SORT_ASC = 1;

    /**
     * Sort order descending.
     */
    private static final int SORT_DESC = 2;

	/**
	 * LocationFilter example which provides parameters to build predicate.
	 */
	private LocationFilter filter;

	/**
	 * List of location specifications based on which the predicate is built.
	 */
	private List<Specification<Location>> list = new ArrayList<>();

	/**
	 * Constructor of LocationFilterSpecification.
	 * 
	 * @param filter
	 *            LocationFilter example which provides parameters to build a
	 *            predicate.
	 */
	public LocationFilterSpecification(final LocationFilter filter) {
		this.filter = filter;
	}

	/**
	 * Method adds location name specification to specification list (if filter
	 * contains location name parameter).
	 */
	private void findByName() {
		if (filter.getName() != null && !filter.getName().trim().equals("")) {
			list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(Location_.name),
					"%" + filter.getName() + "%"));
		}
	}

	/**
	 * Method adds location address specification to specification list (if
	 * filter contains location address parameter).
	 * 
	 */
	private void findByAddress() {
		if (filter.getAddress() != null && !filter.getAddress().trim().equals("")) {
			list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(Location_.address),
					"%" + filter.getAddress() + "%"));
		}
	}

	/**
	 * Method adds sorting order to predicate if filter contains sortByField and
	 * sortOrder parameters.
	 * 
	 * @param root
	 *            a root of predicate
	 * 
	 * @param criteriaQuery
	 *            a query to add sorting order.
	 * 
	 * @param criteriaBuilder
	 *            a criteria builder of predicate.
	 */
	private void setSortingParameters(final Root<Location> root, final CriteriaQuery<?> criteriaQuery,
			final CriteriaBuilder criteriaBuilder) {
		if (filter.getSortOrder() == SORT_ASC) {
			if (filter.getSortByField() == SORT_BY_NAME) {
				criteriaQuery.orderBy(criteriaBuilder.asc(root.get(Location_.name)));
			}
			if (filter.getSortByField() == SORT_BY_ADDRESS) {
				criteriaQuery.orderBy(criteriaBuilder.asc(root.get(Location_.address)));
			}
		} else if (filter.getSortOrder() == SORT_DESC) {
			if (filter.getSortByField() == SORT_BY_NAME) {
				criteriaQuery.orderBy(criteriaBuilder.desc(root.get(Location_.name)));
			}
			if (filter.getSortByField() == SORT_BY_ADDRESS) {
				criteriaQuery.orderBy(criteriaBuilder.desc(root.get(Location_.address)));
			}
		} else {
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get(Location_.id)));
		}
	}

	/**
	 * Method builds predicate based on given LocationFilter parameters.
	 * 
	 * @param root
	 *            a root of predicate
	 * 
	 * @param criteriaQuery
	 *            a query of predicate.
	 * 
	 * @param criteriaBuilder
	 *            a criteria builder of predicate.
	 * 
	 * @return a Predicate example to search locations in database by given
	 *         filter parameters.
	 */
	@Override
	public Predicate toPredicate(Root<Location> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
		setSortingParameters(root, criteriaQuery, criteriaBuilder);
		if (filter != null) {
			findByName();
			findByAddress();
		}
		if (list.size() == 0) {
			return null;
		}
		Specifications<Location> spec = Specifications.where(list.get(0));
		for (int i = 1; i < list.size(); i++) {
			spec = spec.and(list.get(i));
		}
		return spec.toPredicate(root, criteriaQuery, criteriaBuilder);
	}

}
