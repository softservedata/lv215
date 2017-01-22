package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

@Service
public class DateTimeEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
     if (text != null && !text.isEmpty())
         setValue(LocalDateTime.parse(text, DateTimeFormatter.ISO_DATE_TIME));
     else
         setValue(null);
    }
 }
