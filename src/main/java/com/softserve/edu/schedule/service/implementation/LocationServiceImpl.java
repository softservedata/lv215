/*
 * LocationServiceImpl
 * 1.0
 * 3 Jan 2017
 * Copyright (c) Oleksandr Butyter
 */
package com.softserve.edu.schedule.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.dao.LocationDAO;
import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.entity.Location;
import com.softserve.edu.schedule.service.LocationService;

/**
 * The service-level class to handle operation with locations.
 * 
 * @version 1.0 3 Jan 2017
 * @author Oleksandr Butyter
 *
 */
@Service("locationService")
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationDAO locationDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.LocationService#getById(java.lang.
	 * Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public Location getById(final Long id) {
		return locationDAO.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserve.edu.schedule.service.LocationService#getAll()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Location> getAll() {
		return locationDAO.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserve.edu.schedule.service.LocationService#search(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Location> search(final String field, final String pattern) {
		return locationDAO.search(field, pattern);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.LocationService#sort(java.lang.String,
	 * com.softserve.edu.schedule.dao.Order)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Location> sort(final String field, final Order order) {
		return locationDAO.sort(field, order);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.LocationService#create(com.softserve.
	 * edu.schedule.entity.Location)
	 */
	@Override
	@Transactional
	public void create(final Location location) {
		locationDAO.create(location);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.LocationService#update(com.softserve.
	 * edu.schedule.entity.Location)
	 */
	@Override
	@Transactional
	public void update(final Location location) {
		locationDAO.update(location);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.LocationService#delete(com.softserve.
	 * edu.schedule.entity.Location)
	 */
	@Override
	@Transactional
	public void delete(final Location location) {
		locationDAO.delete(location);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.LocationService#sortByCountRooms(com.
	 * softserve.edu.schedule.dao.Order)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Location> sortByCountRooms(final Order order) {
		List<Location> locations = locationDAO.getAll();
		if (order == Order.ASC) {
			locations.sort((l1, l2) -> l1.getRooms().size() - l2.getRooms().size());
		} else {
			locations.sort((l1, l2) -> l2.getRooms().size() - l1.getRooms().size());
		}
		return locations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.LocationService#getAllWithDetails()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Location> getAllWithDetails() {
		return locationDAO.getAllWithDetails();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.LocationService#sortByFields(java.lang
	 * .String, com.softserve.edu.schedule.dao.Order)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Location> sortByFields(String field, Order order) {
		return locationDAO.sortByFields(field, order);
	}

}
