/*
 * Implementation of the MeetingHistoryService interface. 
 */
package com.softserve.edu.schedule.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.aspects.PerfomanceLoggable;
import com.softserve.edu.schedule.dao.MeetingHistoryDAO;
import com.softserve.edu.schedule.dto.MeetingHistoryDTO;
import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.MeetingHistory;
import com.softserve.edu.schedule.entity.MeetingStatus;
import com.softserve.edu.schedule.service.MeetingHistoryService;
import com.softserve.edu.schedule.service.MeetingService;
import com.softserve.edu.schedule.service.implementation.dtoconverter.MeetingHistoryDTOConverter;
import com.softserve.edu.schedule.service.implementation.dtoconverter.MeetingToMeetingHistoryConverter;

/**
 * This class is implementation of MeetingHistoryService.
 *
 * @version 1.0 22.02.2017
 * @author Bohdan Melnyk, Oleksandr Butyter
 */
@Transactional
@Service
@PerfomanceLoggable
public class MeetingHistoryServiceImpl implements MeetingHistoryService {

    /**
     * Field for MeetingHistoryDAO.
     */
    @Autowired
    private MeetingHistoryDAO meetingHistoryDAO;

    /**
     * Field for MeetingHistoryDTOConverter.
     */
    @Autowired
    private MeetingHistoryDTOConverter meetingHistoryDTOConverter;

    /**
     * Field for MeetingHistoryDTOConverter.
     */
    @Autowired
    private MeetingToMeetingHistoryConverter meetingToMeetingHistoryConverter;

    /**
     * Field for MeetingService.
     */
    @Autowired
    private MeetingService meetingService;

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingHistoryService#getAll()
     */
    @Override
    @Transactional(readOnly = true)
    public List<MeetingHistoryDTO> getAll() {
        return meetingHistoryDAO.getAll().stream()
                .map(e -> meetingHistoryDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingHistoryService#
     * getMeetingHistoryByIdMeeting(java.lang.String)
     */
    @Override
    @Transactional(readOnly = true)
    public List<MeetingHistory> getMeetingHistoryByIdMeeting(
            final String idMeeting) {
        return meetingHistoryDAO.getMeetingHistoryByIdMeeting(idMeeting);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingHistoryService#create(com.
     * softserve.edu.schedule.entity.MeetingHistory)
     */
    @Override
    public void create(final MeetingHistory meetingHistory) {
        meetingHistoryDAO.create(meetingHistory);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingHistoryService#backup(com.
     * softserve.edu.schedule.entity.Meeting)
     */
    @Override
    public void backup(final Meeting meeting) {
        create(meetingToMeetingHistoryConverter
                .convertMeetingToMeetingHistory(meeting));
        meetingService.changeMeetingStatus(meeting.getId(),
                MeetingStatus.ARCHIVED);
    }

}
