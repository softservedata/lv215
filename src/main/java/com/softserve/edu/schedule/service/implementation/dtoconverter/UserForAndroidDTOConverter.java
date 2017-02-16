package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.stereotype.Component;

import com.softserve.edu.schedule.dto.UserForAndroidDTO;
import com.softserve.edu.schedule.entity.User;

@Component
public class UserForAndroidDTOConverter {

    public UserForAndroidDTO getDTO(final User user) {
        UserForAndroidDTO userForAndroidDTO = new UserForAndroidDTO();
        userForAndroidDTO.setId(user.getId().toString());
        userForAndroidDTO
                .setName(user.getFirstName() + " " + user.getLastName());
        userForAndroidDTO.setMail(user.getMail());
        userForAndroidDTO.setRole(user.getRole().name());
        return userForAndroidDTO;
    }
}
