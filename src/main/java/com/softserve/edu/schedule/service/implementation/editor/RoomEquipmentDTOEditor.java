/* RoomEquipmentDTOEditor 1.0 01/17/2017 */
package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class RoomEquipmentDTOEditor extends PropertyEditorSupport {

    /**
     * RoomEquipmentService example to provide search DTO operations.
     */
    @Autowired
    private RoomEquipmentService roomEquipmentService;

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
        setValue(roomEquipmentService.getById(Long.valueOf(roomEquipmentId)));
    }
}
