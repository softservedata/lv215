package com.softserve.edu.schedule.service.implementation.dtoconverter;


import org.springframework.stereotype.Service;
import com.softserve.edu.schedule.dto.UserGroupForMeetingDTO;
import com.softserve.edu.schedule.entity.UserGroup;

@Service
public class UserGroupForMeetingDTOConverter {

    public UserGroupForMeetingDTO getDTO(final UserGroup userGroup) {
        if (userGroup != null) {
            UserGroupForMeetingDTO userGroupForMeetingDTO = new UserGroupForMeetingDTO();
            if (userGroup.getId() != null) {
                userGroupForMeetingDTO.setId(userGroup.getId());
            }
            if (userGroup.getName() != null) {
                userGroupForMeetingDTO.setName(userGroup.getName());
            }

            return userGroupForMeetingDTO;
        }
        return null;
    }
}
