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
import com.softserve.edu.schedule.entity.Location_;
import com.softserve.edu.schedule.service.LocationService;

@RequestMapping("/locations")
@Controller
public class LocationController {

	@Autowired
	private LocationService locationService;

	@ModelAttribute("search")
    public LocationDTO getLocationDTO() {
        return new LocationDTO();
    }
	
	@RequestMapping()
	public String showList(Model model) {
		model.addAttribute("locations", locationService.getAllWithDetails());
		return "locations/list";
	}

	@RequestMapping("/sortbynameasc")
	public String sortByNameAsc(Model model) {
		model.addAttribute("locations", locationService.sortByFields(Location_.name.getName(), Order.ASC));
		return "locations/list";

	}

	@RequestMapping("/sortbynamedesc")
	public String sortByNameDesc(Model model) {
		model.addAttribute("locations", locationService.sortByFields(Location_.name.getName(), Order.DESC));
		return "locations/list";

	}

	@RequestMapping("/sortbyaddressasc")
	public String sortByAddressAsc(Model model) {
		model.addAttribute("locations", locationService.sortByFields(Location_.address.getName(), Order.ASC));
		return "locations/list";

	}

	@RequestMapping("/sortbyaddressdesc")
	public String sortByAddressDesc(Model model) {
		model.addAttribute("locations", locationService.sortByFields(Location_.address.getName(), Order.DESC));
		return "locations/list";

	}

	@RequestMapping("/sortbycountroomsasc")
	public String sortByCountRoomsAsc(Model model) {
		model.addAttribute("locations", locationService.sortByCountRooms(Order.ASC));
		return "locations/list";

	}

	@RequestMapping("/sortbycountroomsdesc")
	public String sortByCountRoomsDesc(Model model) {
		model.addAttribute("locations", locationService.sortByCountRooms(Order.DESC));
		return "locations/list";

	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		locationService.deleteById(id);
		return "redirect:/locations";
	}

	@RequestMapping("/create")
	public String createForm(Model model) {
		model.addAttribute("locationForm", new LocationDTO());
		return "locations/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute("locationForm") LocationDTO location) {
		locationService.create(location);
		return "redirect:/locations";
	}

	@RequestMapping("/edit/{id}")
	public String updateForm(@PathVariable Long id, Model model) {
		model.addAttribute("locationForm", locationService.getById(id));
		return "locations/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute("locationForm") LocationDTO location) {
		locationService.update(location);
		return "redirect:/locations";
	}
	
	@RequestMapping(value = "/searchByName", method = RequestMethod.POST)
	public String searchByName(@ModelAttribute("search") LocationDTO location, Model model) {
		model.addAttribute("locations", locationService.search(Location_.name.getName(), location.getName()));
		return "locations/list";
	}
	
	@RequestMapping(value = "/searchByAddress", method = RequestMethod.POST)
	public String searchByAddress(@ModelAttribute("search") LocationDTO location, Model model) {
		model.addAttribute("locations", locationService.search(Location_.address.getName(), location.getAddress()));
		return "locations/list";
	}
}