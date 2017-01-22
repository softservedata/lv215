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
         * Room meetings model attribute name.
         */
        String MEETINGS_MODEL_ATTR = "meetings";

        /**
         * Filter model attribute name.
         */
        String FILTER_MODEL_ATTR = "roomFilter";
        
        /**
         * Date filter model attribute name.
         */
        String DATE_FILTER_MODEL_ATTR = "dateFilter";

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

    /**
     * An interface to storage Registration controller model attributes and
     * mappings URL.
     *
     * @version 1.0 20 January 2017
     *
     * @author Serhiy Dudynsky
     *
     * @since 1.8
     */
    public interface RegistrationControllerConst {

        /**
         * Register new user mapping.
         */
        String USER_REGIST_MAPPING_FROM_STARTPAGE = "registration";

        /**
         * Register new user mapping for administrator.
         */
        String USER_REGIST_MAPPING_FOR_ADMIN = "users/registration";

        /**
         * Registration model attribute name.
         */
        String USER_REGIST_MODEL_ATTR = "userFormCreate";

        /**
         * Registration mapping URL name.
         */
        String USER_REGIST_URL = "users/registration";

        /**
         * Redirect to start page mapping URL name.
         */
        String REDIRECT_STARTPAGE = "redirect:/";

        /**
         * Redirect to users page mapping URL name.
         */
        String REDIRECT_USERS_PAGE = "redirect:/users";
    }

    /**
     * An interface to storage User controller model attributes and
     * mappings URL.
     *
     * @version 1.0 20 January 2017
     *
     * @author Serhiy Dudynsky
     *
     * @since 1.8
     */
    public interface UserControllerConst {

        /**
         * Users mapping.
         */
        String USERS_MAPPING_FROM_HEADER = "/users";

        /**
         * Delete user mapping.
         */
        String DELETE_USER_MAPPING = "/users/delete/";

        /**
         * Edit user mapping.
         */
        String EDIT_USER_MAPPING = "/users/edit/";

        /**
         * Update user mapping.
         */
        String UPDATE_USER_MAPPING = "/updateUser/";

        /**
         * Save updated user mapping.
         */
        String SAVE_UPDATED_USER_MAPPING = "/users/edit/updateUser/saveUpdatedUser/";

        /**
         * Update user position mapping.
         */
        String UPDATE_POSITION_MAPPING = "/updateUserPosition/";

        /**
         * Save updated position user mapping.
         */
        String SAVE_UPDATED_POSITION_MAPPING = "/users/edit/updateUserPosition/saveUpdatedUserPosition/";

        /**
         * Ban user mapping.
         */
        String BAN_USER_MAPPING = "/users/banUser/";

        /**
         * Unban user mapping.
         */
        String UNBAN_USER_MAPPING = "/users/unBanUser/";

        /**
         * Change user role mapping.
         */
        String CHANGE_ROLE_MAPPING = "/changeRole/";

        /**
         * Save changed user role mapping.
         */
        String SAVE_CHANGED_ROLE_MAPPING = "/users/edit/changeRole/saveChangedRole/";

        /**
         * Sort by last name mapping - ascending.
         */
        String SORT_BY_LASTNAME_ASC_MAPPING = "/users/sortbylastnameasc";

        /**
         * Sort by last name mapping - descending.
         */
        String SORT_BY_LASTNAME_DESC_MAPPING = "/users/sortbylastnamedesc";

        /**
         * Sort by position mapping - ascending.
         */
        String SORT_BY_POSITION_ASC_MAPPING = "/users/sortbypositionasc";

        /**
         * Sort by position mapping - descending.
         */
        String SORT_BY_POSITION_DESC_MAPPING = "/users/sortbypositiondesc";
        
        /**
         * Search by last name mapping.
         */
        String SEARCH_BY_LASTNANE_MAPPING = "/user/searchByLastName";
        
        /**
         * Search by position mapping.
         */
        String SEARCH_BY_POSITION_MAPPING = "/user/searchByPosition";

        /**
         * User model attribute name.
         */
        String USERS_MODEL_ATTR = "users";

        /**
         * User model attribute name.
         */
        String USER_MODEL_ATTR = "user";

        /**
         * User for update model attribute name.
         */
        String USER_UPDATE_ATTR = "userFormUpdate";

        /**
         * User for update position model attribute name.
         */
        String USER_UPDATE_POSITION_ATTR = "userFormUpdatePosition";
    
        /**
         * Roles from user model attribute name.
         */
        String USER_ROLE_ATTR = "roles";
        
        /**
         * Search model attribute name.
         */
        String SEARCH_MODEL_ATTR = "search";
        
        /**
         * Users list URL name.
         */
        String USERS_PAGE_URL = "users/users";
        
        /**
         * Edit user URL name.
         */
        String EDIT_PAGE_URL = "users/users/edit";
        
        /**
         * Update user URL name.
         */
        String UPDATE_PAGE_URL = "users/users/edit/updateUser";
        
        /**
         * Update position user URL name.
         */
        String UPDATE_POSITION_PAGE_URL = "users/users/edit/updateUserPosition";
        
        /**
         * Change role user URL name.
         */
        String CHANGE_ROLE_PAGE_URL = "users/users/edit/changeRole";
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

}
