/*
 * SubjectDTOEditor.java
 * 1.0
 * 23 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.service.SubjectService;

/**
 * A class to provide conversion operations from form field roomId to RoomDTO.
 *
 * @version 1.0 17 January 2017
 *
 * @author Ped'ko Volodymyr
 *
 * @since 1.8
 */
@Service
public class SubjectDTOEditor extends PropertyEditorSupport {

    /**
     * RoomService example to provide search DTO operations.
     */
    @Autowired
    private SubjectService subjectService;

    /**
     * Provides a SubjectDTO example by given room id in String format.
     * 
     * @param text
     *            a room id in String format
     * 
     * @throws IllegalArgumentException
     *             if @param text is not String.
     */
    @Override
    public void setAsText(final String text) throws IllegalArgumentException {
        setValue(subjectService.getById(Long.valueOf(text)));
    }

}
