/* Meeting status editor class*/
package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.service.MeetingService;

/**
 * A class to provide conversion operations from form field text status to the
 * enum status.
 *
 * @version 1.0 17 January 2017
 *
 * @author Bohdan Melnyk
 *
 * @since 1.8
 */
@Service
public class MeetingStatusEditor extends PropertyEditorSupport {

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
    public void setAsText(final String meetingStatus)
            throws IllegalArgumentException {
        setValue(meetingService.getStatusbyString(meetingStatus));
    }
}