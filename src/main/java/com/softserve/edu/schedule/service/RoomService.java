/* RoomService 1.0 01/02/2017 */
package com.softserve.edu.schedule.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.RoomFilter;

/**
 * An interface to provide service operations with Room entity.
 *
 * @version 1.0 02 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public interface RoomService {

    /**
     * Save new room entity into the database.
     *
     * @param roomDTO
     *            a new room DTO to storage in database.
     */
    void create(RoomDTO roomDTO);

    /**
     * Update existed room entity in the database.
     *
     * @param roomDTO
     *            a room DTO to update in database.
     */
    void update(RoomDTO roomDTO);

    /**
     * Find room entity in the database by id.
     *
     * @param id
     *            a room id to find in the database.
     * @return an room DTO object if room with this id exists in the database or
     *         Null if room not found
     */
    RoomDTO getById(Long id);

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
    List<RoomDTO> getByNameAndLocation(String roomName, LocationDTO location);

    /**
     * Delete existed room entity from the database by id.
     *
     * @param id
     *            a room id to delete from database.
     */
    void deleteById(Long id);

    /**
     * Find all rooms entities in the database.
     *
     * @return List of the room DTO objects.
     */
    List<RoomDTO> getAll();

    /**
     * Find all rooms entities in the database with location and equipment
     * details.
     *
     * @return List of the room DTO objects.
     */
    List<RoomDTO> getAllWithDetails();

    /**
     * Find all rooms entities in the database with applied filter.
     *
     * @param roomFilter
     *            a filter to apply.
     *
     * @param roomPaginator
     *            paginator object.
     *
     * @return List of the room DTO objects.
     */
    List<RoomDTO> getRoomsPageWithFilter(RoomFilter roomFilter,
            Paginator roomPaginator);

    /**
     * Upload room image file.
     *
     * @param file
     *            file to upload.
     *
     * @param id
     *            roomId to upload file.
     *
     * @throws IOException
     *             if something unexpected happens during file upload
     */
    void uploadFile(MultipartFile file, Long id) throws IOException;

    /**
     * Retrieve all room image files names.
     *
     * @param id
     *            roomId to show files.
     *
     * @return list of room files names
     */
    List<String> showRoomFiles(Long id);

    /**
     * Delete room image file.
     *
     * @param id
     *            roomId to delete file.
     *
     * @param fileName
     *            file name to delete file.
     */
    void deleteFileByRoomId(Long id, String fileName);

    /**
     * Download room image file.
     *
     * @param id
     *            roomId to download file.
     *
     * @param fileName
     *            file name to download file.
     *
     * @param response
     *            response to download file
     *
     * @throws IOException
     *             if something unexpected happens during file download
     */
    void retriveFileByRoomId(Long id, String fileName,
            HttpServletResponse response) throws IOException;
}
