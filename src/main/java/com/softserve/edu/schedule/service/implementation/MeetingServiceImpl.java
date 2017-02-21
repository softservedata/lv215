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
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.aspects.PerfomanceLoggable;
import com.softserve.edu.schedule.dao.MeetingDAO;
import com.softserve.edu.schedule.dao.MeetingHistoryDAO;
import com.softserve.edu.schedule.dto.MeetingDTO;
import com.softserve.edu.schedule.dto.MeetingForCalendarDTO;
import com.softserve.edu.schedule.dto.filter.MeetingFilter;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.MeetingStatus;
import com.softserve.edu.schedule.service.MeetingService;
import com.softserve.edu.schedule.service.implementation.dtoconverter.MeetingDTOConverter;
import com.softserve.edu.schedule.service.implementation.dtoconverter.MeetingForCalendarDTOConverter;
import com.softserve.edu.schedule.service.implementation.dtoconverter.MeetingToMeetingHistory;
import com.softserve.edu.schedule.service.implementation.mailsenders.MeetingChangeStatusMailService;

/**
 * This is implementation of the interface for managing Meetings Service.
 *
 * @version 1.0 12.12.2016
 * @author Bohdan Melnyk
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
     * Field for MeetingForCalendarDTOConverter.
     */
    @Autowired
    private MeetingForCalendarDTOConverter meetingForCalendarDTOConverter;

    /**
     * MeetingStatusChangeMailService example to provide send mail to user
     * operations.
     */
    @Autowired
    private MeetingChangeStatusMailService meetingChangeStatusMailService;

    /**
     * MeetingToMeetingHistory example operations.
     */
    @Autowired
    private MeetingToMeetingHistory meetingToMeetingHistory;

    /**
     * Saving Meeting in database.
     *
     * @param meetingDTO
     *            - Meeting object
     */
    @Override
    public void create(final MeetingDTO meetingDTO) {
        meetingDTO.setStatus(getMeetingStatusDuringCreation(meetingDTO));
        meetingDao.create(meetingDTOConverter.getEntity(meetingDTO));
    }

    /**
     * Gives MeetingStatus by given String name.
     *
     * @param status
     *            name of status
     * @return MeetingStatus object.
     */
    @Override
    public MeetingStatus getStatusbyString(final String status) {
        return meetingDao.getStatusbyString(status);
    }

    /**
     * MeetingHistoryDAO example. operations.
     */
    @Autowired
    MeetingHistoryDAO meetingHistoryDAO;

    /**
     * Update (replace) existence meeting in DB. If such meeting no exist in DB,
     * it will add it.
     *
     * @param meetingDTO
     *            our meeting.
     */
    @Override
    public void update(final MeetingDTO meetingDTO) {
        if (meetingDTO.getStatus() == MeetingStatus.FINISHED
                || meetingDTO.getStatus() == MeetingStatus.ARCHIVED) {
            return;
        }
        sendMailIfStatusChanged(meetingDTO);
        meetingDao.update(meetingDTOConverter.getEntity(meetingDTO));
    }

    /**
     * Sends mail to MeetingOwner and UserGroupsCurators if the meeting status
     * was changed from APPROVED to DISAPPROVED or NOT_APPROVED.
     *
     * @param meetingDTO
     *            meeting DTO of changed meeting. We need it for confirmation.
     */
    @Override
    public void sendMailIfStatusChanged(final MeetingDTO meetingDTO) {

        if ((meetingDao.getById(meetingDTO.getId())
                .getStatus() == MeetingStatus.APPROVED)
                && ((meetingDTO.getStatus() == MeetingStatus.DISAPPROVED)
                        || (meetingDTO
                                .getStatus() == MeetingStatus.NOT_APPROVED))) {
            meetingChangeStatusMailService
                    .sendInfoMessageAboutMeetingStatusChanging(
                            meetingDao.getById(meetingDTO.getId()),
                            LocaleContextHolder.getLocale());
        }

    }

    /**
     * Return a Meeting object if found.
     *
     * @param id
     *            of Meeting transfer object
     * @return Meeting transfer object
     */
    @Override
    @Transactional(readOnly = true)
    public MeetingDTO getById(final Long id) {
        return meetingDTOConverter.getDTO(meetingDao.getById(id));
    }

    /**
     * Read all meetings from DB.
     *
     * @return List<Meeting> our meetings.
     */
    @Override
    @Transactional(readOnly = true)
    public List<MeetingDTO> getAll() {
        return meetingDao.getAll().stream()
                .map(e -> meetingDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Delete meeting from DB.
     *
     * @param meetingDTO
     *            our meeting.
     */
    @Override
    public void delete(final MeetingDTO meetingDTO) {
        meetingDao.delete(meetingDTOConverter.getEntity(meetingDTO));
    }

    /**
     * Delete meeting from DB by given id.
     *
     * @param id
     *            our id of meeting.
     */
    @Override
    public void deleteById(final Long id) {
        Meeting meeting = meetingDao.getById(id);
        if (meeting.getStatus() == MeetingStatus.FINISHED) {
            meetingHistoryDAO.create(meetingToMeetingHistory
                    .convertMeetingToMeetingHistory(meeting));
        }
        meetingDao.deleteById(id);

    }

    /**
     * Returns List of MeetingDTO by given filter and paginator for Room page.
     *
     * @param meetingFilter
     *            filter for meetings.
     * @param meetingPaginator
     *            paginator for meetings.
     * @return List of MeetingDTO
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

    /**
     * Return a List of searched Meeting transfer objects.
     *
     * @param field
     *            for search
     * @param pattern
     *            - input string
     * @return List of sorted Subject transfer objects
     */
    @Override
    @Transactional(readOnly = true)
    public List<MeetingDTO> search(final String field, final String pattern) {
        return meetingDao.search(field, pattern).stream()
                .map(e -> meetingDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Change existing status of the meeting to given new meeting status.
     *
     * @param id
     *            Meeting, in which the status will be changed.
     *
     * @param meetingStatus
     *            New meeting status.
     */
    @Override
    public void changeMeetingStatus(final Long id,
            final MeetingStatus meetingStatus) {
        meetingDao.changeMeetingStatus(id, meetingStatus);
    }

    /**
     * Finds duplicates meeting (converted to meetingDTO) from DB by the given
     * meetingDTO.
     *
     * @param meetingDTO
     *            MeetingDTO instance
     *
     * @return list of duplicates of given DTO
     */
    @Override
    @Transactional(readOnly = true)
    public List<MeetingDTO> duplicatesOfGivenDTO(final MeetingDTO meetingDTO) {
        return meetingDao
                .dublicatesOfGivenFields(
                        meetingDTO.getSubject().getName().trim(),
                        meetingDTO.getOwner().getLastName().trim(),
                        meetingDTO.getRoom().getName().trim(),
                        meetingDTO.getDate(), meetingDTO.getStartTime())
                .stream().map(e -> meetingDTOConverter.getDTO(e))
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
     * @return correct MeetingStatus
     *
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

    /**
     * Method that's used to get all meetings in specified interval for
     * specified group.
     *
     * @author Andriy Zhydenko
     *
     * @param groupId
     *            id of a group to find
     * @param start
     *            start date of meetings
     * @param end
     *            end date of meetings
     * @return list of meetings that will be held for specified group in
     *         specified time limits
     */
    @Override
    public List<MeetingForCalendarDTO> getMeetingsInIntervalByGroupId(
            final Long groupId, final String start, final String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return meetingDao
                .getMeetingsInIntervalByGroupId(groupId, startDate, endDate)
                .stream().map(e -> meetingForCalendarDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Find all meetings in the DB by given date interval and roomId.
     *
     * @author Petro Zelyonka
     *
     * @param roomId
     *            room id for find meetings
     *
     * @param start
     *            start date for find meetings
     *
     * @param end
     *            end date for find meetings
     *
     * @return List of the MeetingForCalendarDTO objects.
     */
    @Override
    public List<MeetingForCalendarDTO> getMeetingsInIntervalByRoomId(
            final Long roomId, final String start, final String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return meetingDao
                .getMeetingsInIntervalByRoomId(roomId, startDate, endDate)
                .stream().map(e -> meetingForCalendarDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Find all meetings in the DB by given date interval and subjectId.
     *
     * @author Volodymyr Ped'ko
     *
     * @param subjectId
     *            subject id for find meetings
     *
     * @param start
     *            start date for find meetings
     *
     * @param end
     *            end date for find meetings
     *
     * @return List of the MeetingForCalendarDTO objects.
     */
    @Override
    public List<MeetingForCalendarDTO> getMeetingsInIntervalBySubjectId(
            final Long subjectId, final String start, final String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return meetingDao
                .getMeetingsInIntervalBySubjectId(subjectId, startDate, endDate)
                .stream().map(e -> meetingForCalendarDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Find all meetings in the DB by given date interval and userId.
     *
     * @author Petro Zelyonka
     *
     * @param userId
     *            user id for find meetings
     *
     * @param start
     *            end date for find meetings
     *
     * @param end
     *            start date for find meetings
     *
     * @return List of the MeetingForCalendarDTO objects.
     */
    @Override
    public List<MeetingForCalendarDTO> getMeetingsInIntervalByUserId(
            final String userId, final String start, final String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        Long id = Long.parseLong(userId);
        return meetingDao.getMeetingsInIntervalByUserId(id, startDate, endDate)
                .stream().map(e -> meetingForCalendarDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }
}
