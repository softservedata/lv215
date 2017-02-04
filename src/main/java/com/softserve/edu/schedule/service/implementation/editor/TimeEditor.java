/* TimeEditor 1.0 01/17/2017 */
package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Service;

/**
 * A class to provide conversion operations from form field time to LocalTime
 * type.
 *
 * @version 1.0 17 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Service
public class TimeEditor extends PropertyEditorSupport {

    /**
     * Provides a LocalTime value by given time in String format.
     *
     * @param text
     *            time id in String format
     *
     * @throws IllegalArgumentException
     *             if text can not be converted to time
     */
    @Override
    public void setAsText(final String text) throws IllegalArgumentException {
        if (text != null && !text.isEmpty()) {
            try {
                setValue(LocalTime.parse(text));
            } catch (DateTimeParseException e) {
                setValue(null);
            }
        } else {
            setValue(null);
        }
    }
}
