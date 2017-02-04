/* Time editor class*/
package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

/**
 * A class to provide conversion operations from form field text time to the
 * time.
 *
 * @version 1.0 17 January 2017
 *
 * @author Bohdan Melnyk
 *
 * @since 1.8
 */
@Service
public class TimeEditor extends PropertyEditorSupport {

    /*
     * (non-Javadoc)
     * 
     * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text != null && !text.isEmpty())
            setValue(LocalTime.parse(text));
        else
            setValue(null);
    }
}
