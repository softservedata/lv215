package com.softserve.edu.schedule.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.dto.RoomEquipmentDTO;
import com.softserve.edu.schedule.dto.filter.RoomFilter;
import com.softserve.edu.schedule.service.LocationService;
import com.softserve.edu.schedule.service.RoomEquipmentService;
import com.softserve.edu.schedule.service.RoomService;
import com.softserve.edu.schedule.service.implementation.editor.LocationDTOEditor;
import com.softserve.edu.schedule.service.implementation.editor.RoomEquipmentDTOEditor;

@RequestMapping("/rooms")
@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private RoomEquipmentService roomEquipmentService;

    @InitBinder("room")
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocationDTO.class,
                new LocationDTOEditor(locationService));
        binder.registerCustomEditor(RoomEquipmentDTO.class,
                new RoomEquipmentDTOEditor(roomEquipmentService));
    }

    @ModelAttribute("room")
    public RoomDTO getRoomDTO() {
        return new RoomDTO();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String listRooms(Model model,
            @ModelAttribute("filter") @Valid RoomFilter filter) {
        model.addAttribute("rooms", roomService.getRoomsWithFilter(filter));
        model.addAttribute("locations", locationService.getAll());
        model.addAttribute("equipments", roomEquipmentService.getAll());
        model.addAttribute("filter", filter);
        return "rooms/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showRoom(@PathVariable("id") Long id, Model model) {
        model.addAttribute("room", roomService.getById(id));
        return "rooms/show";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute("room") RoomDTO roomDTO) {
        roomService.update(roomDTO);
        return "redirect:/rooms";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("room", roomService.getById(id));
        model.addAttribute("locations", locationService.getAll());
        model.addAttribute("equipments", roomEquipmentService.getAll());
        return "rooms/edit";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("room") RoomDTO roomDTO, Model model) {
        roomService.create(roomDTO);
        return "redirect:/rooms";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("room", new RoomDTO());
        model.addAttribute("locations", locationService.getAll());
        model.addAttribute("equipments", roomEquipmentService.getAll());
        return "rooms/create";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomService.deleteById(id);
        return "redirect:/rooms";
    }
}
