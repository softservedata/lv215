
/* UserGroupDTOEditor 1.0 01/17/2017 */
package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;
import org.springframework.stereotype.Service;

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
public class UserForMeetingDTOEditor extends PropertyEditorSupport {



    /**
     * Provides a UserForMeetingDTO example by given location id in String format.
     * 
     * @param locationId
     *            a location id in String format
     * 
     * @throws IllegalArgumentException
     *             if @param locationId is not String.
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
/*        User user = userService.getById(Long.valueOf(text));
        setValue(userGroupForMeetingDTOConverter.getDTO(userGroup));*/
    }
}
