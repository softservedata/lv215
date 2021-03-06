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
