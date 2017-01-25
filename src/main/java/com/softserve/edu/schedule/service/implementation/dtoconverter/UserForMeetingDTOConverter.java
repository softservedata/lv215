package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.stereotype.Service;
import com.softserve.edu.schedule.dto.UserForMeetingDTO;
import com.softserve.edu.schedule.entity.User;

@Service
public class UserForMeetingDTOConverter {
    public UserForMeetingDTO getDTO(final User user) {
        if (user != null) {
            UserForMeetingDTO userForMeetingDTO = new UserForMeetingDTO();
            if (user.getId() != null) {
                userForMeetingDTO.setId(user.getId());
            }
            if (user.getFirstName() != null) {
                userForMeetingDTO.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null) {
                userForMeetingDTO.setLastName(user.getLastName());
            }
            return userForMeetingDTO;
        }
        return null;
    }
}
