/* MeetingDTO editor class*/
package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.softserve.edu.schedule.service.MeetingService;

/**
 * A class to provide conversion operations from form field text id to the Long
 * meeting id.
 *
 * @version 1.0 17 January 2017
 *
 * @author Bohdan Melnyk
 *
 * @since 1.8
 */
@Service
public class MeetingDTOEditor extends PropertyEditorSupport {

    /**
     * MeetingService example to provide search DTO operations.
     */
    @Autowired
    private MeetingService meetingService;

    /*
     * (non-Javadoc)
     * 
     * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(meetingService.getById(Long.valueOf(text)));
    }

}
