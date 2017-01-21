/*
 * LocationDAO
 * 1.0
 * 3 Jan 2017
 * Copyright (c) Oleksandr Butyter
 */
package com.softserve.edu.schedule.dao;

import com.softserve.edu.schedule.entity.Location;

/**
 * A simple DAO interface to handle the database operation required to
 * manipulate Location.
 *
 * @version 1.0 3 Jan 2017
 * @author Oleksandr Butyter
 *
 */
public interface LocationDAO extends CrudDAO<Location> {
	
	/**
     * Method deletes existing location from database by id.
     *
     * @param id
     *            location id to delete
     */
    void deleteById(final Long id);
	
}
