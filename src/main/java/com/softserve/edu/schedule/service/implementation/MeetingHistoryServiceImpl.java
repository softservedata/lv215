package com.softserve.edu.schedule.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.aspects.PerfomanceLoggable;
import com.softserve.edu.schedule.dao.MeetingHistoryDAO;
import com.softserve.edu.schedule.entity.MeetingHistory;
import com.softserve.edu.schedule.service.MeetingHistoryService;

/**
 * This class is implementation of MeetingHistoryService.
 *
 * @version 1.0 12.12.2016
 * @author Bohdan Melnyk
 */
@Transactional
@Service
@PerfomanceLoggable
public class MeetingHistoryServiceImpl implements  MeetingHistoryService {

    /**
     * Field for MeetingHistoryDAO.
     */
    @Autowired
    private MeetingHistoryDAO meetingHistoryDAO;
    
    /* (non-Javadoc)
     * @see com.softserve.edu.schedule.service.MeetingHistoryService#getAll()
     */
    @Override
    @Transactional(readOnly = true)
    public List<MeetingHistory> getAll(){
        return meetingHistoryDAO.getAll();  
        }
    
    /* (non-Javadoc)
     * @see com.softserve.edu.schedule.service.MeetingHistoryService#getMeetingHistoryByIdMeeting(java.lang.String)
     */
    @Override
    @Transactional(readOnly = true)
    public List<MeetingHistory> getMeetingHistoryByIdMeeting(
            final String idMeeting) {
        return meetingHistoryDAO.getMeetingHistoryByIdMeeting(idMeeting);
    }
}
