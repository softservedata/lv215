package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.softserve.edu.schedule.service.MeetingService;

@Service
public class MeetingDTOEditor extends PropertyEditorSupport {

    @Autowired
    private MeetingService meetingService;
   
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(meetingService.getById(Long.valueOf(text)));
    }

}
