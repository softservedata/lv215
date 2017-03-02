/*
 * LocationServiceImpl
 * 1.0
 * 3 Jan 2017
 * Copyright (c) Oleksandr Butyter
 */
package com.softserve.edu.schedule.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.aspects.PerfomanceLoggable;
import com.softserve.edu.schedule.dao.LocationDAO;
import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.dto.filter.LocationFilter;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.entity.Location;
import com.softserve.edu.schedule.entity.Location_;
import com.softserve.edu.schedule.service.LocationService;
import com.softserve.edu.schedule.service.implementation.dtoconverter.LocationDTOConverter;

/**
 * The service-level class to handle operation with locations.
 * 
 * @version 1.0 3 Jan 2017
 * @author Oleksandr Butyter
 *
 */
@Service
@PerfomanceLoggable
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationDAO locationDAO;

	@Autowired
	private LocationDTOConverter locationDTOConverter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.LocationService#getById(java.lang.
	 * Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public LocationDTO getById(final Long id) {
		return locationDTOConverter.getDTO(locationDAO.getById(id));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserve.edu.schedule.service.LocationService#getAll()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<LocationDTO> getAll() {
		return locationDAO.getAll().stream().map(e -> locationDTOConverter.getDTO(e)).collect(Collectors.toList());
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
	public void create(final LocationDTO location) {
		locationDAO.create(locationDTOConverter.getEntity(location));
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
	public void update(final LocationDTO location) {
		locationDAO.update(locationDTOConverter.getEntity(location));
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
	public List<LocationDTO> sortByCountRooms(final Order order) {
		List<LocationDTO> locations = getAll();
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
	 * com.softserve.edu.schedule.service.LocationService#deleteById(java.lang.
	 * Long)
	 */
	@Override
	@Transactional
	public void deleteById(final Long id) {
		Location location = locationDAO.getById(id);
		if (location.getRooms().size() == 0) {
		locationDAO.delete(location);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.LocationService#getLocationsByName(
	 * java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<LocationDTO> getLocationsByName(final String locationName) {
		return locationDAO.getLocationsByField(Location_.name.getName(), locationName).stream()
				.map(e -> locationDTOConverter.getDTO(e)).collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserve.edu.schedule.service.LocationService#
	 * getLocationsPageWithFilter(com.softserve.edu.schedule.dto.filter.
	 * LocationFilter, com.softserve.edu.schedule.dto.filter.Paginator)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<LocationDTO> getLocationsPageWithFilter(final LocationFilter locationFilter, final Paginator locationPaginator) {
		return locationDAO.getLocationsPageWithFilter(locationFilter, locationPaginator).stream()
				.map(e -> locationDTOConverter.getDTO(e)).collect(Collectors.toList());
	}

}
