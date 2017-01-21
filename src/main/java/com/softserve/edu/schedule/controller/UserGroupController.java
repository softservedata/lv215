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

@Controller
@RequestMapping("/usergroups")
public class UserGroupController implements ControllerConst {

	@Autowired
	private UserGroupService userGroupService;

	@ModelAttribute(UserGroupControllerConst.USERGROUP_MODEL_ATTR)
	public UserGroup getUserGroup() {
		return new UserGroup();
	}

	@RequestMapping()
	public String showGroupsPage(final Model model) {
		List<UserGroup> groups = userGroupService.getAll();
		model.addAttribute(UserGroupControllerConst.USERGROUPS_MODEL_ATTR, groups);
		return UserGroupControllerConst.USERGROUP_LIST_URL;
	}

	@RequestMapping("/sortbynameasc")
	public String sortByNameAsc(final Model model) {
		model.addAttribute(UserGroupControllerConst.USERGROUPS_MODEL_ATTR,
				userGroupService.sortByFields(UserGroup_.name.getName(), Order.ASC));
		return UserGroupControllerConst.USERGROUP_LIST_URL;
	}

	@RequestMapping("/sortbynamedesc")
	public String sortByNameDesc(final Model model) {
		model.addAttribute(UserGroupControllerConst.USERGROUPS_MODEL_ATTR,
				userGroupService.sortByFields(UserGroup_.name.getName(), Order.DESC));
		return UserGroupControllerConst.USERGROUP_LIST_URL;
	}

	@RequestMapping("/sortbylevelasc")
	public String sortByLevelAsc(final Model model) {
		model.addAttribute(UserGroupControllerConst.USERGROUPS_MODEL_ATTR,
				userGroupService.sortByFields(UserGroup_.level.getName(), Order.ASC));
		return UserGroupControllerConst.USERGROUP_LIST_URL;
	}

	@RequestMapping("/sortbyleveldesc")
	public String sortByLevelDesc(final Model model) {
		model.addAttribute(UserGroupControllerConst.USERGROUPS_MODEL_ATTR,
				userGroupService.sortByFields(UserGroup_.level.getName(), Order.DESC));
		return UserGroupControllerConst.USERGROUP_LIST_URL;
	}

	@RequestMapping("/sortbymembersasc")
	public String sortByMembersAsc(final Model model) {
		model.addAttribute(UserGroupControllerConst.USERGROUPS_MODEL_ATTR,
				userGroupService.sortByCountMembers(Order.ASC));
		return UserGroupControllerConst.USERGROUP_LIST_URL;
	}

	@RequestMapping("/sortbymembersdesc")
	public String sortByMembersDesc(final Model model) {
		model.addAttribute(UserGroupControllerConst.USERGROUPS_MODEL_ATTR,
				userGroupService.sortByCountMembers(Order.DESC));
		return UserGroupControllerConst.USERGROUP_LIST_URL;
	}

	@RequestMapping(UserGroupControllerConst.USERGROUP_CREATE_MAPPING)
	public String createForm(final Model model) {
		model.addAttribute(UserGroupControllerConst.USERGROUP_MODEL_ATTR, new UserGroup());
		return UserGroupControllerConst.USERGROUP_CREATE_URL;
	}

	@RequestMapping(value = UserGroupControllerConst.USERGROUP_CREATE_MAPPING, method = RequestMethod.POST)
	public String create(@ModelAttribute(UserGroupControllerConst.USERGROUP_MODEL_ATTR) UserGroup userGroup) {
		userGroupService.create(userGroup);
		return UserGroupControllerConst.USERGROUP_REDIRECT_URL;
	}

	@RequestMapping(UserGroupControllerConst.USERGROUP_DELETE_MAPPING)
	public String delete(@PathVariable final Long id) {
		userGroupService.delete(userGroupService.getById(id));
		return UserGroupControllerConst.USERGROUP_REDIRECT_URL;
	}

	@RequestMapping(value = UserGroupControllerConst.USERGROUP_EDIT_MAPPING, method = RequestMethod.POST)
	public String update(@ModelAttribute(UserGroupControllerConst.USERGROUP_MODEL_ATTR) final UserGroup userGroup) {
		userGroupService.update(userGroup);
		return UserGroupControllerConst.USERGROUP_REDIRECT_URL;
	}

	@RequestMapping(value = UserGroupControllerConst.USERGROUP_EDIT_MAPPING, method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") final Long id, final Model model) {
		model.addAttribute(UserGroupControllerConst.USERGROUP_MODEL_ATTR, userGroupService.getById(id));
		return UserGroupControllerConst.USERGROUP_EDIT_URL;
	}
}
