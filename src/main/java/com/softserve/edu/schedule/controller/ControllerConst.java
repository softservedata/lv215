/* ControllerConst 1.0 01/20/2017 */
package com.softserve.edu.schedule.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.edu.schedule.controller.ControllerConst.RegistrationControllerConst;
import com.softserve.edu.schedule.controller.ControllerConst.UserControllerConst;
import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserRole;
import com.softserve.edu.schedule.entity.UserStatus;

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
}
