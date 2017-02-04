/* Date editor class*/
package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Service;

/**
 * A class to provide conversion operations from form field text date to the
 * date.
 *
 * @version 1.0 17 January 2017
 *
 * @author Bohdan Melnyk
 *
 * @since 1.8
 */
@Service
public class DateEditor extends PropertyEditorSupport {

    /*
     * (non-Javadoc)
     * 
     * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text != null && !text.isEmpty()) {
            try {
                setValue(LocalDate.parse(text));
            } catch (DateTimeParseException e) {
                setValue(null);
            }
        } else
            setValue(null);
    }
}
