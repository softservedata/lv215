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
	 * Method deletes existing location from database by id.
	 *
	 * @param id
	 *            location id to delete
	 */
	void deleteById(final Long id);

	/**
	 * Method returns list of locations which suit condition of search by field.
	 * 
	 * @param field
	 *            field for search
	 * @param pattern
	 *            pattern for search
	 * @return list of locations which suit condition of search by field
	 */
	List<Location> getLocationsByField(final String field, final String pattern);
}
