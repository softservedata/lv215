/*
 * LocationDAO
 * 1.0
 * 3 Jan 2017
 * Copyright (c) Oleksandr Butyter
 */
package com.softserve.edu.schedule.dao;

import java.util.List;

import com.softserve.edu.schedule.dto.filter.LocationFilter;
import com.softserve.edu.schedule.dto.filter.Paginator;
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
	 * Method returns list of locations which suit condition of search by field.
	 * 
	 * @param field
	 *            field for search
	 * @param pattern
	 *            pattern for search
	 * @return list of locations which suit condition of search by field
	 */
	List<Location> getLocationsByField(final String field, final String pattern);

	/**
     * Method returns list of locations which suit filter condition.
     * 
     * @param locationFilter
     *            filter to apply.
     * @param locationPaginator
     *            locationPaginator to set
     * @return list of locations which suit filter condition
     */
	public List<Location> getLocationsPageWithFilter(final LocationFilter locationFilter,
			final Paginator locationPaginator);
	
	 /**
     * Count locations entities in the database with specified filter
     *
     * @param predicate
     *            a predicate to apply.
     *
     * @return Count of the location entities in the database with specified
     *         predicate.
     */
    Long getCountOfLocationsWithFilter(final LocationFilter locationFilter);
}

