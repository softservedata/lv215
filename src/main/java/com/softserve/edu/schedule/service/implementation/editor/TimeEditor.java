package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

@Service
public class TimeEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
     if (text != null && !text.isEmpty())
         setValue(LocalTime.parse(text));
     else
         setValue(null);
    }
 }
