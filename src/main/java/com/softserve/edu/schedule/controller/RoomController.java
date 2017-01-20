/* RoomController 1.0 01/15/2017 */
package com.softserve.edu.schedule.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.softserve.edu.schedule.dto.filter.RoomFilter;
import com.softserve.edu.schedule.service.LocationService;
import com.softserve.edu.schedule.service.RoomEquipmentService;
import com.softserve.edu.schedule.service.RoomService;
import com.softserve.edu.schedule.service.implementation.editor.LocationDTOEditor;
import com.softserve.edu.schedule.service.implementation.editor.RoomEquipmentDTOEditor;
import com.softserve.edu.schedule.service.implementation.validators.RoomValidator;

/**
 * A controller class of rooms pages.
 *
 * @version 1.0 15 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@RequestMapping("/rooms")
@Controller
@SessionAttributes("roomFilter")
public class RoomController implements ControllerConst {

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
     * Initialize binder for room model.
     *
     * @param binder
     *            a WebDataBinder example to initialize.
     */
    @InitBinder(RoomControllerConst.ROOM_MODEL_ATTR)
    protected void initBinder(final WebDataBinder binder) {
        binder.setValidator(new RoomValidator(roomService));
        binder.registerCustomEditor(LocationDTO.class,
                new LocationDTOEditor(locationService));
        binder.registerCustomEditor(RoomEquipmentDTO.class,
                new RoomEquipmentDTOEditor(roomEquipmentService));
    }

    /**
     * Initialize binder for filter model.
     *
     * @param binder
     *            a WebDataBinder example to initialize.
     */
    @InitBinder(RoomControllerConst.FILTER_MODEL_ATTR)
    protected void initBinderFilter(final WebDataBinder binder) {
        binder.registerCustomEditor(RoomEquipmentDTO.class,
                new RoomEquipmentDTOEditor(roomEquipmentService));
    }

    /**
     * Provides room model.
     * 
     * @return new RoomDTO object.
     */
    @ModelAttribute(RoomControllerConst.ROOM_MODEL_ATTR)
    public RoomDTO getRoomDTO() {
        return new RoomDTO();
    }

    /**
     * Provides room filter.
     * 
     * @return new RoomFilter object.
     */
    @ModelAttribute(RoomControllerConst.FILTER_MODEL_ATTR)
    public RoomFilter getFilter() {
        return new RoomFilter();
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
     * @return rooms list page URL
     */
    @RequestMapping(method = RequestMethod.GET)
    public String listRooms(final Model model,
            @ModelAttribute(RoomControllerConst.FILTER_MODEL_ATTR) @Valid final RoomFilter filter) {
        model.addAttribute(RoomControllerConst.ROOMS_MODEL_ATTR,
                roomService.getRoomsWithFilter(filter));
        model.addAttribute(RoomControllerConst.LOCATIONS_MODEL_ATTR,
                locationService.getAll());
        model.addAttribute(RoomControllerConst.EQUIPMENTS_MODEL_ATTR,
                roomEquipmentService.getAll());
        model.addAttribute(RoomControllerConst.FILTER_MODEL_ATTR, filter);
        return RoomControllerConst.ROOMS_LIST_URL;
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
    @RequestMapping(value = RoomControllerConst.ROOM_SHOW_MAPPING,
            method = RequestMethod.GET)
    public String showRoom(
            @PathVariable(RoomControllerConst.PATH_VAR_ID) final Long id,
            final Model model) {
        model.addAttribute(RoomControllerConst.ROOM_MODEL_ATTR,
                roomService.getById(id));
        return RoomControllerConst.ROOM_SHOW_URL;
    }

    /**
     * Controls processing of room update information form.
     *
     * @param roomDTO
     *            an RoomDTO example which is built based on form data.
     *
     * @return rooms list page redirect URL
     */
    @RequestMapping(value = RoomControllerConst.ROOM_EDIT_MAPPING,
            method = RequestMethod.POST)
    public String update(
            @ModelAttribute(RoomControllerConst.ROOM_MODEL_ATTR) @Valid final RoomDTO roomDTO,
            BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute(RoomControllerConst.LOCATIONS_MODEL_ATTR,
                    locationService.getAll());
            model.addAttribute(RoomControllerConst.EQUIPMENTS_MODEL_ATTR,
                    roomEquipmentService.getAll());
            return RoomControllerConst.ROOM_EDIT_URL;
        }
        roomService.update(roomDTO);
        return RoomControllerConst.ROOMS_REDIRECT_URL;
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
    @RequestMapping(value = RoomControllerConst.ROOM_EDIT_MAPPING,
            method = RequestMethod.GET)
    public String updateForm(
            @PathVariable(RoomControllerConst.PATH_VAR_ID) final Long id,
            final Model model) {
        model.addAttribute(RoomControllerConst.ROOM_MODEL_ATTR,
                roomService.getById(id));
        model.addAttribute(RoomControllerConst.LOCATIONS_MODEL_ATTR,
                locationService.getAll());
        model.addAttribute(RoomControllerConst.EQUIPMENTS_MODEL_ATTR,
                roomEquipmentService.getAll());
        return RoomControllerConst.ROOM_EDIT_URL;
    }

    /**
     * Controls processing of room create form.
     *
     * @param roomDTO
     *            an RoomDTO example which is built based on form data.
     *
     * @return rooms list page redirect URL
     */
    @RequestMapping(value = RoomControllerConst.ROOM_CREATE_MAPPING,
            method = RequestMethod.POST)
    public String create(
            @ModelAttribute(RoomControllerConst.ROOM_MODEL_ATTR) @Valid final RoomDTO roomDTO,
            BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute(RoomControllerConst.LOCATIONS_MODEL_ATTR,
                    locationService.getAll());
            model.addAttribute(RoomControllerConst.EQUIPMENTS_MODEL_ATTR,
                    roomEquipmentService.getAll());
            return RoomControllerConst.ROOM_CREATE_URL;
        }
        roomService.create(roomDTO);
        return RoomControllerConst.ROOMS_REDIRECT_URL;
    }

    /**
     * Controls view of room create page.
     * 
     * @param model
     *            room create page view model.
     * 
     * @return room create page URL
     */
    @RequestMapping(value = RoomControllerConst.ROOM_CREATE_MAPPING,
            method = RequestMethod.GET)
    public String createForm(final Model model) {
        model.addAttribute(RoomControllerConst.ROOM_MODEL_ATTR, new RoomDTO());
        model.addAttribute(RoomControllerConst.LOCATIONS_MODEL_ATTR,
                locationService.getAll());
        model.addAttribute(RoomControllerConst.EQUIPMENTS_MODEL_ATTR,
                roomEquipmentService.getAll());
        return RoomControllerConst.ROOM_CREATE_URL;
    }

    /**
     * Controls processing of room delete URL.
     *
     * @param id
     *            an room id to delete from database.
     *
     * @return rooms list page redirect URL
     */
    @RequestMapping(value = RoomControllerConst.ROOM_DELETE_MAPPING)
    public String deleteRoom(@PathVariable final Long id) {
        roomService.deleteById(id);
        return RoomControllerConst.ROOMS_REDIRECT_URL;
    }
}
