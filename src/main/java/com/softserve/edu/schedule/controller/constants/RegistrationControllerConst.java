package com.softserve.edu.schedule.controller.constants;

/**
 * An class to storage registration controller model attributes and mappings URL.
 *
 * @version 1.0 20 January 2017
 *
 * @author Serhiy Dudynsky
 *
 * @since 1.8
 */
public final class RegistrationControllerConst {

    public static final String USER_REGIST_MAPPING_FROM_STARTPAGE = "registration";

    public static final String USER_REGIST_MAPPING_FOR_ADMIN = "users/registration";

    public static final String USER_REGIST_MODEL_ATTR = "userFormCreate";

    public static final String USER_REGIST_URL = "users/registration";

    public static final String REDIRECT_STARTPAGE = "redirect:/";

    public static final String REDIRECT_USERS_PAGE = "redirect:/users";
    
    public static final String HAS_ROLE_ADMIN = "hasAnyRole('ROLE_ADMIN')";
    
    private RegistrationControllerConst(){
        
    }
}
