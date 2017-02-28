/*
 * LocationDAOImpl
 * 1.0
 * 3 Jan 2017
 * Copyright (c) Oleksandr Butyter
 */
package com.softserve.edu.schedule.dao.implementation;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.LocationDAO;
import com.softserve.edu.schedule.dto.filter.LocationFilter;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.entity.Location;
import com.softserve.edu.schedule.service.implementation.specification.LocationFilterSpecification;

/**
 * The DAO class to handle the database operation required to manipulate a
 * Location entity.
 * 
 * @version 1.0 3 Jan 2017
 * @author Oleksandr Butyter
 *
 */
@Repository
public class LocationDAOImpl extends CrudDAOImpl<Location> implements LocationDAO {

	/**
	 * Constructor of LocationDAOImpl
	 */
	public LocationDAOImpl() {
		super(Location.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.dao.LocationDAO#getLocationsByField(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public List<Location> getLocationsByField(final String field, final String pattern) {
		CriteriaBuilder builder = getEm().getCriteriaBuilder();
		CriteriaQuery<Location> cq = builder.createQuery(Location.class);
		Root<Location> root = cq.from(Location.class);
		cq.where(builder.like(root.get(field), pattern));
		return getEm().createQuery(cq).getResultList();
	}

	/* (non-Javadoc)
	 * @see com.softserve.edu.schedule.dao.LocationDAO#getLocationsPageWithFilter(com.softserve.edu.schedule.dto.filter.LocationFilter, com.softserve.edu.schedule.dto.filter.Paginator)
	 */
	@Override
	public List<Location> getLocationsPageWithFilter(final LocationFilter locationFilter, final Paginator locationPaginator) {
		CriteriaBuilder builder = getEm().getCriteriaBuilder();
		CriteriaQuery<Location> criteriaQuery = builder.createQuery(Location.class);
		Root<Location> root = criteriaQuery.from(Location.class);
		Predicate predicate = new LocationFilterSpecification(locationFilter).toPredicate(root, criteriaQuery, builder);
		if (predicate != null) {
			criteriaQuery.where(predicate);
		}
		locationPaginator.setPagesCount(getCountOfLocationsWithFilter(locationFilter));
		return getEm().createQuery(criteriaQuery).setFirstResult(locationPaginator.getOffset())
				.setMaxResults(locationPaginator.getPageSize()).getResultList();
	}

	/* (non-Javadoc)
	 * @see com.softserve.edu.schedule.dao.LocationDAO#getCountOfLocationsWithFilter(com.softserve.edu.schedule.dto.filter.LocationFilter)
	 */
	@Override
	public Long getCountOfLocationsWithFilter(final LocationFilter locationFilter) {
		 CriteriaBuilder cb = getEm().getCriteriaBuilder();
	        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
	        Root<Location> root = cq.from(Location.class);
	        cq.select(cb.countDistinct(root));
	        Predicate predicate = new LocationFilterSpecification(locationFilter).toPredicate(root, cq, cb);
	        if (predicate != null) {
	            cq.where(predicate);
	        }
	        return getEm().createQuery(cq).getSingleResult();
	}

}
