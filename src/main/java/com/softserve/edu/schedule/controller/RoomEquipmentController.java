package com.softserve.edu.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dto.RoomEquipmentDTO;
import com.softserve.edu.schedule.service.RoomEquipmentService;

@RequestMapping(ControllerConst.ROOM_EQUIPMENTS_URL)
@Controller
public class RoomEquipmentController implements ControllerConst.RoomEquipmentControllerConst{
	
	@Autowired
	private RoomEquipmentService roomEquipmentService;
	
	@RequestMapping(ROOM_EQUIPMENTS_SORT_ASC)
	public String sortByNameASC(Model model){
		model.addAttribute(ROOM_EQUIPMENTS_MODEL_ATTR, roomEquipmentService.sortByName(Order.ASC));
		return ROOM_EQUIPMENTS_LIST;
	}
	
	@RequestMapping(ROOM_EQUIPMENTS_SORT_DESC)
	public String sortByNameDESC(Model model){
		model.addAttribute(ROOM_EQUIPMENTS_MODEL_ATTR, roomEquipmentService.sortByName(Order.DESC));
		return ROOM_EQUIPMENTS_LIST;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllRoomEquipment(Model model){
		model.addAttribute(ROOM_EQUIPMENTS_MODEL_ATTR,
				roomEquipmentService.getAll());
		return ROOM_EQUIPMENTS_LIST;
	}
	
	@RequestMapping(ROOM_EQUIPMENTS_CREATE)
	public String createNewRoomEquipment(Model model){
		model.addAttribute(ROOM_EQUIPMENT_FORM, new RoomEquipmentDTO());
		return ROOM_EQUIPMENTS_CREATE_URL;
	}
	
	@RequestMapping(value =ROOM_EQUIPMENTS_CREATE,
			method = RequestMethod.POST)
	public String create(@ModelAttribute(ROOM_EQUIPMENT_FORM) RoomEquipmentDTO equipment ){
		roomEquipmentService.create(equipment);
		return ROOM_EQUIPMENTS_REDIRECT;
	}
	@RequestMapping(ROOM_EQUIPMENTS_EDIT + "{id}")
	public String updateForm(@PathVariable Long id,Model model){
		model.addAttribute(ROOM_EQUIPMENT_FORM, roomEquipmentService.getById(id));
		return ROOM_EQUIPMENTS_EDIT_URL;
	}
	
	@RequestMapping(value = ROOM_EQUIPMENTS_EDIT+"{id}",
			method = RequestMethod.POST)
	public String update(@ModelAttribute("equipmentForm") RoomEquipmentDTO equipment){
		roomEquipmentService.update(equipment);
		return ROOM_EQUIPMENTS_REDIRECT;
	}
	
	@RequestMapping(ROOM_EQUIPMENTS_DELETE +"{id}")
	public String delete(@PathVariable Long id){
		roomEquipmentService.deleteById(id);
		return ROOM_EQUIPMENTS_REDIRECT;
	}
	

}
