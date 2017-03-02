/* MeetingControllerConst 1.0 01/03/2017 */
package com.softserve.edu.schedule.controller.constants;

/**
 * A class to storage meeting controller model attributes and mappings URL.
 *
 * @version 1.0 1 March 2017
 *
 * @author Bohdan Melnyk
 */
public final class MeetingControllerConst {

    /**
     * Meetings section URL name.
     */
    public static final String MEETINGS_URL = "/meetings";

    /**
     * Filter attribute for meetings.
     */
    public static final String FILTER_MODEL_ATTR = "meetingFilter";

    /**
     * Meeting model attribute name.
     */
    public static final String MEETING_MODEL_ATTR = "meetingForm";

    /**
     * Paginator model attribute name.
     */
    public static final String MEETING_PAGINATOR_MODEL_ATTR = "meetingPaginator";

    /**
     * Meetings model attribute name.
     */
    public static final String MEETINGS_MODEL_ATTR = "meetings";

    /**
     * Users model attribute name.
     */
    public static final String USERS_MODEL_ATTR = "users";

    /**
     * Owners model attribute name.
     */
    public static final String OWNERS_MODEL_ATTR = "owners";

    /**
     * Subjects model attribute name.
     */
    public static final String SUBJECTS_MODEL_ATTR = "subjects";

    /**
     * Rooms model attribute name.
     */
    public static final String ROOMS_MODEL_ATTR = "rooms";

    /**
     * Groups model attribute name.
     */
    public static final String GROUPS_MODEL_ATTR = "groups";

    /**
     * UserGroups model attribute name.
     */
    public static final String USERGROUPS_MODEL_ATTR = "userGroups";

    /**
     * MeetingStatuses model attribute name.
     */
    public static final String MEETINGSTATUSES_MODEL_ATTR = "meetingStatuses";

    /**
     * Meetings create mapping name.
     */
    public static final String MEETING_CREATE_MAPPING = "/create";

    /**
     * Meeting delete mapping name.
     */
    public static final String MEETING_DELETE_MAPPING = "/delete/{id}";

    /**
     * Meeting create url name.
     */
    public static final String MEETING_CREATE_URL = "meetings/create";

    /**
     * Meeting edit url name.
     */
    public static final String MEETING_EDIT_URL = "meetings/edit";

    /**
     * Meeting edit mapping name.
     */
    public static final String MEETING_EDIT_MAPPING = "/edit/{id}";

    /**
     * Meeting redirect url name.
     */
    public static final String MEETING_REDIRECT_URL = "redirect:/meetings";

    /**
     * Meeting list url name.
     */
    public static final String MEETING_LIST_URL = "meetings/list";

    /**
     * Meeting list url name.
     */
    public static final String MEETING_SUBJECT_URL = "subjects/show";

    /**
     * Id path.
     */
    public static final String ID_URL = "id";

    /**
     * Meetings Id path.
     */
    public static final String MEETINGID_URL = "/{id}";

    /**
     * Meeting showmeeting mapping name.
     */
    public static final String MEETING_SHOWMEETING_MAPPING = "meetings/showmeeting";
    /**
     * User profile mapping name.
     */
    public static final String PROFILE_MAPPING = "profile";

    /**
     * usergroups model attribute name.
     */
    public static final String USERGROUPS_MAPPING = "/usergroups";

    /**
     * meeting delete url name.
     */
    public static final String DELETE_MAPPING = "meetings/delete";

    /**
     * meetingHistory download url name.
     */
    public static final String DOWNLOAD_MAPPING = "/downloadExcel";

}
