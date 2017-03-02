/* RoomController 1.0 01/15/2017 */
package com.softserve.edu.schedule.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.softserve.edu.schedule.controller.constants.RoomContrConst;
import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.dto.RoomEquipmentDTO;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.RoomFilter;
import com.softserve.edu.schedule.service.LocationService;
import com.softserve.edu.schedule.service.RoomEquipmentService;
import com.softserve.edu.schedule.service.RoomService;
import com.softserve.edu.schedule.service.implementation.editor.LocationDTOEditor;
import com.softserve.edu.schedule.service.implementation.editor.RoomEquipmentDTOEditor;

/**
 * A controller class of rooms pages.
 *
 * @version 1.0 15 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@RequestMapping(RoomContrConst.ROOMS_URL)
@Controller
@SessionAttributes({RoomContrConst.FILTER_MODEL_ATTR,
        RoomContrConst.ROOM_PAGINATOR_MODEL_ATTR})
public class RoomController {

    /**
     * RoomService example to provide rooms list to the model.
     */
    @Autowired
    private RoomService roomService;

    /**
     * LocationService example to provide locations list to the model.
     */
    @Autowired
    private LocationService locationService;

    /**
     * RoomEquipmentService example to provide room equipments list to the
     * model.
     */
    @Autowired
    private RoomEquipmentService roomEquipmentService;

    /**
     * LocationDTOEditor example to provide conversions from form select fields
     * to DTO.
     */
    @Autowired
    private LocationDTOEditor locationDTOEditor;

    /**
     * RoomEquipmentDTOEditor example to provide conversions from form select
     * fields to DTO.
     */
    @Autowired
    private RoomEquipmentDTOEditor roomEquipmentDTOEditor;

    /**
     * Initialize binder for room model.
     *
     * @param binder
     *            a WebDataBinder example to initialize.
     */
    @InitBinder(RoomContrConst.ROOM_MODEL_ATTR)
    protected void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(LocationDTO.class, locationDTOEditor);
        binder.registerCustomEditor(RoomEquipmentDTO.class,
                roomEquipmentDTOEditor);
    }

    /**
     * Initialize binder for filter model.
     *
     * @param binder
     *            a WebDataBinder example to initialize.
     */
    @InitBinder(RoomContrConst.FILTER_MODEL_ATTR)
    protected void initBinderFilter(final WebDataBinder binder) {
        binder.registerCustomEditor(RoomEquipmentDTO.class,
                roomEquipmentDTOEditor);
    }

    /**
     * Provides room model.
     *
     * @return new RoomDTO object.
     */
    @ModelAttribute(RoomContrConst.ROOM_MODEL_ATTR)
    public RoomDTO getRoomDTO() {
        return new RoomDTO();
    }

    /**
     * Provides room filter.
     *
     * @return new RoomFilter object.
     */
    @ModelAttribute(RoomContrConst.FILTER_MODEL_ATTR)
    public RoomFilter getFilter() {
        return new RoomFilter();
    }

    /**
     * Provides pagination object for rooms list page.
     *
     * @return new Paginator object.
     */
    @ModelAttribute(RoomContrConst.ROOM_PAGINATOR_MODEL_ATTR)
    public Paginator getPaginator() {
        return new Paginator();
    }

    /**
     * Controls view of rooms list page.
     *
     * @param model
     *            rooms list page view model.
     *
     * @param filter
     *            rooms list page view filter.
     *
     * @param paginator
     *            rooms list paginator.
     *
     * @return rooms list page URL
     */
    @RequestMapping(method = RequestMethod.GET)
    public String listRooms(final Model model,
            @ModelAttribute(RoomContrConst.FILTER_MODEL_ATTR) final RoomFilter filter,
            @ModelAttribute(RoomContrConst.ROOM_PAGINATOR_MODEL_ATTR) final Paginator paginator) {
        model.addAttribute(RoomContrConst.ROOMS_MODEL_ATTR,
                roomService.getRoomsPageWithFilter(filter, paginator));
        model.addAttribute(RoomContrConst.LOCATIONS_MODEL_ATTR,
                locationService.getAll());
        model.addAttribute(RoomContrConst.EQUIPMENTS_MODEL_ATTR,
                roomEquipmentService.getAll());
        model.addAttribute(RoomContrConst.FILTER_MODEL_ATTR, filter);
        model.addAttribute(RoomContrConst.ROOM_PAGINATOR_MODEL_ATTR, paginator);
        return RoomContrConst.ROOMS_LIST_URL;
    }

    /**
     * Controls processing of room update information form.
     *
     * @param roomDTO
     *            an RoomDTO example which is built based on form data.
     *
     * @param br
     *            binding result to check validation errors
     *
     * @param model
     *            room update information page view model.
     *
     * @return rooms list page redirect URL
     */
    @RequestMapping(value = RoomContrConst.ROOM_EDIT_MAPPING,
            method = RequestMethod.POST)
    public String update(
            @ModelAttribute(RoomContrConst.ROOM_MODEL_ATTR) @Valid final RoomDTO roomDTO,
            final BindingResult br, final Model model) {
        if (br.hasErrors()) {
            model.addAttribute(RoomContrConst.LOCATIONS_MODEL_ATTR,
                    locationService.getAll());
            model.addAttribute(RoomContrConst.EQUIPMENTS_MODEL_ATTR,
                    roomEquipmentService.getAll());
            model.addAttribute(RoomContrConst.ROOM_FILES_MODEL_ATTR,
                    roomService.showRoomFiles(roomDTO.getId()));
            return RoomContrConst.ROOM_EDIT_URL;
        }
        roomService.update(roomDTO);
        return RoomContrConst.ROOMS_REDIRECT_URL;
    }

    /**
     * Controls view of room update information page.
     *
     * @param id
     *            room id to update information.
     *
     * @param model
     *            room update information page view model.
     *
     * @return room update information page URL
     */
    @PreAuthorize(RoomContrConst.ROOM_EDIT_PERMISSIONS)
    @RequestMapping(value = RoomContrConst.ROOM_EDIT_MAPPING,
            method = RequestMethod.GET)
    public String updateForm(
            @PathVariable(RoomContrConst.PATH_VAR_ID) final Long id,
            final Model model) {
        model.addAttribute(RoomContrConst.ROOM_MODEL_ATTR,
                roomService.getById(id));
        model.addAttribute(RoomContrConst.LOCATIONS_MODEL_ATTR,
                locationService.getAll());
        model.addAttribute(RoomContrConst.EQUIPMENTS_MODEL_ATTR,
                roomEquipmentService.getAll());
        model.addAttribute(RoomContrConst.ROOM_FILES_MODEL_ATTR,
                roomService.showRoomFiles(id));
        return RoomContrConst.ROOM_EDIT_URL;
    }

    /**
     * Controls processing of room create form.
     *
     * @param roomDTO
     *            an RoomDTO example which is built based on form data.
     *
     * @param br
     *            binding result to check validation errors
     *
     * @param model
     *            room create page view model.
     *
     * @return rooms list page redirect URL
     */
    @RequestMapping(value = RoomContrConst.ROOM_CREATE_MAPPING,
            method = RequestMethod.POST)
    public String create(
            @ModelAttribute(RoomContrConst.ROOM_MODEL_ATTR) @Valid final RoomDTO roomDTO,
            final BindingResult br, final Model model) {
        if (br.hasErrors()) {
            model.addAttribute(RoomContrConst.LOCATIONS_MODEL_ATTR,
                    locationService.getAll());
            model.addAttribute(RoomContrConst.EQUIPMENTS_MODEL_ATTR,
                    roomEquipmentService.getAll());
            return RoomContrConst.ROOM_CREATE_URL;
        }
        roomService.create(roomDTO);
        return RoomContrConst.ROOMS_REDIRECT_URL;
    }

    /**
     * Controls view of room create page.
     *
     * @param model
     *            room create page view model.
     *
     * @return room create page URL
     */
    @PreAuthorize(RoomContrConst.ROOM_EDIT_PERMISSIONS)
    @RequestMapping(value = RoomContrConst.ROOM_CREATE_MAPPING,
            method = RequestMethod.GET)
    public String createForm(final Model model) {
        model.addAttribute(RoomContrConst.ROOM_MODEL_ATTR, new RoomDTO());
        model.addAttribute(RoomContrConst.LOCATIONS_MODEL_ATTR,
                locationService.getAll());
        model.addAttribute(RoomContrConst.EQUIPMENTS_MODEL_ATTR,
                roomEquipmentService.getAll());
        return RoomContrConst.ROOM_CREATE_URL;
    }

    /**
     * Controls processing of room delete URL.
     *
     * @param id
     *            an room id to delete from database.
     *
     * @return rooms list page redirect URL
     */
    @PreAuthorize(RoomContrConst.ROOM_EDIT_PERMISSIONS)
    @RequestMapping(value = RoomContrConst.ROOM_DELETE_MAPPING)
    public String deleteRoom(@PathVariable final Long id) {
        roomService.deleteById(id);
        return RoomContrConst.ROOMS_REDIRECT_URL;
    }

    /**
     * Controls view of room details show page.
     *
     * @param id
     *            room id to show details.
     *
     * @param model
     *            room details show page view model.
     *
     * @return room show detail page URL
     */
    @RequestMapping(value = RoomContrConst.ROOM_SHOW_MAPPING,
            method = RequestMethod.GET)
    public String showRoom(
            @PathVariable(RoomContrConst.PATH_VAR_ID) final Long id,
            final Model model) {
        model.addAttribute(RoomContrConst.ROOM_MODEL_ATTR,
                roomService.getById(id));
        model.addAttribute(RoomContrConst.ROOM_FILES_MODEL_ATTR,
                roomService.showRoomFiles(id));
        return RoomContrConst.ROOM_SHOW_URL;
    }

    /**
     * Controls room files upload process.
     *
     * @param id
     *            room id to upload files.
     *
     * @param file
     *            file to upload
     *
     * @param model
     *            room view model.
     *
     * @return room edit page URL
     *
     * @throws IOException
     *             if something happened during file upload
     */
    @PreAuthorize(RoomContrConst.ROOM_EDIT_PERMISSIONS)
    @RequestMapping(value = RoomContrConst.ROOM_FILE_UPLOAD_MAPPING,
            method = RequestMethod.POST)
    public String uploadFile(@PathVariable final Long id,
            @RequestParam final MultipartFile file, final Model model)
            throws IOException {
        model.addAttribute(RoomContrConst.ROOM_MODEL_ATTR,
                roomService.getById(id));
        model.addAttribute(RoomContrConst.LOCATIONS_MODEL_ATTR,
                locationService.getAll());
        model.addAttribute(RoomContrConst.EQUIPMENTS_MODEL_ATTR,
                roomEquipmentService.getAll());
        model.addAttribute(RoomContrConst.ROOM_FILES_MODEL_ATTR,
                roomService.showRoomFiles(id));
        roomService.uploadFile(file, id);
        return RoomContrConst.ROOM_FILE_REDIRECT_URL;
    }

    /**
     * Controls room files delete process.
     *
     * @param id
     *            room id to delete files.
     *
     * @param fileName
     *            file to delete
     *
     * @return room edit page URL
     *
     */
    @PreAuthorize(RoomContrConst.ROOM_EDIT_PERMISSIONS)
    @RequestMapping(RoomContrConst.ROOM_FILE_DELETE_MAPPING)
    public String deleteFile(@PathVariable final Long id,
            @PathVariable final String fileName) {
        roomService.deleteFileByRoomId(id, fileName);
        return RoomContrConst.ROOM_FILE_REDIRECT_URL;
    }

    /**
     * Controls room files download process.
     *
     * @param roomId
     *            room id to download files.
     *
     * @param fileName
     *            file to download
     *
     * @param response
     *            response to download file
     *
     * @throws IOException
     *             if something happened during file download
     *
     */
    @RequestMapping(RoomContrConst.ROOM_FILE_DOWNLOAD_MAPPING)
    public void downloadFile(@PathVariable final Long roomId,
            @PathVariable final String fileName,
            final HttpServletResponse response) throws IOException {
        roomService.retriveFileByRoomId(roomId, fileName, response);
    }
}
