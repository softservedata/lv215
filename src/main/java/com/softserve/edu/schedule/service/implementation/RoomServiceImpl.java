/* RoomServiceImpl 1.0 01/02/2017 */
package com.softserve.edu.schedule.service.implementation;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.softserve.edu.schedule.aspects.PerfomanceLoggable;
import com.softserve.edu.schedule.dao.FileStorageDAO;
import com.softserve.edu.schedule.dao.RoomDAO;
import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.RoomFilter;
import com.softserve.edu.schedule.entity.Room;
import com.softserve.edu.schedule.service.RoomService;
import com.softserve.edu.schedule.service.implementation.dtoconverter.RoomDTOConverter;

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
@PerfomanceLoggable
public class RoomServiceImpl implements RoomService {

    @Autowired
    private FileStorageDAO fileStorageDao;

    /**
     * RoomDAO example to provide database operations.
     */
    @Autowired
    private RoomDAO roomDAO;

    /**
     * RoomDTOConverter example to provide to DTO and from DTO conversion.
     */
    @Autowired
    private RoomDTOConverter roomDTOConverter;

    /**
     * Save new room entity into the database.
     *
     * @param roomDTO
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
     * Find rooms DTO in the database by name and location.
     *
     * @param roomName
     *            a room name to find in the database.
     *
     * @param location
     *            a location to find room.
     *
     * @return list of rooms DTO with given name and location.
     */
    @Override
    @Transactional(readOnly = true)
    public List<RoomDTO> getByNameAndLocation(final String roomName,
            final LocationDTO location) {
        return roomDAO.getByNameAndLocationId(roomName, location.getId())
                .stream().map(e -> roomDTOConverter.getDTO(e))
                .collect(Collectors.toList());
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
     * Find all rooms entities in the database with applied filter.
     *
     * @param roomFilter
     *            a filter to apply.
     *
     * @return List of the room DTO objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<RoomDTO> getRoomsPageWithFilter(final RoomFilter roomFilter,
            final Paginator roomPaginator) {
        return roomDAO.getRoomsPageWithFilter(roomFilter, roomPaginator)
                .stream().map(e -> roomDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public void uploadFile(MultipartFile file, Long id) {
        DBObject metadata = new BasicDBObject();
        metadata.put("roomId", Long.toString(id));
        try {
            fileStorageDao.store(file.getInputStream(),
                    file.getOriginalFilename(), metadata);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> showRoomFiles(Long id) {
        return fileStorageDao
                .findAllByIdAndType(Long.toString(id), "metadata.roomId")
                .stream().map(f -> f.getFilename())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRoomFileById(Long id) {
        fileStorageDao.deleteById("metadata.roomId", Long.toString(id));
    }

    @Override
    public void retriveRoomFileById(Long id, String fileName,
            HttpServletResponse response) throws IOException {
        GridFSDBFile file = fileStorageDao.retriveByIdAndFileName(
                Long.toString(id), fileName, "metadata.roomId");
        if (file != null) {
            response.setContentType(file.getContentType());
            response.setContentLength((new Long(file.getLength()).intValue()));
            response.setHeader("content-Disposition",
                    "attachment; filename=" + file.getFilename());
            IOUtils.copyLarge(file.getInputStream(),
                    response.getOutputStream());
        }
    }
}
