/*
 * LocationService
 * 1.0
 * 3 Jan 2017
 * Copyright (c) Oleksandr Butyter
 */
package com.softserve.edu.schedule.service;

import java.util.List;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.entity.Location;

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
    Location getById(final Long id);

    /**
     * Method returns all locations from database.
     * 
     * @return all locations from database
     */
    List<Location> getAll();

    /**
     * Method returns list of locations which suit condition of search.
     * 
     * @param field
     *            field for search
     * @param pattern
     *            pattern for search
     * @return list of locations which suit condition of search
     */
    List<Location> search(final String field, final String pattern);

    /**
     * Method returns list of sorted locations.
     * 
     * @param field
     *            field for sort
     * @param order
     *            ASC or DESC
     * @return list of sorted locations
     */
    List<Location> sort(final String field, final Order order);

    /**
     * Method creates new location in database.
     * 
     * @param location
     *            new location
     */
    void create(final Location location);

    /**
     * Method updates existing location in database.
     * 
     * @param location
     *            location to update
     */
    void update(final Location location);

    /**
     * Method deletes existing location from database.
     * 
     * @param location
     *            location to delete
     */
    void delete(final Location location);

    /**
     * Method returns list of sorted locations by count of rooms.
     * 
     * @return list of sorted locations by count of rooms
     */
    List<Location> sortByCountRooms(final Order order);

}
