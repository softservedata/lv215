/* RoomEquipmentController 1.0 01/15/2017 */
package com.softserve.edu.schedule.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.softserve.edu.schedule.dto.RoomEquipmentDTO;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.RoomEquipmentFilter;
import com.softserve.edu.schedule.service.RoomEquipmentService;

/**
 * A controller class of room equipments pages.
 *
 * @version 1.0 15 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@PreAuthorize(ControllerConst.RoomEquipmentControllerConst.ROOM_EQUIPMENT_EDIT_PERMISSIONS)
@RequestMapping(ControllerConst.RoomEquipmentControllerConst.ROOM_EQUIPMENTS_URL)
@Controller
@SessionAttributes({
        ControllerConst.RoomEquipmentControllerConst.FILTER_MODEL_ATTR,
        ControllerConst.RoomEquipmentControllerConst.PAGINATOR_MODEL_ATTR})
public class RoomEquipmentController
        implements ControllerConst.RoomEquipmentControllerConst {

    /**
     * RoomEquipmentService example to provide room equipments list to the
     * model.
     */
    @Autowired
    private RoomEquipmentService roomEquipmentService;

    /**
     * Provides room equipment model.
     *
     * @return new RoomEquipmentDTO object.
     */
    @ModelAttribute(ROOM_EQUIPMENT_MODEL_ATTR)
    public RoomEquipmentDTO getRoomEquipmentDTO() {
        return new RoomEquipmentDTO();
    }

    /**
     * Provides room equipment filter.
     *
     * @return new RoomFilter object.
     */
    @ModelAttribute(FILTER_MODEL_ATTR)
    public RoomEquipmentFilter getFilter() {
        return new RoomEquipmentFilter();
    }

    /**
     * Provides pagination object for room equipments list page.
     *
     * @return new Paginator object.
     */
    @ModelAttribute(PAGINATOR_MODEL_ATTR)
    public Paginator getPaginator() {
        return new Paginator();
    }

    /**
     * Controls view of room equipments list page.
     *
     * @param model
     *            room equipments list page view model.
     *
     * @param filter
     *            room equipments list page view filter.
     *
     * @param paginator
     *            room equipments list paginator.
     *
     * @return room equipments list page URL
     */
    @RequestMapping(method = RequestMethod.GET)
    public String listRoomEquipments(final Model model,
            @ModelAttribute(FILTER_MODEL_ATTR) final RoomEquipmentFilter filter,
            @ModelAttribute(PAGINATOR_MODEL_ATTR) final Paginator paginator) {
        model.addAttribute(ROOM_EQUIPMENTS_MODEL_ATTR, roomEquipmentService
                .getRoomEquipmentsPageWithFilter(filter, paginator));
        model.addAttribute(FILTER_MODEL_ATTR, filter);
        model.addAttribute(PAGINATOR_MODEL_ATTR, paginator);
        return ROOM_EQUIPMENTS_LIST;
    }

    /**
     * Controls processing of room equipment update information form.
     *
     * @param equipmentDTO
     *            an RoomEquipmentDTO example which is built based on form data.
     *
     * @param br
     *            binding result to check validation errors
     *
     * @param model
     *            room equipments update information page view model.
     *
     * @return room equipments list page redirect URL
     */
    @RequestMapping(value = ROOM_EQUIPMENTS_EDIT_MAPPING,
            method = RequestMethod.POST)
    public String update(
            @ModelAttribute(ROOM_EQUIPMENT_MODEL_ATTR) @Valid final RoomEquipmentDTO equipmentDTO,
            final BindingResult br, final Model model) {
        if (br.hasErrors()) {
            return ROOM_EQUIPMENTS_EDIT_URL;
        }
        roomEquipmentService.update(equipmentDTO);
        return ROOM_EQUIPMENTS_REDIRECT_URL;
    }

    /**
     * Controls view of room equipment update information page.
     *
     * @param id
     *            room equipment id to update information.
     *
     * @param model
     *            room equipment update information page view model.
     *
     * @return room equipment update information page URL
     */
    @RequestMapping(ROOM_EQUIPMENTS_EDIT_MAPPING)
    public String updateForm(@PathVariable final Long id, final Model model) {
        model.addAttribute(ROOM_EQUIPMENT_MODEL_ATTR,
                roomEquipmentService.getById(id));
        return ROOM_EQUIPMENTS_EDIT_URL;
    }

    /**
     * Controls processing of room equipment create form.
     *
     * @param equipmentDTO
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
    @RequestMapping(value = ROOM_EQUIPMENTS_CREATE, method = RequestMethod.POST)
    public String create(
            @ModelAttribute(ROOM_EQUIPMENT_MODEL_ATTR) @Valid final RoomEquipmentDTO equipmentDTO,
            final BindingResult br, final Model model) {
        if (br.hasErrors()) {
            return ROOM_EQUIPMENTS_CREATE_URL;
        }
        roomEquipmentService.create(equipmentDTO);
        return ROOM_EQUIPMENTS_REDIRECT_URL;
    }

    /**
     * Controls view of room equipment create page.
     *
     * @param model
     *            room create page view model.
     *
     * @return room equipment create page URL
     */
    @RequestMapping(ROOM_EQUIPMENTS_CREATE)
    public String createForm(final Model model) {
        model.addAttribute(ROOM_EQUIPMENT_MODEL_ATTR, new RoomEquipmentDTO());
        return ROOM_EQUIPMENTS_CREATE_URL;
    }

    /**
     * Controls processing of room equipment delete URL.
     *
     * @param id
     *            an room equipment id to delete from database.
     *
     * @return room equipments list page redirect URL
     */
    @RequestMapping(ROOM_EQUIPMENTS_DELETE_MAPPING)
    public String delete(@PathVariable final Long id) {
        roomEquipmentService.deleteById(id);
        return ROOM_EQUIPMENTS_REDIRECT_URL;
    }

}
