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

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.dao.MeetingDAO;
import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.MeetingStatus;
import com.softserve.edu.schedule.entity.Room;
import com.softserve.edu.schedule.entity.Subject;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserGroup;
import com.softserve.edu.schedule.service.MeetingService;

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

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#create(com.softserve.
     * edu.schedule.entity.Meeting)
     */
    @Override
    public void create(Meeting meeting) {
        meetingDao.create(meeting);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#update(com.softserve.
     * edu.schedule.entity.Meeting)
     */
    @Override
    public void update(final Meeting meeting) {
        meetingDao.update(meeting);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#getById(java.lang.Long)
     */
    @Override
    @Transactional(readOnly = true)
    public Meeting getById(final Long id) {
        return meetingDao.getById(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#getAll()
     */
    @Override
    @Transactional(readOnly = true)
    public List<Meeting> getAll() {
        return meetingDao.getAll();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#delete(com.softserve.
     * edu.schedule.entity.Meeting)
     */
    @Override
    public void delete(final Meeting meeting) {
        meetingDao.delete(meeting);
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
    public List<Meeting> sort(final String field, final Order order) {
        return meetingDao.sort(field, order);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#search(java.lang.
     * String, java.lang.String)
     */
    @Override
    @Transactional(readOnly = true)
    public List<Meeting> search(final String field, final String pattern) {
        return meetingDao.search(field, pattern);
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
    public List<Meeting> searchByLevel(final Integer level) {
        return meetingDao.searchByLevel(level.toString());
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
    public List<Meeting> searchByStatus(final MeetingStatus meetingStatus) {
        return meetingDao.searchByStatus(meetingStatus.toString());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#changeStatus(com.
     * softserve.edu.schedule.entity.Meeting,
     * com.softserve.edu.schedule.entity.MeetingStatus)
     */
    @Override
    public void changeStatus(final Meeting meeting,
            final MeetingStatus meetingStatus) {
        meetingDao.changeStatus(meeting, meetingStatus);
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
    public List<Meeting> searchByDescription(final String description) {
        return meetingDao.searchByDescription(description);
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
    public List<Meeting> searchBySubject(final Subject subject) {
        return meetingDao.searchBySubject(subject.getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#searchByOwner(com.
     * softserve.edu.schedule.entity.User)
     */
    @Override
    @Transactional(readOnly = true)
    public List<Meeting> searchByOwner(final User user) {
        return meetingDao.searchByOwner(user.getLastName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#searchByRoom(com.
     * softserve.edu.schedule.entity.Room)
     */
    @Override
    @Transactional(readOnly = true)
    public List<Meeting> searchByRoom(final Room room) {
        return meetingDao.searchByRoom(room.getName());
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
    public List<Meeting> searchByDate(final LocalDateTime date) {
        String pattern = "" + date.getMonthValue() + "." + date.getDayOfMonth()
                + "." + date.getYear();
        return meetingDao.searchByDate(pattern);
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
    public List<Meeting> searchByUserGroup(final UserGroup userGroup) {
        return meetingDao.searchByDate(userGroup.getName());
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
    public List<Meeting> searchByUserGroups(final List<UserGroup> userGroups) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#deleteByOwner(com.
     * softserve.edu.schedule.entity.User)
     */
    @Override
    public void deleteByOwner(final User user) {
        // TODO
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#deleteByRoom(com.
     * softserve.edu.schedule.entity.Room)
     */
    @Override
    public void deleteByRoom(final Room room) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#deleteByDate(java.time.
     * LocalDateTime)
     */
    @Override
    public void deleteByDate(final LocalDateTime localDateTime) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#
     * deleteMeetingByUserGroup(com.softserve.edu.schedule.entity.UserGroup)
     */
    @Override
    public void deleteMeetingByUserGroup(final UserGroup userGroup) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#deleteMeetingByStatus(
     * com.softserve.edu.schedule.entity.MeetingStatus)
     */
    @Override
    public void deleteMeetingByStatus(final MeetingStatus meetingStatus) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.MeetingService#sortByDescription(com.
     * softserve.edu.schedule.dao.Order)
     */
    @Override
    public List<Meeting> sortByDescription(final Order order) {
        return meetingDao.sortByDescription(order);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#sortBySubject(com.
     * softserve.edu.schedule.dao.Order)
     */
    @Override
    public List<Meeting> sortBySubject(final Order order) {
        return meetingDao.sortBySubject(order);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#sortByOwner(com.
     * softserve.edu.schedule.dao.Order)
     */
    @Override
    public List<Meeting> sortByOwner(final Order order) {
        return meetingDao.sortByOwner(order);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#sortByRoom(com.
     * softserve.edu.schedule.dao.Order)
     */
    @Override
    public List<Meeting> sortByRoom(final Order order) {
        return meetingDao.sortByRoom(order);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#sortByLevel(com.
     * softserve.edu.schedule.dao.Order)
     */
    @Override
    public List<Meeting> sortByLevel(final Order order) {
        return meetingDao.sortByLevel(order);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.MeetingService#sortByStatus(com.
     * softserve.edu.schedule.dao.Order)
     */
    @Override
    public List<Meeting> sortByStatus(final Order order) {
        return meetingDao.sortByStatus(order);
    }
}
