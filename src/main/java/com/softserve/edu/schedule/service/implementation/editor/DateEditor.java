package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class DateEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
     if (text != null && !text.isEmpty())
         setValue(LocalDate.parse(text));
     else
         setValue(null);
    }
 }
