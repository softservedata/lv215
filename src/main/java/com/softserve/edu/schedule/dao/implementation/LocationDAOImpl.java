/*
 * LocationDAOImpl
 * 1.0
 * 3 Jan 2017
 * Copyright (c) Oleksandr Butyter
 */
package com.softserve.edu.schedule.dao.implementation;

import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.LocationDAO;
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

}
