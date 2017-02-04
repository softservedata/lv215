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
     * Application root URL
     */
    String ROOT_URL = "/";

    /**
     * Index page URL
     */
    String INDEX_URL = "index";

    /**
     * Error handling page URL
     */
    String ERROR_PAGE_URL = "error";

    /**
     * Exception model attribute name.
     */
    String EXCEPTION_MODEL_ATTR = "exception";

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
         * Filter model attribute name.
         */
        String ROOM_PAGINATOR_MODEL_ATTR = "roomPaginator";

        /**
         * Date filter model attribute name.
         */
        String DATE_FILTER_MODEL_ATTR = "dateFilter";

        /**
         * Path variable for room id name.
         */
        String PATH_VAR_ID = "id";

        /**
         * Rooms section URL name.
         */
        String ROOMS_URL = "/rooms";

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

    public interface RegistrationControllerConst {

        String USER_REGIST_MAPPING_FROM_STARTPAGE = "registration";

        String USER_REGIST_MAPPING_FOR_ADMIN = "users/registration";

        String USER_REGIST_MODEL_ATTR = "userFormCreate";

        String USER_REGIST_URL = "users/registration";

        String REDIRECT_STARTPAGE = "redirect:/";

        String REDIRECT_USERS_PAGE = "redirect:/users";
    }

    /**
     * An interface to storage User controller model attributes and mappings
     * URL.
     *
     * @version 1.0 20 January 2017
     *
     * @author Serhiy Dudynsky
     *
     * @since 1.8
     */
    public interface UserControllerConst {

        String USERS_MAPPING_FROM_HEADER = "/users";

        String DELETE_USER_MAPPING = "/users/delete/";

        String UPDATE_USER_MAPPING = "update";

        String SAVE_UPDATED_USER_MAPPING = "update/saveUpdatedUser/";

        String BAN_USER_MAPPING = "/users/banUser/";

        String UNBAN_USER_MAPPING = "/users/unBanUser/";

        String CHANGE_ROLE_MAPPING = "/changeRole/";

        String SAVE_CHANGED_ROLE_MAPPING = "/users/changeRole/saveChangedRole/";

        String SORT_BY_LASTNAME_ASC_MAPPING = "/users/sortbylastnameasc";

        String SORT_BY_LASTNAME_DESC_MAPPING = "/users/sortbylastnamedesc";

        String SORT_BY_POSITION_ASC_MAPPING = "/users/sortbypositionasc";

        String SORT_BY_POSITION_DESC_MAPPING = "/users/sortbypositiondesc";

        String SEARCH_BY_LASTNANE_MAPPING = "/user/searchByLastName";

        String SEARCH_BY_POSITION_MAPPING = "/user/searchByPosition";

        String USERS_MODEL_ATTR = "users";

        String USER_MODEL_ATTR = "user";

        String USER_UPDATE_ATTR = "userFormUpdate";

        String USER_UPDATE_POSITION_ATTR = "userFormUpdatePosition";

        String USER_ROLE_ATTR = "roles";

        String SEARCH_MODEL_ATTR = "search";

        String USERS_PAGE_URL = "users/users";

        String UPDATE_PAGE_URL = "users/users/updateUser";

        String CHANGE_ROLE_PAGE_URL = "users/users/changeRole";

        String DELETE_USER_URL = "users/users/delete";

        String USER_PROFILE_MAPPING = "profile";

        String USER_PROFILE_URL = "users/users/profile";

        String CHANGE_PASSWORD_MAPPING = "changePassword";

        String SAVE_CHANGED_PASSWORD_MAPPING = "changePassword/saveChangePassword";

        String CHANGE_PASSWORD_URL = "users/users/changePassword";
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
		 * Filter model attribute name.
		 */
		String FILTER_MODEL_ATTR = "locationFilter";

		/**
		 * Paginator model attribute name.
		 */
		String LOCATION_PAGINATOR_MODEL_ATTR = "locationPaginator";

		/**
		 * Locations model attribute name.
		 */
		String LOCATIONS_MODEL_ATTR = "locations";

		/**
		 * Location form model attribute name.
		 */
		String LOCATION_FORM_MODEL_ATTR = "locationForm";

		/**
		 * Location map model attribute name.
		 */
		String LOCATION_MAP_MODEL_ATTR = "map";
		
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
		 * Map location URL.
		 */
		String LOCATION_MAP_URL = "locations/map";
		
		/**
		 * Locations mapping URL.
		 */
		String LOCATIONS_MAPPING = "/locations";

		/**
		 * Locations sort by count rooms (asc) mapping URL.
		 */
		String LOCATIONS_SORT_BY_COUNT_ROOM_ASC_MAPPING = "/locations/sortbycountroomsasc";

		/**
		 * Locations sort by count rooms (desc) mapping URL.
		 */
		String LOCATIONS_SORT_BY_COUNT_ROOM_DESC_MAPPING = "/locations/sortbycountroomsdesc";

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

		/**
		 * Map location mapping URL name.
		 */
		String LOCATION_MAP_MAPPING = "/locations/map/";
	}

    public interface SubjectControllerConst {

        /**
         * Subject form model attribute name.
         */
        String SUBJECT_FORM_MODEL_ATTR = "subjectForm";

        /**
         * Filter model attribute name.
         */
        String FILTER_MODEL_ATTR = "subjectFilter";

        /**
         * Filter model attribute name.
         */
        String SUBJECT_PAGINATOR_MODEL_ATTR = "subjectPaginator";

        /**
         * Subjects model attribute name.
         */
        String SUBJECTS_MODEL_ATTR = "subjects";

        /**
         * Users model attribute name.
         */
        String USERS_MODEL_ATTR = "users";

        /**
         * Subjects list URL.
         */
        String SUBJECTS_LIST_URL = "subjects/list";

        /**
         * Subjects list redirect URL.
         */
        String SUBJECTS_REDIRECT_URL = "redirect:/subjects";

        /**
         * Create new Subject URL.
         */
        String SUBJECT_CREATE_URL = "subjects/create";

        /**
         * Edit location information URL.
         */
        String SUBJECTS_EDIT_URL = "subjects/edit";

        /**
         * Subjects mapping URL.
         */
        String SUBJECTS_MAPPING = "/subjects";

        /**
         * Delete subject mapping URL name.
         */
        String SUBJECT_DELETE_MAPPING = "/subjects/delete/";

        /**
         * Create new subject mapping URL name.
         */
        String SUBJECT_CREATE_MAPPING = "/subjects/create";

        /**
         * Edit subject information mapping URL name.
         */
        String SUBJECT_EDIT_MAPPING = "/subjects/edit/";

        /**
         * Subject field id.
         */
        String SUBJECT_PATH_ID = "id";

        /**
         * Subject field name.
         */
        String SUBJECT_PATH_NAME = "name";

        /**
         * Subject field description.
         */
        String SUBJECT_PATH_DESCRIPTION = "description";

        /**
         * Subject field lastName.
         */
        String SUBJECT_PATH_LASTNAME = "lastName";

        /**
         * Subject field users.
         */
        String SUBJECT_PATH_USERS = "users";
        
        /**
         * Subject field users.
         */
        String SUBJECT_PATH_USER_ID = "userId";
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

        /**
         * Mapping for searching.
         */
        String SEARCH_MODEL_ATTR = "search";

        /**
         * Groups searching by name mapping.
         */
        String USERGROUPS_SEARCH_BY_NAME_MAPPING = "/usergroups/searchByName";

        /**
         * Groups searching by curator mapping.
         */
        String USERGROUPS_SEARCH_BY_CURATOR_MAPPING = "/usergroups/searchByCurator";

        /**
         * Curators attribute.
         */
        String USERGROUP_CURATORS_ATTR = "curators";

        /**
         * All users attribute.
         */
        String USERGROUP_ALL_USERS_ATTR = "allUsers";

        /**
         * Levels attribute.
         */
        String USERGROUP_LEVEL_ATTR = "levels";

        /**
         * Mapping on a usergroups page
         */
        String USERGROUP_MAPPING = "/usergroups";

        /**
         * Path name
         */
        String USERGROUP_PATH_NAME = "name";

        /**
         * Path curator's lastname
         */
        String USERGROUP_PATH_CURATOR_LASTNAME = "curator.lastName";
    }

    public interface RoomEquipmentControllerConst {

        String ROOM_EQUIPMENTS_URL = "/roomequipments";

        String ROOM_EQUIPMENTS_MAPPING = "/roomequipments";

        String ROOM_EQUIPMENTS_CREATE = "/create";

        String ROOM_EQUIPMENTS_LIST = "roomequipments/list";

        String ROOM_EQUIPMENTS_CREATE_URL = "roomequipments/create";

        String ROOM_EQUIPMENTS_REDIRECT = "redirect:/roomequipments";

        String ROOM_EQUIPMENTS_EDIT_URL = "roomequipments/edit";

        String ROOM_EQUIPMENTS_EDIT = "/edit/";

        String ROOM_EQUIPMENTS_MODEL_ATTR = "equipments";

        String ROOM_EQUIPMENTS_DELETE = "/delete/";

        String ROOM_EQUIPMENT_FORM = "equipmentForm";

        String ROOM_EQUIPMENTS_SORT_ASC = "/sortbynameasc";

        String ROOM_EQUIPMENTS_SORT_DESC = "/sortbynamedesc";
    }
}