/* RoomEqContrConst 1.0 01/20/2017 */
package com.softserve.edu.schedule.controller.constants;

/**
 * An class to storage room equipment controller model attributes and mappings
 * URL.
 *
 * @version 1.0 20 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public final class RoomEqContrConst {

    /**
     * Room equipment edit permissions.
     */
    public static final String ROOM_EQUIPMENT_EDIT_PERMISSIONS = "hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')";

    /**
     * Room equipments section URL name.
     */
    public static final String ROOM_EQUIPMENTS_URL = "/roomequipments";

    /**
     * Room equipments filter model attribute.
     */
    public static final String FILTER_MODEL_ATTR = "roomEquipmentFilter";

    /**
     * Room equipments paginator model attribute.
     */
    public static final String PAGINATOR_MODEL_ATTR = "roomEquipmentPaginator";

    /**
     * Room equipments model attribute.
     */
    public static final String ROOM_EQUIPMENTS_MODEL_ATTR = "equipments";

    /**
     * Room equipment model attribute.
     */
    public static final String ROOM_EQUIPMENT_MODEL_ATTR = "equipmentForm";

    /**
     * Room equipments list URL.
     */
    public static final String ROOM_EQUIPMENTS_LIST = "roomequipments/list";

    /**
     * Room equipments edit URL mapping.
     */
    public static final String ROOM_EQUIPMENTS_EDIT_MAPPING = "/edit/{id}";

    /**
     * Room equipments edit URL.
     */
    public static final String ROOM_EQUIPMENTS_EDIT_URL = "roomequipments/edit";

    /**
     * Room equipments redirect URL.
     */
    public static final String ROOM_EQUIPMENTS_REDIRECT_URL = "redirect:/roomequipments";

    /**
     * Room equipments create URL mapping.
     */
    public static final String ROOM_EQUIPMENTS_CREATE = "/create";

    /**
     * Room equipments create URL.
     */
    public static final String ROOM_EQUIPMENTS_CREATE_URL = "roomequipments/create";

    /**
     * Room equipments delete URL mapping.
     */
    public static final String ROOM_EQUIPMENTS_DELETE_MAPPING = "/delete/{id}";

    /**
     * Private constructor to prevent instance creation.
     */
    private RoomEqContrConst() {
    }

}
