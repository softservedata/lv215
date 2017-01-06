/* RoomServiceImpl 1.0 01/02/2017 */
package com.softserve.edu.schedule.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.dao.RoomDAO;
import com.softserve.edu.schedule.entity.Location;
import com.softserve.edu.schedule.entity.Room;
import com.softserve.edu.schedule.entity.RoomEquipment;
import com.softserve.edu.schedule.service.RoomService;

/**
 * A class to provide service operations with Room entity.
 *
 * @version 1.0 02 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Transactional
@Service("roomService")
public class RoomServiceImpl implements RoomService {

    /**
     * RoomDAO example to provide database operations.
     */
    @Autowired
    private RoomDAO roomDAO;

    /**
     * Save new room entity into the database.
     *
     * @param room
     *            a new room to storage in database.
     */
    @Override
    public void add(final Room room) {
        roomDAO.add(room);
    }

    /**
     * Update existed room entity in the database.
     *
     * @param origin
     *            a room to update in database.
     * @param updated
     *            a room with updated data to update origin info.
     */
    @Override
    public void update(final Room room) {
        roomDAO.update(room);
    }

    /**
     * Find room entity in the database by id.
     *
     * @param id
     *            a room id to find in the database.
     * @return an room object if room with this id exists in the database or
     *         Null if room not found
     */
    @Override
    @Transactional(readOnly = true)
    public Room findById(final Long id) {
        return roomDAO.findById(id);
    }

    /**
     * Find room entity in the database by name.
     *
     * @param roomName
     *            a room name to find in the database.
     * @return an room object if room with this name exists in the database or
     *         Null if room not found
     */
    @Override
    @Transactional(readOnly = true)
    public Room findByName(final String roomName) {
        return roomDAO.findAllWithDetails().stream()
                .filter(e -> e.getName().equals(roomName)).findFirst()
                .orElse(null);
    }

    /**
     * Delete existed room entity from the database.
     *
     * @param room
     *            a room object to delete from database.
     */
    @Override
    public void delete(final Room room) {
        roomDAO.delete(room);
    }

    /**
     * Delete existed room entity from the database by id.
     *
     * @param id
     *            a room id to delete from database.
     */
    @Override
    public void deleteById(final Long id) {
        roomDAO.deleteById(id);
    }

    /**
     * Find all rooms entities in the database.
     * 
     * @return List of the room objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Room> findAll() {
        return roomDAO.findAll();
    }

    /**
     * Find all rooms entities in the database with location and equipment
     * details.
     * 
     * @return List of the room objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Room> findAllWithDetails() {
        return roomDAO.findAllWithDetails();
    }

    /**
     * Find all rooms entities in the database with location and equipment
     * details which has capacity in given interval.
     * 
     * @param minCapacity
     *            a minimum capacity.
     * @param maxCapacity
     *            a minimum capacity.
     * 
     * @return List of the room objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Room> findRoomsByCapacity(final int minCapacity,
            final int maxCapacity) {
        return roomDAO.findAllWithDetails().stream()
                .filter(e -> e.getCapacity() >= minCapacity
                        && e.getCapacity() <= maxCapacity)
                .collect(Collectors.toList());
    }

    /**
     * Find all rooms entities in the database with location and equipment
     * details by given location.
     * 
     * @param location
     *            a location to find rooms.
     * 
     * @return List of the room objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Room> findRoomsByLocation(final Location location) {
        return roomDAO.findAllWithDetails().stream()
                .filter(e -> e.getLocation().getId().equals(location.getId()))
                .collect(Collectors.toList());
    }

    /**
     * Find all rooms entities in the database with location and equipment
     * details which has given list of equipments.
     * 
     * @param equipments
     *            a list of equipments to find rooms.
     * 
     * @return List of the room objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Room> findRoomsWithEquipments(
            final List<RoomEquipment> equipments) {
        return roomDAO.findAllWithDetails().stream()
                .filter(e -> e.getEquipments().containsAll(equipments))
                .collect(Collectors.toList());
    }

}
