/* ControllerConst 1.0 01/20/2017 */
package com.softserve.edu.schedule.controller;

/**
 * An interface to storage controllers model attributes and mappings URL.
 *
 * @version 1.0 20 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public interface ControllerConst {

	/**
	 * An interface to storage room controller model attributes and mappings
	 * URL.
	 *
	 * @version 1.0 20 January 2017
	 *
	 * @author Petro Zelyonka
	 *
	 * @since 1.8
	 */
	public interface RoomControllerConst {
		/**
		 * Room model attribute name.
		 */
		String ROOM_MODEL_ATTR = "room";

		/**
		 * Rooms model attribute name.
		 */
		String ROOMS_MODEL_ATTR = "rooms";

		/**
		 * Locations model attribute name.
		 */
		String LOCATIONS_MODEL_ATTR = "locations";

		/**
		 * Room equipments model attribute name.
		 */
		String EQUIPMENTS_MODEL_ATTR = "equipments";

		/**
		 * Filter model attribute name.
		 */
		String FILTER_MODEL_ATTR = "roomFilter";

		/**
		 * Path variable for room id name.
		 */
		String PATH_VAR_ID = "id";

		/**
		 * Rooms list URL name.
		 */
		String ROOMS_LIST_URL = "rooms/list";

		/**
		 * Rooms list redirect URL name.
		 */
		String ROOMS_REDIRECT_URL = "redirect:/rooms";

		/**
		 * Show room details URL name.
		 */
		String ROOM_SHOW_URL = "rooms/show";

		/**
		 * Show room details mapping URL name.
		 */
		String ROOM_SHOW_MAPPING = "/{id}";

		/**
		 * Edit room information URL name.
		 */
		String ROOM_EDIT_URL = "rooms/edit";

		/**
		 * Edit room information mapping URL name.
		 */
		String ROOM_EDIT_MAPPING = "/edit/{id}";

		/**
		 * Create new room URL name.
		 */
		String ROOM_CREATE_URL = "rooms/create";

		/**
		 * Create new room mapping URL name.
		 */
		String ROOM_CREATE_MAPPING = "/create";

		/**
		 * Delete room mapping URL name.
		 */
		String ROOM_DELETE_MAPPING = "/delete/{id}";
	}

	/**
	 * An interface to storage location controller model attributes and mappings
	 * URL.
	 *
	 * @version 1.0 20 January 2017
	 *
	 * @author Oleksandr Butyter
	 */
	public interface LocationControllerConst {
		/**
		 * Search model attribute name.
		 */
		String SEARCH_MODEL_ATTR = "search";

		/**
		 * Locations model attribute name.
		 */
		String LOCATIONS_MODEL_ATTR = "locations";

		/**
		 * Location form model attribute name.
		 */
		String LOCATION_FORM_MODEL_ATTR = "locationForm";
		
		/**
		 * Locations list URL.
		 */
		String LOCATIONS_LIST_URL = "locations/list";

		/**
		 * Locations list redirect URL.
		 */
		String LOCATIONS_REDIRECT_URL = "redirect:/locations";
		
		/**
		 * Create new location URL.
		 */
		String LOCATION_CREATE_URL = "locations/create";

		/**
		 * Edit location information URL.
		 */
		String LOCATION_EDIT_URL = "locations/edit";
		
		/**
		 * Locations mapping URL.
		 */
		String LOCATIONS_MAPPING = "/locations";
		
		/**
		 * Locations sort by name (asc) mapping URL.
		 */
		String LOCATIONS_SORT_BY_NAME_ASC_MAPPING = "/locations/sortbynameasc";
		
		/**
		 * Locations sort by name (desc) mapping URL.
		 */
		String LOCATIONS_SORT_BY_NAME_DESC_MAPPING = "/locations/sortbynamedesc";
		
		/**
		 * Locations sort by address (asc) mapping URL.
		 */
		String LOCATIONS_SORT_BY_ADDRESS_ASC_MAPPING = "/locations/sortbyaddressasc";
		
		/**
		 * Locations sort by address (desc) mapping URL.
		 */
		String LOCATIONS_SORT_BY_ADDRESS_DESC_MAPPING = "/locations/sortbyaddressdesc";
		
		/**
		 * Locations sort by count rooms (asc) mapping URL.
		 */
		String LOCATIONS_SORT_BY_COUNT_ROOM_ASC_MAPPING = "/locations/sortbycountroomsasc";
		
		/**
		 * Locations sort by count rooms (desc) mapping URL.
		 */
		String LOCATIONS_SORT_BY_COUNT_ROOM_DESC_MAPPING = "/locations/sortbycountroomsdesc";
		
		/**
		 * Locations search by name mapping URL.
		 */
		String LOCATIONS_SEARCH_BY_NAME_MAPPING = "/locations/searchByName";
		
		/**
		 * Locations search by address mapping URL.
		 */
		String LOCATIONS_SEARCH_BY_ADDRESS_MAPPING = "/locations/searchByAddress";
		
		/**
		 * Delete location mapping URL name.
		 */
		String LOCATION_DELETE_MAPPING = "/locations/delete/";
		
		/**
		 * Create new location mapping URL name.
		 */
		String LOCATION_CREATE_MAPPING = "/locations/create";
		
		/**
		 * Edit location information mapping URL name.
		 */
		String LOCATION_EDIT_MAPPING = "/locations/edit/";
		
	}

	/**
	 * An interface that stores all constants for USerGroup controller
	 * 
	 * @author Andrew
	 *
	 */

	public interface UserGroupControllerConst {
		/**
		 * UserGroup model attribute name.
		 */
		String USERGROUP_MODEL_ATTR = "userGroupForm";

		/**
		 * UserGroups model attribute name.
		 */
		String USERGROUPS_MODEL_ATTR = "usergroups";

		/**
		 * UserGroup create mapping name.
		 */
		String USERGROUP_CREATE_MAPPING = "/create";

		/**
		 * UserGroup delete mapping name.
		 */
		String USERGROUP_DELETE_MAPPING = "/delete/{id}";

		/**
		 * UserGroup create url name.
		 */
		String USERGROUP_CREATE_URL = "usergroups/create";

		/**
		 * UserGroup edit url name.
		 */
		String USERGROUP_EDIT_URL = "usergroups/edit";

		/**
		 * UserGroup edit mapping name.
		 */
		String USERGROUP_EDIT_MAPPING = "/edit/{id}";

		/**
		 * UserGroup redirect url name.
		 */
		String USERGROUP_REDIRECT_URL = "redirect:/usergroups";

		/**
		 * UserGroup list url name.
		 */
		String USERGROUP_LIST_URL = "usergroups/list";

	}

}
