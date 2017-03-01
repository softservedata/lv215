/* AndroidRESTContrConst 1.0 01/20/2017 */
package com.softserve.edu.schedule.controller.constants;

/**
 * An class to storage android REST controller model attributes and mappings
 * URL.
 *
 * @version 1.0 20 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public final class AndroidRESTContrConst {

    /**
     * Android user verification URL mapping.
     */
    public static final String USER_VERIFY_URL_MAPPING = "/userVerify";

    /**
     * REST request meetings by user id URL mapping.
     */
    public static final String REST_BY_USER_ANDROID_URL_MAPPING = "/meetings/restByUserAndroid";

    /**
     * REST request rooms URL mapping.
     */
    public static final String REST_ROOMS_ANDROID_URL_MAPPING = "/rooms/restRoomsAndroid";

    /**
     * REST request groups URL mapping.
     */
    public static final String REST_GROUPS_ANDROID_URL_MAPPING = "/groups/restGroupsAndroid";

    /**
     * REST request subjects URL mapping.
     */
    public static final String REST_SUBJECTS_ANDROID_URL_MAPPING = "/subjects/restSubjectsAndroid";

    /**
     * REST request locations URL mapping.
     */
    public static final String REST_LOCATIONS_ANDROID_URL_MAPPING = "/locations/restLocationsAndroid";

    /**
     * REST request room equipments URL mapping.
     */
    public static final String REST_EQUIPMENTS_ANDROID_URL_MAPPING = "/roomequipments/restEquipmentsAndroid";

    /**
     * User mail request parameter.
     */
    public static final String USER_MAIL_REQUEST_PARAM = "userMail";

    /**
     * User password request parameter.
     */
    public static final String USER_PASS_REQUEST_PARAM = "userPass";

    /**
     * Start date request parameter.
     */
    public static final String START_REQUEST_PARAM = "start";

    /**
     * End date request parameter.
     */
    public static final String END_REQUEST_PARAM = "end";

    /**
     * User id request parameter.
     */
    public static final String USER_ID_REQUEST_PARAM = "userId";

    /**
     * Private constructor to prevent instance creation.
     */
    private AndroidRESTContrConst() {
    }

}
