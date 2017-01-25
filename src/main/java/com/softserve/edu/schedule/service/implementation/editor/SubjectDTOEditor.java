package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.service.SubjectService;

@Service
public class SubjectDTOEditor extends PropertyEditorSupport {

    @Autowired
    private SubjectService subjectService;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(subjectService.getById(Long.valueOf(text)));
    }

}
