package com.softserve.edu.schedule.controller;

public interface ControllerConst {

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

}
