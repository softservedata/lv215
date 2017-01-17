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
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.LocationDAO;
import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.entity.Location;

/**
 * The DAO class to handle the database operation required to manipulate a
 * Location entity.
 * 
 * @version 1.0 3 Jan 2017
 * @author Oleksandr Butyter
 *
 */
@Repository("locationDAO")
public class LocationDAOImpl extends CrudDAOImpl<Location>
        implements LocationDAO {

    /**
     * Constructor of LocationDAOImpl
     */
    public LocationDAOImpl() {
        super(Location.class);
    }

	/* (non-Javadoc)
	 * @see com.softserve.edu.schedule.dao.LocationDAO#getAllWithDetails()
	 */
	@Override
	public List<Location> getAllWithDetails() {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Location> cq = builder.createQuery(Location.class);
        Root<Location> root = cq.from(Location.class);
        root.fetch("rooms", JoinType.LEFT);
        return getEm().createQuery(cq).getResultList();
    }

	/* (non-Javadoc)
	 * @see com.softserve.edu.schedule.dao.LocationDAO#sortByFields(java.lang.String, com.softserve.edu.schedule.dao.Order)
	 */
	@Override
	public List<Location> sortByFields(String field, Order order) {
		CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Location> cq = builder.createQuery(Location.class);
        Root<Location> root = cq.from(Location.class);
        root.fetch("rooms", JoinType.LEFT);
        if (order == Order.ASC) {
            cq.orderBy(builder.asc(root.get(field)));
        } else {
            cq.orderBy(builder.desc(root.get(field)));
        }
        return getEm().createQuery(cq).getResultList();
	}

}
