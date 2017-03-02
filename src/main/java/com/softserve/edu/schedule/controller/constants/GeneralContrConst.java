/* GeneralContrConst 1.0 01/20/2017 */
package com.softserve.edu.schedule.controller.constants;

/**
 * An class to storage room controller model attributes and mappings URL.
 *
 * @version 1.0 20 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public final class GeneralContrConst {

    /**
     * Application root URL.
     */
    public static final String ROOT_URL = "/";

    /**
     * Index page URL.
     */
    public static final String INDEX_URL = "index";

    /**
     * Error handling page URL.
     */
    public static final String ERROR_PAGE_URL = "error";

    /**
     * Login page URL mapping.
     */
    public static final String LOGIN_PAGE_URL_MAPPING = "/login";

    /**
     * Login page URL.
     */
    public static final String LOGIN_PAGE_URL = "login";

    /**
     * Default sign in page URL mapping.
     */
    public static final String SIGNIN_PAGE_URL_MAPPING = "/signin";

    /**
     * Default sign up page URL mapping.
     */
    public static final String SIGNUP_PAGE_URL_MAPPING = "/signup";

    /**
     * Exception model attribute name.
     */
    public static final String EXCEPTION_MODEL_ATTR = "exception";

    /**
     * Login failed page URL.
     */
    public static final String LOGIN_FAILED_REDIRECT_URL = "redirect:/login?accessDenied=true";

    /**
     * Login social failed page URL.
     */
    public static final String LOGIN_SOCIAL_FAILED_REDIRECT_URL = "redirect:/login?fail=true";

    /**
     * Registration page redirect URL.
     */
    public static final String REGISTRATION_REDIRECT_URL = "redirect:/registration";

    /**
     * Calendar show permissions.
     */
    public static final String CALENDAR_SHOW_PERMISSIONS = "isAuthenticated()";

    /**
     * Calendar URL mapping.
     */
    public static final String CALENDAR_URL_MAPPING = "/calendar";

    /**
     * Calendar page URL.
     */
    public static final String CALENDAR_URL = "calendar";

    /**
     * Location model attribute for index page.
     */
    public static final String LOCATION_MODEL_ATTR = "locationI";

    /**
     * Subject model attribute for index page.
     */
    public static final String SUBJECT_MODEL_ATTR = "subjectI";

    /**
     * User group model attribute for index page.
     */
    public static final String GROUP_MODEL_ATTR = "groupI";

    /**
     * Private constructor to prevent instance creation.
     */
    private GeneralContrConst() {
    }

}
