/* RoomEquipmentDTOEditor 1.0 01/17/2017 */
package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import com.softserve.edu.schedule.dto.RoomEquipmentDTO;
import com.softserve.edu.schedule.entity.RoomEquipment;
import com.softserve.edu.schedule.service.RoomEquipmentService;

/**
 * A class to provide conversion operations from form field roomEquipmentId to
 * RoomEquipmentDTO.
 *
 * @version 1.0 17 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public class RoomEquipmentDTOEditor extends PropertyEditorSupport {

    /**
     * RoomEquipmentService example to provide search DTO operations.
     */
    private RoomEquipmentService roomEquipmentService;

    /**
     * Constructor of RoomEquipmentDTOEditor.
     * 
     * @param roomEquipmentService
     *            RoomEquipmentService example
     */
    public RoomEquipmentDTOEditor(
            final RoomEquipmentService roomEquipmentService) {
        this.roomEquipmentService = roomEquipmentService;
    }

    /**
     * Provides a RoomEquipmentDTO example by given room equipment id in String
     * format.
     * 
     * @param roomEquipmentId
     *            a room equipment id in String format
     * 
     * @throws IllegalArgumentException
     *             if @param roomEquipmentId is not String.
     */
    @Override
    public void setAsText(final String roomEquipmentId)
            throws IllegalArgumentException {
        RoomEquipment roomEquipment = roomEquipmentService
                .getById(Long.valueOf(roomEquipmentId));
        RoomEquipmentDTO roomEquipmentDTO = new RoomEquipmentDTO();
        roomEquipmentDTO.setId(roomEquipment.getId());
        roomEquipmentDTO.setName(roomEquipment.getName());
        setValue(roomEquipmentDTO);
    }
}
