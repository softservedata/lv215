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
import com.softserve.edu.schedule.service.MeetingHistoryService;
import com.softserve.edu.schedule.service.implementation.dtoconverter.MeetingHistoryDTOConverter;
import com.softserve.edu.schedule.service.implementation.dtoconverter.MeetingToMeetingHistoryConverter;

/**
 * This class is implementation of MeetingHistoryService.
 *
 * @version 1.0 12.12.2016
 * @author Bohdan Melnyk
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

    @Override
    public void create(final MeetingHistory meetingHistory) {
        meetingHistoryDAO.create(meetingHistory);
    }

    /**
     * Backup given meeting to MeetinHistory table.
     * 
     * @param meeting
     *            meeting to backup.
     */
    @Override
    public void backup(Meeting meeting) {
        create(meetingToMeetingHistoryConverter
                .convertMeetingToMeetingHistory(meeting));
    }

}
