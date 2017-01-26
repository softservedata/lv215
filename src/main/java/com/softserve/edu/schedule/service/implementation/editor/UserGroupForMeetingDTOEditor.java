
/* UserGroupDTOEditor 1.0 01/17/2017 */
package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dto.UserGroupDTO;

import com.softserve.edu.schedule.service.UserGroupService;

/**
 * A class to provide conversion operations from form field locationId to
 * LocationDTO.
 *
 * @version 1.0 17 January 2017
 *
 *
 * @since 1.8
 */
@Service
public class UserGroupForMeetingDTOEditor extends PropertyEditorSupport {

    /**
     * LocationService example to provide search DTO operations.
     */
    @Autowired
    private UserGroupService userGroupService;


    /**
     * Provides a LocationDTO example by given location id in String format.
     * 
     * @param locationId
     *            a location id in String format
     * 
     * @throws IllegalArgumentException
     *             if @param locationId is not String.
     */
    @Override

    public void setAsText(String text) throws IllegalArgumentException {
        UserGroupDTO userGroupDTO = userGroupService.getById(Long.valueOf(text));
        setValue(userGroupDTO);
    }
}
