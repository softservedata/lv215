/* RoomController 1.0 01/15/2017 */
package com.softserve.edu.schedule.controller;

import java.time.LocalDate;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.dto.RoomEquipmentDTO;
import com.softserve.edu.schedule.dto.filter.DateFilter;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.RoomFilter;
import com.softserve.edu.schedule.service.LocationService;
import com.softserve.edu.schedule.service.MeetingService;
import com.softserve.edu.schedule.service.RoomEquipmentService;
import com.softserve.edu.schedule.service.RoomService;
import com.softserve.edu.schedule.service.implementation.editor.DateEditor;
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
@RequestMapping(ControllerConst.RoomControllerConst.ROOMS_URL)
@Controller
@SessionAttributes({ControllerConst.RoomControllerConst.FILTER_MODEL_ATTR,
        ControllerConst.RoomControllerConst.ROOM_PAGINATOR_MODEL_ATTR})
public class RoomController implements ControllerConst.RoomControllerConst {

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
     * MeetingService example to provide meetings list to the model.
     */
    @Autowired
    private MeetingService meetingService;

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
     * DateEditor example to provide conversions from form date fields to
     * LocalDate objects.
     */
    @Autowired
    private DateEditor dateEditor;

    /**
     * Initialize binder for room model.
     *
     * @param binder
     *            a WebDataBinder example to initialize.
     */
    @InitBinder(ROOM_MODEL_ATTR)
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
    @InitBinder(FILTER_MODEL_ATTR)
    protected void initBinderFilter(final WebDataBinder binder) {
        binder.registerCustomEditor(RoomEquipmentDTO.class,
                roomEquipmentDTOEditor);
    }

    /**
     * Initialize binder for filter model.
     *
     * @param binder
     *            a WebDataBinder example to initialize.
     */
    @InitBinder(DATE_FILTER_MODEL_ATTR)
    protected void initBinderDateFilter(final WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, dateEditor);
    }

    /**
     * Provides room model.
     *
     * @return new RoomDTO object.
     */
    @ModelAttribute(ROOM_MODEL_ATTR)
    public RoomDTO getRoomDTO() {
        return new RoomDTO();
    }

    /**
     * Provides room filter.
     *
     * @return new RoomFilter object.
     */
    @ModelAttribute(FILTER_MODEL_ATTR)
    public RoomFilter getFilter() {
        return new RoomFilter();
    }

    /**
     * Provides pagination object for rooms list page.
     *
     * @return new Paginator object.
     */
    @ModelAttribute(ROOM_PAGINATOR_MODEL_ATTR)
    public Paginator getPaginator() {
        return new Paginator();
    }

    /**
     * Provides date filter for filtering meetings.
     *
     * @return new RoomFilter object.
     */
    @ModelAttribute(DATE_FILTER_MODEL_ATTR)
    public DateFilter getDateFilter() {
        return new DateFilter();
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
            @ModelAttribute(FILTER_MODEL_ATTR) final RoomFilter filter,
            @ModelAttribute(ROOM_PAGINATOR_MODEL_ATTR) final Paginator paginator) {
        model.addAttribute(ROOMS_MODEL_ATTR,
                roomService.getRoomsPageWithFilter(filter, paginator));
        model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.getAll());
        model.addAttribute(EQUIPMENTS_MODEL_ATTR,
                roomEquipmentService.getAll());
        model.addAttribute(FILTER_MODEL_ATTR, filter);
        model.addAttribute("roomPaginator", paginator);
        return ROOMS_LIST_URL;
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
     * @param dateFilter
     *            date filter for page
     *
     * @return room show detail page URL
     */
    @RequestMapping(value = ROOM_SHOW_MAPPING, method = RequestMethod.GET)
    public String showRoom(@PathVariable(PATH_VAR_ID) final Long id,
            final Model model,
            @ModelAttribute(DATE_FILTER_MODEL_ATTR) @Valid final DateFilter dateFilter) {
        model.addAttribute(ROOM_MODEL_ATTR, roomService.getById(id));
        model.addAttribute(MEETINGS_MODEL_ATTR, meetingService
                .getMeetingsByRoomIDAndDate(id, dateFilter.getDate()));
        return ROOM_SHOW_URL;
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
    @RequestMapping(value = ROOM_EDIT_MAPPING, method = RequestMethod.POST)
    public String update(
            @ModelAttribute(ROOM_MODEL_ATTR) @Valid final RoomDTO roomDTO,
            final BindingResult br, final Model model) {
        if (br.hasErrors()) {
            model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.getAll());
            model.addAttribute(EQUIPMENTS_MODEL_ATTR,
                    roomEquipmentService.getAll());
            return ROOM_EDIT_URL;
        }
        roomService.update(roomDTO);
        return ROOMS_REDIRECT_URL;
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
    @RequestMapping(value = ROOM_EDIT_MAPPING, method = RequestMethod.GET)
    public String updateForm(@PathVariable(PATH_VAR_ID) final Long id,
            final Model model) {
        model.addAttribute(ROOM_MODEL_ATTR, roomService.getById(id));
        model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.getAll());
        model.addAttribute(EQUIPMENTS_MODEL_ATTR,
                roomEquipmentService.getAll());
        return ROOM_EDIT_URL;
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
    @RequestMapping(value = ROOM_CREATE_MAPPING, method = RequestMethod.POST)
    public String create(
            @ModelAttribute(ROOM_MODEL_ATTR) @Valid final RoomDTO roomDTO,
            final BindingResult br, final Model model) {
        if (br.hasErrors()) {
            model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.getAll());
            model.addAttribute(EQUIPMENTS_MODEL_ATTR,
                    roomEquipmentService.getAll());
            return ROOM_CREATE_URL;
        }
        roomService.create(roomDTO);
        return ROOMS_REDIRECT_URL;
    }

    /**
     * Controls view of room create page.
     *
     * @param model
     *            room create page view model.
     *
     * @return room create page URL
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
    @RequestMapping(value = ROOM_CREATE_MAPPING, method = RequestMethod.GET)
    public String createForm(final Model model) {
        model.addAttribute(ROOM_MODEL_ATTR, new RoomDTO());
        model.addAttribute(LOCATIONS_MODEL_ATTR, locationService.getAll());
        model.addAttribute(EQUIPMENTS_MODEL_ATTR,
                roomEquipmentService.getAll());
        return ROOM_CREATE_URL;
    }

    /**
     * Controls processing of room delete URL.
     *
     * @param id
     *            an room id to delete from database.
     *
     * @return rooms list page redirect URL
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
    @RequestMapping(value = ROOM_DELETE_MAPPING)
    public String deleteRoom(@PathVariable final Long id) {
        roomService.deleteById(id);
        return ROOMS_REDIRECT_URL;
    }
}
