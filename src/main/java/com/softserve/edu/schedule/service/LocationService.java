/*
 * LocationService
 * 1.0
 * 3 Jan 2017
 * Copyright (c) Oleksandr Butyter
 */
package com.softserve.edu.schedule.service;

import java.util.List;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.dto.filter.LocationFilter;
import com.softserve.edu.schedule.dto.filter.Paginator;

/**
 * The service-level interface to handle operation with locations.
 * 
 * @version 1.0 3 Jan 2017
 * @author Oleksandr Butyter
 *
 */
public interface LocationService {

	/**
	 * Method returns location by id.
	 * 
	 * @param id
	 *            id of location which will be got
	 * @return the location which has current id
	 */
	LocationDTO getById(final Long id);

	/**
	 * Method returns all locations from database.
	 * 
	 * @return all locations from database
	 */
	List<LocationDTO> getAll();

	/**
	 * Method creates new location in database.
	 * 
	 * @param location
	 *            new location
	 */
	void create(final LocationDTO location);

	/**
	 * Method updates existing location in database.
	 * 
	 * @param location
	 *            location to update
	 */
	void update(final LocationDTO location);

	/**
	 * Method deletes existing location from database.
	 * 
	 * @param location
	 *            location to delete
	 */
	void delete(final LocationDTO location);

	/**
	 * Method returns list of sorted locations by count of rooms.
	 * 
	 * @return list of sorted locations by count of rooms
	 */
	List<LocationDTO> sortByCountRooms(final Order order);

	/**
	 * Method deletes existing location from database by id.
	 * 
	 * @param id
	 *            location id to delete
	 */
	void deleteById(final Long id);

	/**
	 * Method returns list of locations which suit condition of search by name.
	 * 
	 * @param locationName
	 *            location name for search
	 * @return list of locations which suit condition of search by name
	 */
	List<LocationDTO> getLocationsByName(final String locationName);

	/**
     * Method returns list of locations which suit filter condition.
     * 
     * @param locationFilter
     *            filter to apply.
     * @param locationPaginator
     *            locationPaginator to set
     * @return list of locations which suit filter condition
     */
	List<LocationDTO> getLocationsPageWithFilter(final LocationFilter locationFilter,
			final Paginator locationPaginator);

}
