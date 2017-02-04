/* RoomDTOEditor 1.0 01/17/2017 */
package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class RoomDTOEditor extends PropertyEditorSupport {

    /**
     * RoomService example to provide search DTO operations.
     */
    @Autowired
    private RoomService roomService;

    /**
     * Provides a RoomDTO example by given room id in String format.
     *
     * @param roomId
     *            a room id in String format
     *
     * @throws IllegalArgumentException
     *             if roomId is not String.
     */
    @Override
    public void setAsText(final String roomId) throws IllegalArgumentException {
        setValue(roomService.getById(Long.valueOf(roomId)));
    }
}
