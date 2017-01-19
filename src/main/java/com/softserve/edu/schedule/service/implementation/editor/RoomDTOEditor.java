/* RoomDTOEditor 1.0 01/17/2017 */
package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import com.softserve.edu.schedule.service.RoomService;

/**
 * A class to provide conversion operations from form field roomId to RoomDTO.
 *
 * @version 1.0 17 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public class RoomDTOEditor extends PropertyEditorSupport {

    /**
     * RoomService example to provide search DTO operations.
     */
    private RoomService roomService;

    /**
     * Constructor of RoomDTOEditor.
     * 
     * @param roomService
     *            RoomService example
     */
    public RoomDTOEditor(final RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Provides a RoomDTO example by given room id in String format.
     * 
     * @param roomId
     *            a room id in String format
     * 
     * @throws IllegalArgumentException
     *             if @param roomId is not String.
     */
    @Override
    public void setAsText(final String roomId) throws IllegalArgumentException {
        setValue(roomService.getById(Long.valueOf(roomId)));
    }
}
