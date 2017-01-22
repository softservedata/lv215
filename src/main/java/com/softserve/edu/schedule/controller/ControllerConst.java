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

    public interface UserControllerConst {

        /**
         * Users mapping.
         */
        String USERS_MAPPING_FROM_HEADER = "/users";

        /**
         * Delete user mapping.
         */
        String DELETE_USER_MAPPING = "/users/delete/{id}";

        /**
         * Edit user mapping.
         */
        String EDIT_USER_MAPPING = "/users/edit/{id}";

        /**
         * Update user mapping.
         */
        String UPDATE_USER_MAPPING = "/users/edit/updateUser/{id}";

        /**
         * Save updated user mapping.
         */
        String SAVE_UPDATED_USER_MAPPING = "/users/edit/updateUser/saveUpdatedUser/{id}";

        /**
         * Update user position mapping.
         */
        String UPDATE_POSITION_MAPPING = "/users/edit/updateUserPosition/{id}";

        /**
         * Save updated position user mapping.
         */
        String SAVE_UPDATED_POSITION_MAPPING = "/users/edit/updateUserPosition/saveUpdatedUserPosition/{id}";

        /**
         * Ban user mapping.
         */
        String BAN_USER_MAPPING = "/users/banUser/{id}";

        /**
         * Unban user mapping.
         */
        String UNBAN_USER_MAPPING = "/users/unBanUser/{id}";

        /**
         * Change user role mapping.
         */
        String CHANGE_ROLE_MAPPING = "/users/edit/changeRole/{id}";

        /**
         * Save changed user role mapping.
         */
        String SAVE_CHANGED_ROLE_MAPPING = "/users/edit/changeRole/saveChangedRole/{id}";

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

    public interface SubjectControllerConst {

        /**
         * Search model attribute name.
         */
        String SEARCH_MODEL_ATTR = "search";
        
        /**
         * Search model attribute tutor.
         */
        String SEARCH_BY_TUTOR_MODEL_ATTR = "searchTutor";
        
        /**
         * Subject form model attribute name.
         */
        String SUBJECT_FORM_MODEL_ATTR = "subjectForm";
        
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
         *Subjects list redirect URL.
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
         * Subjects sort by name (asc) mapping URL.
         */
        String SUBJECTS_SORT_BY_NAME_ASC_MAPPING = "/subjects/sortbynameasc";

        /**
         * Subjects sort by name (desc) mapping URL.
         */
        String SUBJECTS_SORT_BY_NAME_DESC_MAPPING = "/subjects/sortbynamedesc";
        
        /**
         * Subjects sort by description (asc) mapping URL.
         */
        String SUBJECTS_SORT_BY_DESCRIPTION_ASC_MAPPING = "/subjects/sortbydescriptionasc";

        /**
         * Subjects sort by description (desc) mapping URL.
         */
        String SUBJECTS_SORT_BY_DESCRIPTION_DESC_MAPPING = "/subjects/sortbydescriptiondesc";
        
        /**
         * Subjects search by name mapping URL.
         */
        String SUBJECTS_SEARCH_BY_NAME_MAPPING = "/subjects/searchByName";
        
        /**
         * Subjects search by description mapping URL.
         */
        String SUBJECTS_SEARCH_BY_DESCRIPTION_MAPPING = "/subjects/searchByDescription";
        
        /**
         * Subjects search by description mapping URL.
         */
        String SUBJECTS_SEARCH_BY_TUTOR_MAPPING = "/subjects/searchByTutor";
        
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
         *Subject field id.
         */
        String SUBJECT_PATH_ID = "id";
        
        /**
         *Subject field name.
         */
        String SUBJECT_PATH_NAME = "name";
        
        /**
         *Subject field description.
         */
        String SUBJECT_PATH_DESCRIPTION = "description";
        
        /**
         *Subject field description.
         */
        String SUBJECT_PATH_LASTNAME = "lastName";
        
        String SUBJECT_PATH_USERS = "users";
    }
}
