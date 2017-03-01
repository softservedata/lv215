/* LocationControllerConst 1.0 01/03/2017 */
package com.softserve.edu.schedule.controller.constants;

/**
 * A class to storage location controller model attributes and mappings
 * URL.
 *
 * @version 1.0 1 March 2017
 *
 * @author Oleksandr Butyter
 */
public final class LocationControllerConst {
	 /**
     * Filter model attribute name.
     */
	public static final String FILTER_MODEL_ATTR = "locationFilter";

    /**
     * Paginator model attribute name.
     */
	public static final String LOCATION_PAGINATOR_MODEL_ATTR = "locationPaginator";

    /**
     * Locations model attribute name.
     */
	public static final String LOCATIONS_MODEL_ATTR = "locations";

    /**
     * Location form model attribute name.
     */
	public static final String LOCATION_FORM_MODEL_ATTR = "locationForm";

    /**
     * Location map model attribute name.
     */
	public static final String LOCATION_MAP_MODEL_ATTR = "map";

    /**
     * Locations list URL.
     */
	public static final String LOCATIONS_LIST_URL = "locations/list";

    /**
     * Locations list redirect URL.
     */
	public static final String LOCATIONS_REDIRECT_URL = "redirect:/locations";

    /**
     * Create new location URL.
     */
	public static final String LOCATION_CREATE_URL = "locations/create";

    /**
     * Edit location information URL.
     */
	public static final String LOCATION_EDIT_URL = "locations/edit";

    /**
     * Map location URL.
     */
	public static final String LOCATION_MAP_URL = "locations/map";

    /**
     * Locations mapping URL.
     */
	public static final String LOCATIONS_MAPPING = "/locations";

    /**
     * Locations sort by count rooms (asc) mapping URL.
     */
	public static final String LOCATIONS_SORT_BY_COUNT_ROOM_ASC_MAPPING = "/locations/sortbycountroomsasc";

    /**
     * Locations sort by count rooms (desc) mapping URL.
     */
	public static final String LOCATIONS_SORT_BY_COUNT_ROOM_DESC_MAPPING = "/locations/sortbycountroomsdesc";

    /**
     * Delete location mapping URL name.
     */
	public static final String LOCATION_DELETE_MAPPING = "/locations/delete/";

    /**
     * Create new location mapping URL name.
     */
	public static final String LOCATION_CREATE_MAPPING = "/locations/create";

    /**
     * Edit location information mapping URL name.
     */
	public static final String LOCATION_EDIT_MAPPING = "/locations/edit/";

    /**
     * Map location mapping URL name.
     */
	public static final String LOCATION_MAP_MAPPING = "/locations/map/";
	/**
     * Location edit permissions.
     */
	public static final String LOCATION_EDIT_PERMISSIONS = "hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')";
}
