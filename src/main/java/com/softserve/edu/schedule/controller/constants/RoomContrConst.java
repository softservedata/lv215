/* RoomContrConst 1.0 01/20/2017 */
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
public final class RoomContrConst {

    /**
     * Room edit permissions.
     */
    public static final String ROOM_EDIT_PERMISSIONS = "hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')";

    /**
     * Room model attribute name.
     */
    public static final String ROOM_MODEL_ATTR = "room";

    /**
     * Rooms model attribute name.
     */
    public static final String ROOMS_MODEL_ATTR = "rooms";

    /**
     * Locations model attribute name.
     */
    public static final String LOCATIONS_MODEL_ATTR = "locations";

    /**
     * Room equipments model attribute name.
     */
    public static final String EQUIPMENTS_MODEL_ATTR = "equipments";

    /**
     * Room meetings model attribute name.
     */
    public static final String MEETINGS_MODEL_ATTR = "meetings";

    /**
     * Filter model attribute name.
     */
    public static final String FILTER_MODEL_ATTR = "roomFilter";

    /**
     * Paginator model attribute name.
     */
    public static final String ROOM_PAGINATOR_MODEL_ATTR = "roomPaginator";

    /**
     * Room files model attribute name.
     */
    public static final String ROOM_FILES_MODEL_ATTR = "roomFiles";

    /**
     * Path variable for room id name.
     */
    public static final String PATH_VAR_ID = "id";

    /**
     * Rooms section URL name.
     */
    public static final String ROOMS_URL = "/rooms";

    /**
     * Rooms list URL name.
     */
    public static final String ROOMS_LIST_URL = "rooms/list";

    /**
     * Rooms list redirect URL name.
     */
    public static final String ROOMS_REDIRECT_URL = "redirect:/rooms";

    /**
     * Show room details URL name.
     */
    public static final String ROOM_SHOW_URL = "rooms/show";

    /**
     * Show room details mapping URL name.
     */
    public static final String ROOM_SHOW_MAPPING = "/{id}";

    /**
     * Edit room information URL name.
     */
    public static final String ROOM_EDIT_URL = "rooms/edit";

    /**
     * Edit room information mapping URL name.
     */
    public static final String ROOM_EDIT_MAPPING = "/edit/{id}";

    /**
     * Create new room URL name.
     */
    public static final String ROOM_CREATE_URL = "rooms/create";

    /**
     * Create new room mapping URL name.
     */
    public static final String ROOM_CREATE_MAPPING = "/create";

    /**
     * Delete room mapping URL name.
     */
    public static final String ROOM_DELETE_MAPPING = "/delete/{id}";

    /**
     * Room upload file mapping URL name.
     */
    public static final String ROOM_FILE_UPLOAD_MAPPING = "/edit/{id}/uploadfile";

    /**
     * Room delete file mapping URL name.
     */
    public static final String ROOM_FILE_DELETE_MAPPING = "/deleteFile/{fileName}/{id}";

    /**
     * Room download file mapping URL name.
     */
    public static final String ROOM_FILE_DOWNLOAD_MAPPING = "/downloadFile/{fileName}/{roomId}";

    /**
     * Room upload file redirect URL name.
     */
    public static final String ROOM_FILE_REDIRECT_URL = "redirect:/rooms/edit/{id}";

    /**
     * Private constructor to prevent instance creation.
     */
    private RoomContrConst() {
    }

}
