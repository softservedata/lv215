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

import com.softserve.edu.schedule.controller.constants.RoomEqContrConst;
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
@PreAuthorize(RoomEqContrConst.ROOM_EQUIPMENT_EDIT_PERMISSIONS)
@RequestMapping(RoomEqContrConst.ROOM_EQUIPMENTS_URL)
@Controller
@SessionAttributes({RoomEqContrConst.FILTER_MODEL_ATTR,
        RoomEqContrConst.PAGINATOR_MODEL_ATTR})
public class RoomEquipmentController {

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
    @ModelAttribute(RoomEqContrConst.ROOM_EQUIPMENT_MODEL_ATTR)
    public RoomEquipmentDTO getRoomEquipmentDTO() {
        return new RoomEquipmentDTO();
    }

    /**
     * Provides room equipment filter.
     *
     * @return new RoomFilter object.
     */
    @ModelAttribute(RoomEqContrConst.FILTER_MODEL_ATTR)
    public RoomEquipmentFilter getFilter() {
        return new RoomEquipmentFilter();
    }

    /**
     * Provides pagination object for room equipments list page.
     *
     * @return new Paginator object.
     */
    @ModelAttribute(RoomEqContrConst.PAGINATOR_MODEL_ATTR)
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
            @ModelAttribute(RoomEqContrConst.FILTER_MODEL_ATTR) final RoomEquipmentFilter filter,
            @ModelAttribute(RoomEqContrConst.PAGINATOR_MODEL_ATTR) final Paginator paginator) {
        model.addAttribute(RoomEqContrConst.ROOM_EQUIPMENTS_MODEL_ATTR,
                roomEquipmentService.getRoomEquipmentsPageWithFilter(filter,
                        paginator));
        model.addAttribute(RoomEqContrConst.FILTER_MODEL_ATTR, filter);
        model.addAttribute(RoomEqContrConst.PAGINATOR_MODEL_ATTR, paginator);
        return RoomEqContrConst.ROOM_EQUIPMENTS_LIST;
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
    @RequestMapping(value = RoomEqContrConst.ROOM_EQUIPMENTS_EDIT_MAPPING,
            method = RequestMethod.POST)
    public String update(
            @ModelAttribute(RoomEqContrConst.ROOM_EQUIPMENT_MODEL_ATTR) @Valid final RoomEquipmentDTO equipmentDTO,
            final BindingResult br, final Model model) {
        if (br.hasErrors()) {
            return RoomEqContrConst.ROOM_EQUIPMENTS_EDIT_URL;
        }
        roomEquipmentService.update(equipmentDTO);
        return RoomEqContrConst.ROOM_EQUIPMENTS_REDIRECT_URL;
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
    @RequestMapping(RoomEqContrConst.ROOM_EQUIPMENTS_EDIT_MAPPING)
    public String updateForm(@PathVariable final Long id, final Model model) {
        model.addAttribute(RoomEqContrConst.ROOM_EQUIPMENT_MODEL_ATTR,
                roomEquipmentService.getById(id));
        return RoomEqContrConst.ROOM_EQUIPMENTS_EDIT_URL;
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
    @RequestMapping(value = RoomEqContrConst.ROOM_EQUIPMENTS_CREATE,
            method = RequestMethod.POST)
    public String create(
            @ModelAttribute(RoomEqContrConst.ROOM_EQUIPMENT_MODEL_ATTR) @Valid final RoomEquipmentDTO equipmentDTO,
            final BindingResult br, final Model model) {
        if (br.hasErrors()) {
            return RoomEqContrConst.ROOM_EQUIPMENTS_CREATE_URL;
        }
        roomEquipmentService.create(equipmentDTO);
        return RoomEqContrConst.ROOM_EQUIPMENTS_REDIRECT_URL;
    }

    /**
     * Controls view of room equipment create page.
     *
     * @param model
     *            room create page view model.
     *
     * @return room equipment create page URL
     */
    @RequestMapping(RoomEqContrConst.ROOM_EQUIPMENTS_CREATE)
    public String createForm(final Model model) {
        model.addAttribute(RoomEqContrConst.ROOM_EQUIPMENT_MODEL_ATTR,
                new RoomEquipmentDTO());
        return RoomEqContrConst.ROOM_EQUIPMENTS_CREATE_URL;
    }

    /**
     * Controls processing of room equipment delete URL.
     *
     * @param id
     *            an room equipment id to delete from database.
     *
     * @return room equipments list page redirect URL
     */
    @RequestMapping(RoomEqContrConst.ROOM_EQUIPMENTS_DELETE_MAPPING)
    public String delete(@PathVariable final Long id) {
        roomEquipmentService.deleteById(id);
        return RoomEqContrConst.ROOM_EQUIPMENTS_REDIRECT_URL;
    }

}
