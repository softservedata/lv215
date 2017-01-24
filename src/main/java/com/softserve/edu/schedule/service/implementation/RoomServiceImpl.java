/* RoomServiceImpl 1.0 01/02/2017 */
package com.softserve.edu.schedule.service.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.dao.RoomDAO;
import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.dto.MeetingCompactDTO;
import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.dto.filter.RoomFilter;
import com.softserve.edu.schedule.entity.MeetingStatus;
import com.softserve.edu.schedule.entity.Room;
import com.softserve.edu.schedule.service.RoomService;
import com.softserve.edu.schedule.service.implementation.dtoconverter.MeetingCompactDTOConverter;
import com.softserve.edu.schedule.service.implementation.dtoconverter.RoomDTOConverter;
import com.softserve.edu.schedule.service.implementation.mailsenders.MeetingCanceledMailService;

/**
 * A class to provide service operations with Room entity.
 *
 * @version 1.0 02 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    /**
     * RoomDAO example to provide database operations.
     */
    @Autowired
    private RoomDAO roomDAO;

    /**
     * RoomDTOConverter example to provide to DTO and from DTO conversion.
     */
    @Autowired
    private MeetingCompactDTOConverter meetingForMailDTOConverter;

    /**
     * RoomDTOConverter example to provide to DTO and from DTO conversion.
     */
    @Autowired
    private RoomDTOConverter roomDTOConverter;

    /**
     * MeetingCanceledMailService example to provide send mail to user
     * operations.
     */
    @Autowired
    private MeetingCanceledMailService meetingCanceledMailService;

    /**
     * Save new room entity into the database.
     *
     * @param room
     *            a new room DTO to storage in database.
     */
    @Override
    public void create(final RoomDTO roomDTO) {
        roomDAO.create(roomDTOConverter.getEntity(roomDTO));
    }

    /**
     * Update existed room entity in the database.
     *
     * @param roomDTO
     *            a room DTO to update in database.
     */
    @Override
    public void update(final RoomDTO roomDTO) {
        roomDAO.update(roomDTOConverter.getEntity(roomDTO));
    }

    /**
     * Find room entity in the database by id.
     *
     * @param id
     *            a room id to find in the database.
     * @return an room DTO object if room with this id exists in the database or
     *         Null if room not found
     */
    @Override
    @Transactional(readOnly = true)
    public RoomDTO getById(final Long id) {
        return roomDTOConverter.getDTO(roomDAO.getById(id));
    }

    /**
     * Find a room DTO in the database by name and location.
     *
     * @param roomName
     *            a room name to find in the database.
     * 
     * @param location
     *            a location to find room.
     * 
     * @return a room DTO with given name and location.
     */
    @Override
    @Transactional(readOnly = true)
    public RoomDTO getByNameAndLocation(final String roomName,
            final LocationDTO location) {
        return roomDTOConverter.getDTO(
                roomDAO.getByNameAndLocationId(roomName, location.getId()));
    }

    /**
     * Delete existed room entity from the database by id.
     *
     * @param id
     *            a room id to delete from database.
     */
    @Override
    public void deleteById(final Long id) {
        Room room = roomDAO.getById(id);
        List<MeetingCompactDTO> meetingToAlert = new ArrayList<>();
        room.getMeetings().forEach(e -> {
            if (e.getDate().isAfter(LocalDate.now().minusDays(1))) {
                e.setStatus(MeetingStatus.NOT_APPROVED);
                meetingToAlert.add(meetingForMailDTOConverter.getDTO(e));
            }
            e.setRoom(null);
        });
        meetingToAlert.forEach(
                e -> meetingCanceledMailService.sendInfoMessageRoomDeletion(e));
        roomDAO.delete(room);
    }

    /**
     * Find all rooms entities in the database.
     * 
     * @return List of the room DTO objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<RoomDTO> getAll() {
        return roomDAO.getAll().stream().map(e -> roomDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Find all rooms entities in the database with location and equipment
     * details.
     * 
     * @return List of the room DTO objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<RoomDTO> getAllWithDetails() {
        return roomDAO.getAllWithDetails().stream()
                .map(e -> roomDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Find all rooms entities in the database with applied filter
     * 
     * @param roomFilter
     *            a filter to apply.
     * 
     * @return List of the room DTO objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<RoomDTO> getRoomsWithFilter(final RoomFilter roomFilter) {
        return roomDAO.getRoomsWithFilter(roomFilter).stream()
                .map(e -> roomDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    // TODO Delete after all go to DTOs
    @Override
    public Room getEntityById(final Long id) {
        return roomDAO.getById(id);
    }

}
