package com.softserve.edu.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.service.LocationService;

@Controller
public class LocationController {

	@Autowired
	private LocationService locationService;

	@ModelAttribute("search")
    public LocationDTO getLocationDTO() {
        return new LocationDTO();
    }
	
	@RequestMapping("/locations")
	public String showList(Model model) {
		model.addAttribute("locations", locationService.getAll());
		return "locations/list";
	}

	@RequestMapping("/locations/sortbynameasc")
	public String sortByNameAsc(Model model) {
		model.addAttribute("locations", locationService.sortByName(Order.ASC));
		return "locations/list";

	}

	@RequestMapping("/locations/sortbynamedesc")
	public String sortByNameDesc(Model model) {
		model.addAttribute("locations", locationService.sortByName(Order.DESC));
		return "locations/list";

	}

	@RequestMapping("/locations/sortbyaddressasc")
	public String sortByAddressAsc(Model model) {
		model.addAttribute("locations", locationService.sortByAddress(Order.ASC));
		return "locations/list";

	}

	@RequestMapping("/locations/sortbyaddressdesc")
	public String sortByAddressDesc(Model model) {
		model.addAttribute("locations", locationService.sortByAddress(Order.DESC));
		return "locations/list";

	}

	@RequestMapping("/locations/sortbycountroomsasc")
	public String sortByCountRoomsAsc(Model model) {
		model.addAttribute("locations", locationService.sortByCountRooms(Order.ASC));
		return "locations/list";

	}

	@RequestMapping("/locations/sortbycountroomsdesc")
	public String sortByCountRoomsDesc(Model model) {
		model.addAttribute("locations", locationService.sortByCountRooms(Order.DESC));
		return "locations/list";

	}

	@RequestMapping("/locations/delete/{id}")
	public String delete(@PathVariable Long id) {
		locationService.deleteById(id);
		return "redirect:/locations";
	}

	@RequestMapping("/locations/create")
	public String createForm(Model model) {
		model.addAttribute("locationForm", new LocationDTO());
		return "locations/create";
	}

	@RequestMapping(value = "/locations/create", method = RequestMethod.POST)
	public String create(@ModelAttribute("locationForm") LocationDTO location) {
		locationService.create(location);
		return "redirect:/locations";
	}

	@RequestMapping("/locations/edit/{id}")
	public String updateForm(@PathVariable Long id, Model model) {
		model.addAttribute("locationForm", locationService.getById(id));
		return "locations/edit";
	}

	@RequestMapping(value = "/locations/edit/{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute("locationForm") LocationDTO location) {
		locationService.update(location);
		return "redirect:/locations";
	}
	
	@RequestMapping(value = "/locations/searchByName", method = RequestMethod.POST)
	public String searchByName(@ModelAttribute("search") LocationDTO location, Model model) {
		model.addAttribute("locations", locationService.searchByName(location.getName()));
		return "locations/list";
	}
	
	@RequestMapping(value = "/locations/searchByAddress", method = RequestMethod.POST)
	public String searchByAddress(@ModelAttribute("search") LocationDTO location, Model model) {
		model.addAttribute("locations", locationService.searchByAddress(location.getAddress()));
		return "locations/list";
	}
}