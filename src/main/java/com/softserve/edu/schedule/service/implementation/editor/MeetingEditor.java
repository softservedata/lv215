package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;

import com.softserve.edu.schedule.service.MeetingService;

public class MeetingEditor extends PropertyEditorSupport {

    private MeetingService meetingService;

    public MeetingEditor(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(meetingService.getById(Long.valueOf(text)));
    }

}
