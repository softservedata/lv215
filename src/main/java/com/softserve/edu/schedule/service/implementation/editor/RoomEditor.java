package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.service.RoomService;

// delete after meeting DTO

@Service
public class RoomEditor extends PropertyEditorSupport {

    @Autowired
    private RoomService roomService;

    @Override
    public void setAsText(final String roomId) throws IllegalArgumentException {
        setValue(roomService.getEntityById(Long.valueOf(roomId)));
    }
}
