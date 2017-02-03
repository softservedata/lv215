/* Room 1.0 02/03/2017 */
package com.softserve.edu.schedule.entitylisteners;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PreRemove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.softserve.edu.schedule.dto.MeetingCompactDTO;
import com.softserve.edu.schedule.entity.MeetingStatus;
import com.softserve.edu.schedule.entity.Room;
import com.softserve.edu.schedule.service.implementation.dtoconverter.MeetingCompactDTOConverter;
import com.softserve.edu.schedule.service.implementation.mailsenders.MeetingCanceledMailService;

/**
 * An entity listener class for Room entity.
 *
 * @version 1.0 03 February 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public class RoomEntityListener {
    
    /**
     * MeetingCompactDTOConverter example to provide to DTO and from DTO conversion.
     */
    @Autowired
    private MeetingCompactDTOConverter meetingCompactDTOConverter;

    /**
     * MeetingCanceledMailService example to provide send mail to user
     * operations.
     */
    @Autowired
    private MeetingCanceledMailService meetingCanceledMailService;
    
    /**
     * Set to all meetings in room null value in field room, changed status of
     * all future meetings to NOT_APPROVED and send mail messages to their
     * owners.
     * 
     * @param room
     *            Room to proceed meetings
     * 
     */
    @PreRemove
    public void processingRoomBeforeDeletion(Room room) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        List<MeetingCompactDTO> meetingToAlert = new ArrayList<>();
        room.getMeetings().forEach(e -> {
            if (e.getDate().isAfter(LocalDate.now().minusDays(1))) {
                e.setStatus(MeetingStatus.NOT_APPROVED);
                meetingToAlert.add(meetingCompactDTOConverter.getDTO(e));
            }
            e.setRoom(null);
        });
        meetingToAlert.forEach(
                e -> meetingCanceledMailService.sendInfoMessageRoomDeletion(e,
                        LocaleContextHolder.getLocale()));
    }
    
}
