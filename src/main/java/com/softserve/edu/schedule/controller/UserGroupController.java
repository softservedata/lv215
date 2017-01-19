package com.softserve.edu.schedule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.entity.UserGroup;
import com.softserve.edu.schedule.entity.UserGroup_;
import com.softserve.edu.schedule.service.UserGroupService;
import com.softserve.edu.schedule.service.UserService;

@Controller
@RequestMapping("/usergroups")
public class UserGroupController {

	@Autowired
	private UserGroupService userGroupService;

	@Autowired
	private UserService userService;

	@ModelAttribute("userGroupForm")
	public UserGroup getUserGroup() {
		return new UserGroup();
	}

	@RequestMapping()
	public String showGroupsPage(Model model) {
		List<UserGroup> groups = userGroupService.getAll();
		model.addAttribute("usergroups", groups);
		return "usergroups/list";
	}

	@RequestMapping("/sortbynameasc")
	public String sortByNameAsc(Model model) {
		model.addAttribute("usergroups", userGroupService.sortByFields(UserGroup_.name.getName(), Order.ASC));
		return "usergroups/list";
	}

	@RequestMapping("/sortbynamedesc")
	public String sortByNameDesc(Model model) {
		model.addAttribute("usergroups", userGroupService.sortByFields(UserGroup_.name.getName(), Order.DESC));
		return "usergroups/list";
	}

	@RequestMapping("/sortbylevelasc")
	public String sortByLevelAsc(Model model) {
		model.addAttribute("usergroups", userGroupService.sortByFields(UserGroup_.level.getName(), Order.ASC));
		return "usergroups/list";
	}

	@RequestMapping("/sortbyleveldesc")
	public String sortByLevelDesc(Model model) {
		model.addAttribute("usergroups", userGroupService.sortByFields(UserGroup_.level.getName(), Order.DESC));
		return "usergroups/list";
	}

	@RequestMapping("/sortbymembersasc")
	public String sortByMembersAsc(Model model) {
		model.addAttribute("usergroups", userGroupService.sortByCountMembers(Order.ASC));
		return "usergroups/list";
	}

	@RequestMapping("/sortbymembersdesc")
	public String sortByMembersDesc(Model model) {
		model.addAttribute("usergroups", userGroupService.sortByCountMembers(Order.DESC));
		return "usergroups/list";
	}

	@RequestMapping("/create")
	public String createForm(Model model) {
		model.addAttribute("userGroupForm", new UserGroup());
		return "usergroups/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute("userGroupForm") UserGroup userGroup) {
		userGroupService.create(userGroup);
		return "redirect:/usergroups";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		userGroupService.delete(userGroupService.getById(id));
		return "redirect:/usergroups";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute("userGroupForm") UserGroup userGroup) {
		userGroupService.update(userGroup);
		return "redirect:/usergroups";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("userGroupForm", userGroupService.getById(id));
		return "usergroups/edit";
	}
}
