package com.softserve.edu.schedule.aspect;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.softserve.edu.schedule.dto.MeetingCompactDTO;
import com.softserve.edu.schedule.entity.MeetingStatus;
import com.softserve.edu.schedule.entity.Room;
import com.softserve.edu.schedule.service.implementation.dtoconverter.MeetingCompactDTOConverter;
import com.softserve.edu.schedule.service.implementation.mailsenders.MeetingCanceledMailService;

@Component
@Aspect
public class DeleteEventsAspect {

    /**
     * RoomDTOConverter example to provide to DTO and from DTO conversion.
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
    @Before("execution(* com.softserve.edu.schedule.dao.implementation.RoomDAOImpl.delete(..)) && args(room,..)")
    public void processingRoomBeforeDeletion(Room room) {
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
