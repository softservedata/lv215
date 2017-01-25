package com.softserve.edu.schedule.service.implementation.dtoconverter;


import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dto.RoomForMeetingDTO;

import com.softserve.edu.schedule.entity.Room;

@Service
public class RoomForMeetingDTOConverter {


    public RoomForMeetingDTO getDTO(final Room room) {
        RoomForMeetingDTO roomForMeetingDTO = new RoomForMeetingDTO();
        if (room != null) {
            if (room.getId() != null) {
                roomForMeetingDTO.setId(room.getId());
            }
            if (room.getName() != null) {
                roomForMeetingDTO.setName(room.getName());
            }
            return roomForMeetingDTO;
        }
        return null;
    }
}
