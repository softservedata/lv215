/*
 * LocationController
 * 1.0
 * 5 Jan 2017
 * Copyright (c) Oleksandr Butyter
 */
package com.softserve.edu.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.service.LocationService;

/**
 * A controller class of locations pages.
 *
 * @version 1.0 5 January 2017
 *
 * @author Oleksandr Butyter
 *
 */
@Controller
public class LocationController implements ControllerConst.LocationControllerConst {

	/**
	 * LocationService example to provide locations list to the model.
	 */
	@Autowired
	private LocationService locationService;

	/**
	 * Method provides model attribute for search.
	 * 
	 * @return new LocationDTO object
	 */
	@ModelAttribute(SEARCH_MODEL_ATTR)
	public LocationDTO getLocationDTO() {
		return new LocationDTO();
	}

	/**
	 * Method controls view of locations list page.
	 * 
	 * @param model
	 *            locations list page model
	 * @return locations list page URL
	 */
	@RequestMapping(LOCATIONS_MAPPING)
	public String showList(Model model) {
		model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.getAll());
		return LOCATIONS_LIST_URL;
	}

	/**
	 * Method provides sorting of location list by name in ascending order.
	 * 
	 * @param model
	 *            locations list page model
	 * @return locations list page URL
	 */
	@RequestMapping(LOCATIONS_SORT_BY_NAME_ASC_MAPPING)
	public String sortByNameAsc(Model model) {
		model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.sortByName(Order.ASC));
		return LOCATIONS_LIST_URL;
	}

	/**
	 * Method provides sorting of location list by name in descending order.
	 * 
	 * @param model
	 *            locations list page model
	 * @return locations list page URL
	 */
	@RequestMapping(LOCATIONS_SORT_BY_NAME_DESC_MAPPING)
	public String sortByNameDesc(Model model) {
		model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.sortByName(Order.DESC));
		return LOCATIONS_LIST_URL;
	}

	/**
	 * Method provides sorting of location list by address in ascending order.
	 * 
	 * @param model
	 *            locations list page model
	 * @return locations list page URL
	 */
	@RequestMapping(LOCATIONS_SORT_BY_ADDRESS_ASC_MAPPING)
	public String sortByAddressAsc(Model model) {
		model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.sortByAddress(Order.ASC));
		return LOCATIONS_LIST_URL;
	}

	/**
	 * Method provides sorting of location list by address in descending order.
	 * 
	 * @param model
	 *            locations list page model
	 * @return locations list page URL
	 */
	@RequestMapping(LOCATIONS_SORT_BY_ADDRESS_DESC_MAPPING)
	public String sortByAddressDesc(Model model) {
		model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.sortByAddress(Order.DESC));
		return LOCATIONS_LIST_URL;
	}

	/**
	 * Method provides sorting of location list by count of rooms in ascending
	 * order.
	 * 
	 * @param model
	 *            locations list page model
	 * @return locations list page URL
	 */
	@RequestMapping(LOCATIONS_SORT_BY_COUNT_ROOM_ASC_MAPPING)
	public String sortByCountRoomsAsc(Model model) {
		model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.sortByCountRooms(Order.ASC));
		return LOCATIONS_LIST_URL;
	}

	/**
	 * Method provides sorting of location list by count of rooms in descending
	 * order.
	 * 
	 * @param model
	 *            locations list page model
	 * @return locations list page URL
	 */
	@RequestMapping(LOCATIONS_SORT_BY_COUNT_ROOM_DESC_MAPPING)
	public String sortByCountRoomsDesc(Model model) {
		model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.sortByCountRooms(Order.DESC));
		return LOCATIONS_LIST_URL;

	}

	/**
	 * Method provides deleting location by id.
	 * 
	 * @param id
	 *            id location to delete
	 * @return locations list page URL (redirect)
	 */
	@RequestMapping(LOCATION_DELETE_MAPPING + "{id}")
	public String delete(@PathVariable Long id) {
		locationService.deleteById(id);
		return LOCATIONS_REDIRECT_URL;
	}

	/**
	 * Method prepares form for creating location
	 * 
	 * @param model
	 *            locations create page model
	 * @return locations create page URL
	 */
	@RequestMapping(LOCATION_CREATE_MAPPING)
	public String createForm(Model model) {
		model.addAttribute(LOCATION_FORM_MODEL_ATTR, new LocationDTO());
		return LOCATION_CREATE_URL;
	}

	/**
	 * Method creates new location
	 * 
	 * @param location
	 *            locations create page model
	 * @return locations list page URL (redirect)
	 */
	@RequestMapping(value = LOCATION_CREATE_MAPPING, method = RequestMethod.POST)
	public String create(@ModelAttribute(LOCATION_FORM_MODEL_ATTR) LocationDTO location) {
		locationService.create(location);
		return LOCATIONS_REDIRECT_URL;
	}

	/**
	 * Method prepares form for editing location
	 * 
	 * @param model
	 *            locations edit page model
	 * @return locations edit page URL
	 */
	@RequestMapping(LOCATION_EDIT_MAPPING + "{id}")
	public String updateForm(@PathVariable Long id, Model model) {
		model.addAttribute(LOCATION_FORM_MODEL_ATTR, locationService.getById(id));
		return LOCATION_EDIT_URL;
	}

	/**
	 * Method edits current location
	 * 
	 * @param location
	 *            locations edit page model
	 * @return locations list page URL (redirect)
	 */
	@RequestMapping(value = LOCATION_EDIT_MAPPING + "{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute(LOCATION_FORM_MODEL_ATTR) LocationDTO location) {
		locationService.update(location);
		return LOCATIONS_REDIRECT_URL;
	}

	/**
	 * Method provides searching location by name.
	 * 
	 * @param location
	 *            search pattern model
	 * @param model
	 *            locations list page model
	 * @return locations list page URL
	 */
	@RequestMapping(value = LOCATIONS_SEARCH_BY_NAME_MAPPING, method = RequestMethod.POST)
	public String searchByName(@ModelAttribute(SEARCH_MODEL_ATTR) LocationDTO location, Model model) {
		model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.searchByName(location.getName()));
		return LOCATIONS_LIST_URL;
	}

	/**
	 * Method provides searching location by address.
	 * 
	 * @param location
	 *            search pattern model
	 * @param model
	 *            locations list page model
	 * @return locations list page URL
	 */
	@RequestMapping(value = LOCATIONS_SEARCH_BY_ADDRESS_MAPPING, method = RequestMethod.POST)
	public String searchByAddress(@ModelAttribute(SEARCH_MODEL_ATTR) LocationDTO location, Model model) {
		model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.searchByAddress(location.getAddress()));
		return LOCATIONS_LIST_URL;
	}
}