/* MeetingRESTContrConst 1.0 01/20/2017 */
package com.softserve.edu.schedule.controller.constants;

/**
 * An class to storage meeting REST controller model attributes and mappings
 * URL.
 *
 * @version 1.0 20 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public final class MeetingRESTContrConst {

    /**
     * REST request meetings by room id URL mapping.
     */
    public static final String REST_BY_ROOM_URL_MAPPING = "/meetings/restByRoom";

    /**
     * REST request meetings by user id URL mapping.
     */
    public static final String REST_BY_USER_URL_MAPPING = "/meetings/restByUser";

    /**
     * REST request meetings by group id URL mapping.
     */
    public static final String REST_BY_GROUP_URL_MAPPING = "/meetings/restByGroup";

    /**
     * REST request meetings by subject id URL mapping.
     */
    public static final String REST_BY_SUBJECT_URL_MAPPING = "/meetings/restBySubject";

    /**
     * REST request meetings for user charts URL mapping.
     */
    public static final String REST_FOR_CHARTS_URL_MAPPING = "/meetings/restForChart";

    /**
     * Start date request parameter.
     */
    public static final String START_REQUEST_PARAM = "start";

    /**
     * End date request parameter.
     */
    public static final String END_REQUEST_PARAM = "end";

    /**
     * Room id request parameter.
     */
    public static final String ROOM_ID_REQUEST_PARAM = "roomId";

    /**
     * User id request parameter.
     */
    public static final String USER_ID_REQUEST_PARAM = "userId";

    /**
     * Subject id request parameter.
     */
    public static final String SUBJECT_ID_REQUEST_PARAM = "subjectId";

    /**
     * Group id request parameter.
     */
    public static final String GROUP_ID_REQUEST_PARAM = "groupId";

    /**
     * Private constructor to prevent instance creation.
     */
    private MeetingRESTContrConst() {
    }

}
