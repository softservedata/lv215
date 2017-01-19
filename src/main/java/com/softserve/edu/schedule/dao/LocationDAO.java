/*
 * LocationDAO
 * 1.0
 * 3 Jan 2017
 * Copyright (c) Oleksandr Butyter
 */
package com.softserve.edu.schedule.dao;

import java.util.List;

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
	 * Method returns all locations from database with room details.
	 * 
	 * @return all locations from database with room details
	 */
	List<Location> getAllWithDetails();
	
	/**
     * Method deletes existing location from database by id.
     *
     * @param id
     *            location id to delete
     */
    void deleteById(final Long id);
	
	/**
     * Method returns list of sorted locations.
     * 
     * @param field
     *            field for sort
     * @param order
     *            ASC or DESC
     * @return list of sorted locations
     */
	List<Location> sortByFields(final String field, final Order order);
}
