/*
 * Interface for Meetings Service.
 *
 * Version 03.01.17
 *
 * 03.01.17
 *
 * Create by Melnyk Bohdan
 */

package com.softserve.edu.schedule.service;

import java.util.List;

import com.softserve.edu.schedule.dto.MeetingDTO;
import com.softserve.edu.schedule.dto.MeetingForCalendarDTO;
import com.softserve.edu.schedule.dto.filter.MeetingFilter;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.entity.MeetingStatus;

/**
 * This interface for managing Meetings Service.
 *
 * @version 1.0 12.12.2016
 * @author IT Academy
 */
public interface MeetingService {

    /**
     * Saving Meeting in database.
     *
     * @param meetingDTO
     *            - Meeting object
     */
    void create(MeetingDTO meetingDTO);

    /**
     * Return a Meeting object if found.
     *
     * @param id
     *            of Meeting transfer object
     * @return Meeting transfer object
     */
    MeetingDTO getById(Long id);

    /**
     * Gives MeetingStatus by given String name.
     *
     * @param status
     *            name of status
     * @return MeetingStatus object.
     */
    MeetingStatus getStatusbyString(String status);

    /**
     * Read all meetings from DB.
     *
     * @return List<Meeting> our meetings.
     */
    List<MeetingDTO> getAll();

    /**
     * Update (replace) existence meeting in DB. If such meeting no exist in DB,
     * it will add it.
     *
     * @param meetingDTO
     *            our meeting.
     */
    void update(MeetingDTO meetingDTO);

    /**
     * Delete meeting from DB.
     *
     * @param meetingDTO
     *            our meeting.
     */
    void delete(MeetingDTO meetingDTO);

    /**
     * Delete meeting from DB by given id.
     *
     * @param id
     *            our id of meeting.
     */
    void deleteById(Long id);

    /**
     * Returns List of MeetingDTO by given filter and paginator for Room page.
     *
     * @param meetingFilter
     *            filter for meetings.
     * @param meetingPaginator
     *            paginator for meetings.
     * @return List of MeetingDTO
     */
    List<MeetingDTO> getMeetingPageWithFilter(MeetingFilter meetingFilter,
            Paginator meetingPaginator);

    /**
     * Return a List of searched Meeting transfer objects.
     *
     * @param field
     *            for search
     * @param pattern
     *            - input string
     * @return List of sorted Subject transfer objects
     */
    List<MeetingDTO> search(String field, String pattern);

    /**
     * Change existing status of the meeting to given new meeting status.
     *
     * @param id
     *            Meeting, in which the status will be changed.
     *
     * @param meetingStatus
     *            New meeting status.
     */
    void changeMeetingStatus(Long id, MeetingStatus meetingStatus);

    /**
     * Finds duplicates meeting (converted to meetingDTO) from DB by the given
     * meetingDTO.
     *
     * @param meetingDTO
     *            MeetingDTO instance
     *
     * @return list of duplicates of given DTO
     */
    List<MeetingDTO> duplicatesOfGivenDTO(MeetingDTO meetingDTO);

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
    MeetingStatus getMeetingStatusDuringCreation(MeetingDTO meetingDTO);

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
    List<MeetingForCalendarDTO> getMeetingsInIntervalByRoomId(Long roomId,
            String start, String end);

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
    List<MeetingForCalendarDTO> getMeetingsInIntervalByUserId(String userId,
            String start, String end);

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
    List<MeetingForCalendarDTO> getMeetingsInIntervalByGroupId(Long groupId,
            String start, String end);

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
    List<MeetingForCalendarDTO> getMeetingsInIntervalBySubjectId(Long subjectId,
            String start, String end);

    /**
     * Sends mail to MeetingOwner and UserGroupsCurators if the meeting status
     * was changed from APPROVED to DISAPPROVED or NOT_APPROVED.
     *
     * @param meetingDTO
     *            meeting DTO of changed meeting. We need it for confirmation.
     */
    void sendMailIfStatusChanged(MeetingDTO meetingDTO);
}
