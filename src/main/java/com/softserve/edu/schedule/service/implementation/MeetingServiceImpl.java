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
import com.softserve.edu.schedule.dao.MeetingDAO;
import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dto.MeetingDTO;
import com.softserve.edu.schedule.dto.MeetingsForRoomDTO;
import com.softserve.edu.schedule.dto.UserGroupDTO;
import com.softserve.edu.schedule.entity.MeetingStatus;
import com.softserve.edu.schedule.entity.Meeting_;
import com.softserve.edu.schedule.service.MeetingService;
import com.softserve.edu.schedule.service.implementation.dtoconverter.MeetingDTOConverter;
import com.softserve.edu.schedule.service.implementation.dtoconverter.MeetingsForRoomDTOConverter;

/**
 * This is implementation of the interface for managing Meetings Service.
 * 
 * @version 1.0 12.12.2016
 * @author IT Academy
 */

@Transactional
@Service("meetingService")
public class MeetingServiceImpl implements MeetingService {

    /**
     * Field for meetingDAO.
     */
    @Autowired
    private MeetingDAO meetingDao;

    @Autowired
    private MeetingDTOConverter meetingDTOConverter;

    /**
     * Field for MeetingsForRoomDTOConverter.
     */
    @Autowired
    private MeetingsForRoomDTOConverter meetingsForRoomDTOConverter;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#create(com.softserve.
     * edu.schedule.entity.Meeting)
     */
    @Override
    public void create(final MeetingDTO meetingDTO) {
        meetingDao.create(meetingDTOConverter.getEntity(meetingDTO));

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
     * com.softserve.edu.schedule.service.MeetingService#searchByLevel(java.lang
     * .Integer)
     */
    @Override
    @Transactional(readOnly = true)
    public List<MeetingDTO> searchByLevel(final String pattern) {
        return meetingDao.search(Meeting_.level.getName(), pattern).stream()
                .map(e -> meetingDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#searchByStatus(com.
     * softserve.edu.schedule.entity.MeetingStatus)
     */
    @Override
    @Transactional(readOnly = true)
    public List<MeetingDTO> searchByStatus(final MeetingStatus meetingStatus) {
        return meetingDao.searchByStatus(meetingStatus.toString()).stream()
                .map(e -> meetingDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#searchByDescription(
     * java.lang.String)
     */
    @Override
    @Transactional(readOnly = true)
    public List<MeetingDTO> searchByDescription(final String description) {
        return meetingDao.searchByDescription(description).stream()
                .map(e -> meetingDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#searchBySubject(com.
     * softserve.edu.schedule.entity.Subject)
     */
    @Override
    @Transactional(readOnly = true)
    public List<MeetingDTO> searchBySubject(final String pattern) {
        return meetingDao.searchBySubject(pattern).stream()
                .map(e -> meetingDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#searchByOwner(com.
     * softserve.edu.schedule.entity.User)
     */
    @Override
    @Transactional(readOnly = true)
    public List<MeetingDTO> searchByOwner(final String pattern) {
        return meetingDao.searchByOwner(pattern).stream()
                .map(e -> meetingDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#searchByRoom(com.
     * softserve.edu.schedule.entity.Room)
     */
    @Override
    @Transactional(readOnly = true)
    public List<MeetingDTO> searchByRoom(final String pattern) {
        return meetingDao.searchByRoom(pattern).stream()
                .map(e -> meetingDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#searchByDate(java.time.
     * LocalDateTime)
     */
    @Override
    @Transactional(readOnly = true)
    public List<MeetingDTO> searchByDate(final LocalDate date) {
/*        String pattern = "" + date.getMonthValue() + "." + date.getDayOfMonth()
                + "." + date.getYear();*/
        return meetingDao.searchByDate(date).stream()
                .map(e -> meetingDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#searchByUserGroup(com.
     * softserve.edu.schedule.entity.UserGroup)
     */
    @Override
    @Transactional(readOnly = true)
    public List<MeetingDTO> searchByUserGroup(final String pattern) {
        return meetingDao.searchByUserGroup(pattern).stream()
                .map(e -> meetingDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#searchByUserGroups(java
     * .util.List)
     */
    @Override
    @Transactional(readOnly = true)
    public List<MeetingDTO> searchByUserGroups(
            final List<UserGroupDTO> userGroups) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#sortByDescription(com.
     * softserve.edu.schedule.dao.Order)
     */
    @Override
    public List<MeetingDTO> sortByDescription(final Order order) {
        return meetingDao.sortByDescription(order).stream()
                .map(e -> meetingDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#sortBySubject(com.
     * softserve.edu.schedule.dao.Order)
     */
    @Override
    public List<MeetingDTO> sortBySubject(final Order order) {
        return meetingDao.sortBySubject(order).stream()
                .map(e -> meetingDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#sortByOwner(com.
     * softserve.edu.schedule.dao.Order)
     */
    @Override
    public List<MeetingDTO> sortByOwner(final Order order) {
        return meetingDao.sortByOwner(order).stream()
                .map(e -> meetingDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#sortByRoom(com.
     * softserve.edu.schedule.dao.Order)
     */
    @Override
    public List<MeetingDTO> sortByRoom(final Order order) {
        return meetingDao.sortByRoom(order).stream()
                .map(e -> meetingDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#sortByLevel(com.
     * softserve.edu.schedule.dao.Order)
     */
    @Override
    public List<MeetingDTO> sortByLevel(final Order order) {
        return meetingDao.sortByLevel(order).stream()
                .map(e -> meetingDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#sortByStatus(com.
     * softserve.edu.schedule.dao.Order)
     */
    @Override
    public List<MeetingDTO> sortByStatus(final Order order) {
        return meetingDao.sortByStatus(order).stream()
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
     * @see com.softserve.edu.schedule.service.MeetingService#
     * getMeetingsByRoomIDAndDate(java.lang.Long, java.time.LocalDate)
     */
    @Override
    public List<MeetingsForRoomDTO> getMeetingsByRoomIDAndDate(Long roomId,
            LocalDate date) {
        return meetingDao.getMeetingsByRoomIDAndDate(roomId, date).stream()
                .map(e -> meetingsForRoomDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }
}
