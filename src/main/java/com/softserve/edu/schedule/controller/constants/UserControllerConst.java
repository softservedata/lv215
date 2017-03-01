package com.softserve.edu.schedule.controller.constants;

/**
 * An class to storage User controller model attributes and mappings URL.
 *
 * @version 1.0 20 January 2017
 *
 * @author Serhiy Dudynsky
 *
 * @since 1.8
 */
public final class UserControllerConst {

    public static final String USERS_MAPPING_FROM_HEADER = "/users";

    public static final String DELETE_USER_MAPPING = "/users/delete/";

    public static final String UPDATE_USER_MAPPING = "update/";

    public static final String SAVE_UPDATED_USER_MAPPING = "update/saveUpdatedUser/";

    public static final String BAN_USER_MAPPING = "/users/banUser/";

    public static final String UNBAN_USER_MAPPING = "/users/unBanUser/";

    public static final String CHANGE_ROLE_MAPPING = "/changeRole/";

    public static final String SAVE_CHANGED_ROLE_MAPPING = "/users/changeRole/saveChangedRole/";

    public static final String SORT_BY_LASTNAME_ASC_MAPPING = "/users/sortbylastnameasc";

    public static final String SORT_BY_LASTNAME_DESC_MAPPING = "/users/sortbylastnamedesc";

    public static final String SORT_BY_POSITION_ASC_MAPPING = "/users/sortbypositionasc";

    public static final String SORT_BY_POSITION_DESC_MAPPING = "/users/sortbypositiondesc";

    public static final String SEARCH_BY_LASTNANE_MAPPING = "/user/searchByLastName";

    public static final String SEARCH_BY_POSITION_MAPPING = "/user/searchByPosition";

    public static final String USERS_MODEL_ATTR = "users";

    public static final String USER_MODEL_ATTR = "user";

    public static final String USER_UPDATE_ATTR = "userFormUpdate";

    public static final String USER_UPDATE_POSITION_ATTR = "userFormUpdatePosition";

    public static final String USER_ROLE_ATTR = "roles";

    public static final String SEARCH_MODEL_ATTR = "search";

    public static final String USERS_PAGE_URL = "users/users";

    public static final String UPDATE_PAGE_URL = "users/users/updateUser";

    public static final String CHANGE_ROLE_PAGE_URL = "users/users/changeRole";

    public static final String DELETE_USER_URL = "users/users/delete";

    public static final String USER_PROFILE_MAPPING = "profile/";

    public static final String USER_PROFILE_URL = "users/users/profile";

    public static final String CHANGE_PASSWORD_MAPPING = "changePassword/";

    public static final String SAVE_CHANGED_PASSWORD_MAPPING = "changePassword/saveChangePassword/";

    public static final String CHANGE_PASSWORD_URL = "users/users/changePassword";

    public static final String FILTER_MODEL_ATTR = "userFilter";

    public static final String USER_PAGINATOR_MODEL_ATTR = "userPaginator";

    public static final String USER_DETAILS_MAPPING = "/userDetails";

    public static final String USER_DETAILS_URL = "/userDetails";

    public static final String REDIRECT_USER_DETAILS_URL = "redirect:/userDetails";

    public static final String USER_MEETINGS_MAPPING = "showMeetings/";

    public static final String USER_MEETINGS_URL = "/showMeetings";

    public static final String SAVE_IMAGES = "/saveImage";

    public static final String RESTORE_PASSWORD_MAPPING = "restorePassword";

    public static final String MAIL_MODEL_ATTR = "mail";

    public static final String RESTORE_PASSWORD_URL = "/restorePassword";

    public static final String NO_SUCH_USER_URL = "/noSuchUser";

    public static final String PASSWORD_WAS_SENT = "/passwordWasSent";

    public static final String FILE = "file";

    public static final String PATH_VAR_ID = "id";

    public static final String USER_FILES = "userFiles";

    public static final String HAS_ROLE_ADMIN = "hasAnyRole('ROLE_ADMIN')";

    public static final String HAS_ROLE_ADMIN_OR_ROLE_SUPERVISOR = "hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')";

    public static final String HAS_ANY_AUTHORIZED_ROLE = "hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR', 'ROLE_USER')";

    public static final String IS_AUTHENTICATED = "isAuthenticated()";

    private UserControllerConst() {

    }

}
