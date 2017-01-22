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

@Controller
public class LocationController implements ControllerConst.LocationControllerConst {

	@Autowired
	private LocationService locationService;

	@ModelAttribute(SEARCH_MODEL_ATTR)
	public LocationDTO getLocationDTO() {
		return new LocationDTO();
	}

	@RequestMapping(LOCATIONS_MAPPING)
	public String showList(Model model) {
		model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.getAll());
		return LOCATIONS_LIST_URL;
	}

	@RequestMapping(LOCATIONS_SORT_BY_NAME_ASC_MAPPING)
	public String sortByNameAsc(Model model) {
		model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.sortByName(Order.ASC));
		return LOCATIONS_LIST_URL;

	}

	@RequestMapping(LOCATIONS_SORT_BY_NAME_DESC_MAPPING)
	public String sortByNameDesc(Model model) {
		model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.sortByName(Order.DESC));
		return LOCATIONS_LIST_URL;

	}

	@RequestMapping(LOCATIONS_SORT_BY_ADDRESS_ASC_MAPPING)
	public String sortByAddressAsc(Model model) {
		model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.sortByAddress(Order.ASC));
		return LOCATIONS_LIST_URL;

	}

	@RequestMapping(LOCATIONS_SORT_BY_ADDRESS_DESC_MAPPING)
	public String sortByAddressDesc(Model model) {
		model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.sortByAddress(Order.DESC));
		return LOCATIONS_LIST_URL;

	}

	@RequestMapping(LOCATIONS_SORT_BY_COUNT_ROOM_ASC_MAPPING)
	public String sortByCountRoomsAsc(Model model) {
		model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.sortByCountRooms(Order.ASC));
		return LOCATIONS_LIST_URL;

	}

	@RequestMapping(LOCATIONS_SORT_BY_COUNT_ROOM_DESC_MAPPING)
	public String sortByCountRoomsDesc(Model model) {
		model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.sortByCountRooms(Order.DESC));
		return LOCATIONS_LIST_URL;

	}

	@RequestMapping(LOCATION_DELETE_MAPPING + "{id}")
	public String delete(@PathVariable Long id) {
		locationService.deleteById(id);
		return LOCATIONS_REDIRECT_URL;
	}

	@RequestMapping(LOCATION_CREATE_MAPPING)
	public String createForm(Model model) {
		model.addAttribute(LOCATION_FORM_MODEL_ATTR, new LocationDTO());
		return LOCATION_CREATE_URL;
	}

	@RequestMapping(value = LOCATION_CREATE_MAPPING, method = RequestMethod.POST)
	public String create(@ModelAttribute(LOCATION_FORM_MODEL_ATTR) LocationDTO location) {
		locationService.create(location);
		return LOCATIONS_REDIRECT_URL;
	}

	@RequestMapping(LOCATION_EDIT_MAPPING+"{id}")
	public String updateForm(@PathVariable Long id, Model model) {
		model.addAttribute(LOCATION_FORM_MODEL_ATTR, locationService.getById(id));
		return LOCATION_EDIT_URL;
	}

	@RequestMapping(value = LOCATION_EDIT_MAPPING+"{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute(LOCATION_FORM_MODEL_ATTR) LocationDTO location) {
		locationService.update(location);
		return LOCATIONS_REDIRECT_URL;
	}

	@RequestMapping(value = LOCATIONS_SEARCH_BY_NAME_MAPPING, method = RequestMethod.POST)
	public String searchByName(@ModelAttribute(SEARCH_MODEL_ATTR) LocationDTO location, Model model) {
		model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.searchByName(location.getName()));
		return LOCATIONS_LIST_URL;
	}

	@RequestMapping(value = LOCATIONS_SEARCH_BY_ADDRESS_MAPPING, method = RequestMethod.POST)
	public String searchByAddress(@ModelAttribute(SEARCH_MODEL_ATTR) LocationDTO location, Model model) {
		model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.searchByAddress(location.getAddress()));
		return LOCATIONS_LIST_URL;
	}
}