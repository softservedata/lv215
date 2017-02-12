/*
 * LocationController
 * 1.0
 * 5 Jan 2017
 * Copyright (c) Oleksandr Butyter
 */
package com.softserve.edu.schedule.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.dto.filter.LocationFilter;
import com.softserve.edu.schedule.dto.filter.Paginator;
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
@SessionAttributes({ControllerConst.LocationControllerConst.FILTER_MODEL_ATTR,
        ControllerConst.LocationControllerConst.LOCATION_PAGINATOR_MODEL_ATTR})
public class LocationController
        implements ControllerConst.LocationControllerConst {

    /**
     * LocationService example to provide locations list to the model.
     */
    @Autowired
    private LocationService locationService;

    @ModelAttribute(FILTER_MODEL_ATTR)
    public LocationFilter getFilter() {
        return new LocationFilter();
    }

    @ModelAttribute(LOCATION_PAGINATOR_MODEL_ATTR)
    public Paginator getPaginator() {
        return new Paginator();
    }

    @RequestMapping(LOCATIONS_MAPPING)
    public String showLocationPage(final Model model,
            @ModelAttribute(FILTER_MODEL_ATTR) final LocationFilter filter,
            @ModelAttribute(LOCATION_PAGINATOR_MODEL_ATTR) final Paginator paginator) {
        model.addAttribute(LOCATIONS_MODEL_ATTR,
                locationService.getLocationsPageWithFilter(filter, paginator));
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
        model.addAttribute(LOCATIONS_MODEL_ATTR,
                locationService.sortByCountRooms(Order.ASC));
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
        model.addAttribute(LOCATIONS_MODEL_ATTR,
                locationService.sortByCountRooms(Order.DESC));
        return LOCATIONS_LIST_URL;

    }

    /**
     * Method provides deleting location by id.
     * 
     * @param id
     *            id location to delete
     * @return locations list page URL (redirect)
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
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
    @RequestMapping(value = LOCATION_CREATE_MAPPING,
            method = RequestMethod.POST)
    public String create(
            @ModelAttribute(LOCATION_FORM_MODEL_ATTR) @Valid LocationDTO location,
            BindingResult result) {
        if (result.hasErrors()) {
            return LOCATION_CREATE_URL;
        }
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
    @RequestMapping(LOCATION_EDIT_MAPPING + "{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute(LOCATION_FORM_MODEL_ATTR,
                locationService.getById(id));
        return LOCATION_EDIT_URL;
    }

    /**
     * Method edits current location
     * 
     * @param location
     *            locations edit page model
     * @return locations list page URL (redirect)
     */
    @RequestMapping(value = LOCATION_EDIT_MAPPING + "{id}",
            method = RequestMethod.POST)
    public String update(
            @ModelAttribute(LOCATION_FORM_MODEL_ATTR) @Valid LocationDTO location,
            BindingResult result) {
        if (result.hasErrors()) {
            return LOCATION_EDIT_URL;
        }
        locationService.update(location);
        return LOCATIONS_REDIRECT_URL;
    }

    /**
     * Method shows location on map
     * 
     * @param model
     *            locations map page model
     * @return locations map page URL
     */
    @RequestMapping(LOCATION_MAP_MAPPING + "{id}")
    public String showMap(@PathVariable Long id, Model model) {
        model.addAttribute(LOCATION_MAP_MODEL_ATTR,
                locationService.getById(id));
        return LOCATION_MAP_URL;
    }

}