package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import com.softserve.edu.schedule.service.RoomService;

public class RoomDTOEditor extends PropertyEditorSupport {

    private RoomService roomService;

    public RoomDTOEditor(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(roomService.getById(Long.valueOf(text)));
    }
}
