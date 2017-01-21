package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;

public class DateTimeEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
     if (text != null && !text.isEmpty())
         setValue(LocalDateTime.parse(text));
     else
         setValue(null);
    }
 }
