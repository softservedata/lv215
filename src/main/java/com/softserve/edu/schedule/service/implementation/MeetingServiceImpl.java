/*
 * Implementation of the Interface for Meetings Service.
 *
 * Version 03.01.17
 *
 * 03.01.17
 *
 * Create by Melnyk Bohdan
 */
package com.softserve.edu.schedule.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.aspects.PerfomanceLoggable;
import com.softserve.edu.schedule.dao.MeetingDAO;
import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dto.MeetingCompactDTO;
import com.softserve.edu.schedule.dto.MeetingDTO;
import com.softserve.edu.schedule.dto.filter.MeetingFilter;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.entity.MeetingStatus;
import com.softserve.edu.schedule.service.MeetingService;
import com.softserve.edu.schedule.service.implementation.dtoconverter.MeetingCompactDTOConverter;
import com.softserve.edu.schedule.service.implementation.dtoconverter.MeetingDTOConverter;

/**
 * This is implementation of the interface for managing Meetings Service.
 *
 * @version 1.0 12.12.2016
 * @author IT Academy
 */
@Transactional
@Service
@PerfomanceLoggable
public class MeetingServiceImpl implements MeetingService {

    /**
     * Field for MeetingDTOConverter.
     */
    @Autowired
    private MeetingDAO meetingDao;

    /**
     * Field for MeetingDTOConverter.
     */
    @Autowired
    private MeetingDTOConverter meetingDTOConverter;

    /**
     * Field for MeetingsForRoomDTOConverter.
     */
    @Autowired
    private MeetingCompactDTOConverter meetingCompactDTOConverter;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#create(com.softserve.
     * edu.schedule.entity.Meeting)
     */
    @Override
    public void create(final MeetingDTO meetingDTO) {
        meetingDTO.setStatus(getMeetingStatusDuringCreation(meetingDTO));
        meetingDao.create(meetingDTOConverter.getEntity(meetingDTO));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#getStatusbyString(java.
     * lang.String)
     */
    public MeetingStatus getStatusbyString(final String status) {
        return meetingDao.getStatusbyString(status);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#update(com.softserve.
     * edu.schedule.entity.Meeting)
     */
    @Override
    public void update(final MeetingDTO meetingDTO) {
        meetingDao.update(meetingDTOConverter.getEntity(meetingDTO));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#getById(java.lang.Long)
     */
    @Override
    @Transactional(readOnly = true)
    public MeetingDTO getById(final Long id) {
        return meetingDTOConverter.getDTO(meetingDao.getById(id));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#getAll()
     */
    @Override
    @Transactional(readOnly = true)
    public List<MeetingDTO> getAll() {
        return meetingDao.getAll().stream()
                .map(e -> meetingDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#delete(com.softserve.
     * edu.schedule.entity.Meeting)
     */
    @Override
    public void delete(final MeetingDTO meetingDTO) {
        meetingDao.delete(meetingDTOConverter.getEntity(meetingDTO));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#deleteById(java.lang.
     * Long)
     */
    @Override
    public void deleteById(final Long id) {
        meetingDao.deleteById(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#
     * getMeetingPageWithFilter(com.softserve.edu.schedule.dto.filter.
     * MeetingFilter, com.softserve.edu.schedule.dto.filter.Paginator)
     */
    @Override
    @Transactional(readOnly = true)
    public List<MeetingDTO> getMeetingPageWithFilter(
            final MeetingFilter meetingFilter,
            final Paginator meetingPaginator) {
        return meetingDao
                .getMeetingPageWithFilter(meetingFilter, meetingPaginator)
                .stream().map(e -> meetingDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#sort(java.lang.String,
     * com.softserve.edu.schedule.dao.Order)
     */
    @Override
    @Transactional(readOnly = true)
    public List<MeetingDTO> sort(final String field, final Order order) {
        return null;// meetingDao.sort(field, order);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#search(java.lang.
     * String, java.lang.String)
     */
    @Override
    @Transactional(readOnly = true)
    public List<MeetingDTO> search(final String field, final String pattern) {
        return meetingDao.search(field, pattern).stream()
                .map(e -> meetingDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#updateMeetingStatus(com
     * .softserve.edu.schedule.entity.Meeting,
     * com.softserve.edu.schedule.entity.MeetingStatus)
     */
    public void changeMeetingStatus(final Long id,
            final MeetingStatus meetingStatus) {
        meetingDao.changeMeetingStatus(id, meetingStatus);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#DublicatesOfGivenDTO(
     * com.softserve.edu.schedule.dto.MeetingDTO)
     */

    @Transactional(readOnly = true)
    public List<MeetingDTO> DublicatesOfGivenDTO(final MeetingDTO meetingDTO) {
        return meetingDao
                .dublicatesOfGivenFields(
                        meetingDTO.getSubject().getName().trim(),
                        meetingDTO.getOwner().getLastName().trim(),
                        meetingDTO.getRoom().getName().trim(),
                        meetingDTO.getDate(), meetingDTO.getStartTime())
                .stream().map(e -> meetingDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#
     * getMeetingStatusDuringCreation(com.softserve.edu.schedule.dto.MeetingDTO)
     * ======= /** Find all meetings in the DB by given date and roomId.
     *
     * @author Petro Zelyonka
     *
     * @param roomId room id for find meetings
     *
     * @param date date for find meetings
     *
     * @return List of the MeetingCompactDTO objects.
     */
    @Override
    public List<MeetingCompactDTO> getMeetingsByRoomIDAndDate(final Long roomId,
            final LocalDate date) {
        return meetingDao.getMeetingsByRoomIdAndDate(roomId, date).stream()
                .map(e -> meetingCompactDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * During creation checks which status can be given to the meeting depends
     * on room availability.
     *
     * @author Petro Zelyonka
     *
     * @param meetingDTO
     *            given meeting DTO
     *
     * @return correct MeetingStatus >>>>>>>
     *         d11c436cda17c9bf3f6ebe584643ca2acad7a94d
     */
    @Override
    @Transactional(readOnly = true)
    public MeetingStatus getMeetingStatusDuringCreation(
            final MeetingDTO meetingDTO) {
        if (meetingDao
                .getApprovedMeetingsByRoomIdAndTime(
                        meetingDTO.getRoom().getId(), meetingDTO.getDate(),
                        meetingDTO.getStartTime(), meetingDTO.getEndTime())
                .isEmpty()) {
            return MeetingStatus.APPROVED;
        }
        return MeetingStatus.NOT_APPROVED;
    }
}