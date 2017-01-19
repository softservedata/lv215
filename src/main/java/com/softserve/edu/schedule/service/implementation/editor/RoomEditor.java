package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import com.softserve.edu.schedule.service.RoomService;

// delete after meeting DTO
public class RoomEditor extends PropertyEditorSupport {

    private RoomService roomService;
    
    public RoomEditor(final RoomService roomService) {
        this.roomService = roomService;
    }
    
    @Override
    public void setAsText(final String roomId) throws IllegalArgumentException {
        setValue(roomService.getEntityById(Long.valueOf(roomId)));
    }
}
