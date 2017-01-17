/* RoomServiceImpl 1.0 01/02/2017 */
package com.softserve.edu.schedule.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.dao.LocationDAO;
import com.softserve.edu.schedule.dao.RoomDAO;
import com.softserve.edu.schedule.dao.RoomEquipmentDAO;
import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.dto.RoomEquipmentDTO;
import com.softserve.edu.schedule.dto.filter.RoomFilter;
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
@Service("roomService")
public class RoomServiceImpl implements RoomService {

    /**
     * RoomDAO example to provide database operations.
     */
    @Autowired
    private RoomDAO roomDAO;

    /**
     * LocationDAO example to provide database operations.
     */
    @Autowired
    private LocationDAO locationDAO;

    /**
     * RoomEquipmentDAO example to provide database operations.
     */
    @Autowired
    private RoomEquipmentDAO roomEquipmentDAO;

    /**
     * Save new room entity into the database.
     *
     * @param room
     *            a new room DTO to storage in database.
     */
    @Override
    @Transactional
    public void create(final RoomDTO roomDTO) {
        roomDAO.create(getEntity(roomDTO));
    }

    /**
     * Update existed room entity in the database.
     *
     * @param roomDTO
     *            a room DTO to update in database.
     */
    @Override
    @Transactional
    public void update(final RoomDTO roomDTO) {
        roomDAO.update(getEntity(roomDTO));
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
        return getDTO(roomDAO.getById(id));
    }

    /**
     * Find a room DTO in the database by name.
     *
     * @param roomName
     *            a room name to find in the database.
     * @return a room DTO with given name.
     */
    @Override
    @Transactional(readOnly = true)
    public RoomDTO getByName(final String roomName) {
        return getDTO(roomDAO.getByName(roomName));
    }

    /**
     * Delete existed room entity from the database.
     *
     * @param room
     *            a room DTO object to delete from database.
     */
    @Override
    @Transactional
    public void delete(final RoomDTO roomDTO) {
        roomDAO.delete(getEntity(roomDTO));
    }

    /**
     * Delete existed room entity from the database by id.
     *
     * @param id
     *            a room id to delete from database.
     */
    @Override
    @Transactional
    public void deleteById(final Long id) {
        roomDAO.deleteById(id);
    }

    /**
     * Find all rooms entities in the database.
     * 
     * @return List of the room DTO objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<RoomDTO> getAll() {
        return roomDAO.getAll().stream().map(e -> getDTO(e))
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
        return roomDAO.getAllWithDetails().stream().map(e -> getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Find all rooms in the database with location and equipment details which
     * has capacity in given interval.
     * 
     * @param minCapacity
     *            a minimum capacity.
     * @param maxCapacity
     *            a minimum capacity.
     * 
     * @return List of the room DTO objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<RoomDTO> getRoomsByCapacity(final int minCapacity,
            final int maxCapacity) {
        return roomDAO.getRoomsByCapacity(minCapacity, maxCapacity).stream()
                .map(e -> getDTO(e)).collect(Collectors.toList());
    }

    /**
     * Find all rooms in the database with location and equipment details by
     * given location.
     * 
     * @param location
     *            a location DTO to find rooms.
     * 
     * @return List of the room DTO objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<RoomDTO> getRoomsByLocation(final LocationDTO location) {
        return roomDAO.getRoomsByLocationId(location.getId()).stream()
                .map(e -> getDTO(e)).collect(Collectors.toList());
    }

    /**
     * Find all rooms entities in the database with location and equipment
     * details which has given list of equipments.
     * 
     * @param equipments
     *            a list of equipments DTO to find rooms.
     * 
     * @return List of the room DTO objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<RoomDTO> getRoomsWithEquipments(
            final List<RoomEquipmentDTO> equipments) {
        List<RoomEquipment> re = equipments.stream()
                .map(e -> roomEquipmentDAO.getById(e.getId()))
                .collect(Collectors.toList());
        return roomDAO.getRoomsWithEquipments(re).stream().map(e -> getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Add equipment to the room.
     * 
     * @param id
     *            room id to add equipment
     * 
     * @param equipmentID
     *            equipment id to add to room
     * 
     */
    @Override
    @Transactional
    public void addEquipment(Long id, Long equipmentID) {
        Room room = roomDAO.getById(id);
        RoomEquipment equipment = roomEquipmentDAO.getById(equipmentID);
        room.getEquipments().add(equipment);
    }

    /**
     * Delete equipment from the room.
     * 
     * @param id
     *            room id to delete equipment
     * 
     * @param equipmentID
     *            equipment id to delete from room
     * 
     */
    @Override
    @Transactional
    public void deleteEquipment(Long id, Long equipmentID) {
        Room room = roomDAO.getById(id);
        room.getEquipments().removeIf((e) -> e.getId() == equipmentID);
    }

    /**
     * Convert given RoomDTO object to Room object
     * 
     * @param roomDTO
     *            a RoomDTO object to convert.
     * 
     * @return a Room object or null if given @param roomDTO is null.
     */
    @Override
    public Room getEntity(RoomDTO roomDTO) {
        if (roomDTO != null) {
            Room room = new Room();
            if (roomDTO.getId() != null) {
                room.setId(roomDTO.getId());
            }
            if (roomDTO.getLocation() != null) {
                room.setLocation(
                        locationDAO.getById(roomDTO.getLocation().getId()));
            }
            if (roomDTO.getName() != null) {
                room.setName(roomDTO.getName());
            }
            if (roomDTO.getCapacity() != null) {
                room.setCapacity(Integer.parseInt(roomDTO.getCapacity()));
            }
            if (roomDTO.getEquipments() != null) {
                roomDTO.getEquipments().forEach(e -> room.getEquipments()
                        .add(roomEquipmentDAO.getById(e.getId())));
            }
            return room;
        }
        return null;
    }

    /**
     * Convert given Room object to RoomDTO object.
     * 
     * @param room
     *            a Room object to convert.
     * 
     * @return a RoomDTO object or null if given @param room is null.
     */
    @Override
    public RoomDTO getDTO(Room room) {
        if (room != null) {
            RoomDTO roomDTO = new RoomDTO();
            if (room.getId() != null) {
                roomDTO.setId(room.getId());
            }
            if (room.getName() != null) {
                roomDTO.setName(room.getName());
            }
            if (room.getCapacity() != null) {
                roomDTO.setCapacity(String.valueOf(room.getCapacity()));
            }
            if (room.getLocation() != null) {
                Location location = room.getLocation();
                LocationDTO locationDTO = new LocationDTO();
                locationDTO.setId(location.getId());
                locationDTO.setName(location.getName());
                locationDTO.setAddress(location.getAddress());
                locationDTO.setCoordinates(location.getCoordinates());
                roomDTO.setLocation(locationDTO);
            }
            if (room.getEquipments() != null) {
                room.getEquipments().forEach(e -> {
                    RoomEquipmentDTO reDTO = new RoomEquipmentDTO();
                    reDTO.setId(e.getId());
                    reDTO.setName(e.getName());
                    roomDTO.getEquipments().add(reDTO);
                });
            }
            return roomDTO;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomDTO> getRoomsWithFilter(RoomFilter roomFilter) {
        return roomDAO.getRoomsWithFilter(roomFilter).stream()
                .map(e -> getDTO(e)).collect(Collectors.toList());
    }

}
