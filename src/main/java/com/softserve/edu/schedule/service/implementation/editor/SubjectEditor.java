package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import com.softserve.edu.schedule.service.SubjectService;

public class SubjectEditor extends PropertyEditorSupport {

    private SubjectService subjectService;

    public SubjectEditor(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(subjectService.getById(Long.valueOf(text)));
    }
}
