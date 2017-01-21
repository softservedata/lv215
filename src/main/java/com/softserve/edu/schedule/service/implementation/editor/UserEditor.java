package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dao.UserDAO;

@Service
public class UserEditor extends PropertyEditorSupport {

    @Autowired
    private UserDAO userDAO;

    

    @Override
    public void setAsText(String userId) throws IllegalArgumentException {
        setValue(userDAO.getById(Long.valueOf(userId)));
        
    }
}
