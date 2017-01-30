package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dao.SubjectDAO;

@Service
public class SubjectEditor extends PropertyEditorSupport {

    @Autowired
    private SubjectDAO subjectDAO;

    @Override
    public void setAsText(String subjectId) throws IllegalArgumentException {
        setValue(subjectDAO.getById(Long.valueOf(subjectId)));
    }
}
