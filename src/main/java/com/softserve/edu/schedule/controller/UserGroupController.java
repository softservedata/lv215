package com.softserve.edu.schedule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserGroup;
import com.softserve.edu.schedule.entity.UserGroup_;
import com.softserve.edu.schedule.service.UserGroupService;
import com.softserve.edu.schedule.service.UserService;
import com.softserve.edu.schedule.service.implementation.editor.UserEditor;
import com.softserve.edu.schedule.service.implementation.editor.UserGroupEditor;

/**
 * Controller class for a UserGroup
 * 
 * @author Andrew
 *
 */
@Controller
@RequestMapping("/usergroups")
public class UserGroupController implements ControllerConst {

	@Autowired
	private UserGroupEditor userGroupEditor;

	@Autowired
	private UserEditor userEditor;

	/**
	 * An instance of a service for a UserGroup
	 */
	@Autowired
	private UserGroupService userGroupService;

	/**
	 * An instance of a service for a User
	 */
	@Autowired
	private UserService userService;

	/**
	 * Creates an instance of a new UserGroup
	 * 
	 * @return New instance of a UserGroup
	 */
	@ModelAttribute(UserGroupControllerConst.USERGROUP_MODEL_ATTR)
	public UserGroup getUserGroup() {
		return new UserGroup();
	}

	/**
	 * Binder for userGroup model.
	 * 
	 * @param binder
	 *            WebDataBinder object
	 */
	@InitBinder(UserGroupControllerConst.USERGROUP_MODEL_ATTR)
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(UserGroup.class, userGroupEditor);
		binder.registerCustomEditor(User.class, userEditor);
	}

	/**
	 * Shows view with all UserGroups
	 * 
	 * @param model
	 *            Container for an attributes
	 * @return URL of a page that shows all UserGroups
	 */
	@RequestMapping()
	public String showGroupsPage(final Model model) {
		List<UserGroup> groups = userGroupService.getAll();
		model.addAttribute(UserGroupControllerConst.USERGROUPS_MODEL_ATTR, groups);
		return UserGroupControllerConst.USERGROUP_LIST_URL;
	}

	/**
	 * Shows view with all UserGroups sorted by name in ascending order
	 * 
	 * @param model
	 *            Container for an attributes
	 * @return URL of a page that shows all UserGroups sorted by name in
	 *         ascending order
	 */
	@RequestMapping("/sortbynameasc")
	public String sortByNameAsc(final Model model) {
		model.addAttribute(UserGroupControllerConst.USERGROUPS_MODEL_ATTR,
				userGroupService.sortByFields(UserGroup_.name.getName(), Order.ASC));
		return UserGroupControllerConst.USERGROUP_LIST_URL;
	}

	/**
	 * Shows view with all UserGroups sorted by name in descending order
	 * 
	 * @param model
	 *            Container for an attributes
	 * @return URL of a page that shows all UserGroups sorted by name in
	 *         descending order
	 */
	@RequestMapping("/sortbynamedesc")
	public String sortByNameDesc(final Model model) {
		model.addAttribute(UserGroupControllerConst.USERGROUPS_MODEL_ATTR,
				userGroupService.sortByFields(UserGroup_.name.getName(), Order.DESC));
		return UserGroupControllerConst.USERGROUP_LIST_URL;
	}

	/**
	 * Shows view with all UserGroups sorted by level in ascending order
	 * 
	 * @param model
	 *            Container for an attributes
	 * @return URL of a page that shows all UserGroups sorted by level in
	 *         ascending order
	 */
	@RequestMapping("/sortbylevelasc")
	public String sortByLevelAsc(final Model model) {
		model.addAttribute(UserGroupControllerConst.USERGROUPS_MODEL_ATTR,
				userGroupService.sortByFields(UserGroup_.level.getName(), Order.ASC));
		return UserGroupControllerConst.USERGROUP_LIST_URL;
	}

	/**
	 * Shows view with all UserGroups sorted by level in descending order
	 * 
	 * @param model
	 *            Container for an attributes
	 * @return URL of a page that shows all UserGroups sorted by level in
	 *         descending order
	 */
	@RequestMapping("/sortbyleveldesc")
	public String sortByLevelDesc(final Model model) {
		model.addAttribute(UserGroupControllerConst.USERGROUPS_MODEL_ATTR,
				userGroupService.sortByFields(UserGroup_.level.getName(), Order.DESC));
		return UserGroupControllerConst.USERGROUP_LIST_URL;
	}

	/**
	 * Shows view with all UserGroups sorted by count of members in ascending
	 * order
	 * 
	 * @param model
	 *            Container for an attributes
	 * @return URL of a page that shows all UserGroups sorted by level in
	 *         ascending order
	 */
	@RequestMapping("/sortbymembersasc")
	public String sortByMembersAsc(final Model model) {
		model.addAttribute(UserGroupControllerConst.USERGROUPS_MODEL_ATTR,
				userGroupService.sortByCountMembers(Order.ASC));
		return UserGroupControllerConst.USERGROUP_LIST_URL;
	}

	/**
	 * Shows view with all UserGroups sorted by count of members in descending
	 * order
	 * 
	 * @param model
	 *            Container for an attributes
	 * @return URL of a page that shows all UserGroups sorted by level in
	 *         descending order
	 */
	@RequestMapping("/sortbymembersdesc")
	public String sortByMembersDesc(final Model model) {
		model.addAttribute(UserGroupControllerConst.USERGROUPS_MODEL_ATTR,
				userGroupService.sortByCountMembers(Order.DESC));
		return UserGroupControllerConst.USERGROUP_LIST_URL;
	}

	/**
	 * Redirects to a creation view previously put attributes in a model
	 * 
	 * @param model
	 *            Container for an attributes
	 * @return URL of a page with a form for creation a new group
	 */
	@RequestMapping(UserGroupControllerConst.USERGROUP_CREATE_MAPPING)
	public String createForm(final Model model) {
		model.addAttribute(UserGroupControllerConst.USERGROUP_MODEL_ATTR, new UserGroup());
		model.addAttribute(UserGroup_.users.getName(), userService.getAll());
		return UserGroupControllerConst.USERGROUP_CREATE_URL;
	}

	/**
	 * Creates a new UserGroup with a curator in it
	 * 
	 * @param userGroup
	 *            A new UserGroup that will be created
	 * @return URL of a page that shows all UserGroups
	 */
	@RequestMapping(value = UserGroupControllerConst.USERGROUP_CREATE_MAPPING, method = RequestMethod.POST)
	public String create(@ModelAttribute(UserGroupControllerConst.USERGROUP_MODEL_ATTR) UserGroup userGroup) {
		userGroupService.create(userGroup);
		userGroupService.addUserToGroup(userGroup.getCurator(), userGroup.getId());
		return UserGroupControllerConst.USERGROUP_REDIRECT_URL;
	}

	/**
	 * Deletes a UserGroup(only when it has no members)
	 * 
	 * @param userGroup
	 *            A UserGroup that will be deleted
	 * @return URL of a page that shows all UserGroups
	 */
	@RequestMapping(UserGroupControllerConst.USERGROUP_DELETE_MAPPING)
	public String delete(@PathVariable final Long id) {
		userGroupService.delete(userGroupService.getById(id));
		return UserGroupControllerConst.USERGROUP_REDIRECT_URL;
	}

	/**
	 * Updates a UserGroup
	 * 
	 * @param userGroup
	 *            A UserGroup that will be updated
	 * @return URL of a page that shows all UserGroups
	 */
	@RequestMapping(value = UserGroupControllerConst.USERGROUP_EDIT_MAPPING, method = RequestMethod.POST)
	public String update(@ModelAttribute(UserGroupControllerConst.USERGROUP_MODEL_ATTR) final UserGroup userGroup) {
		userGroupService.update(userGroup);
		return UserGroupControllerConst.USERGROUP_REDIRECT_URL;
	}

	/**
	 * Sends a model with a UserGroup details to a form
	 * 
	 * @param id
	 *            An id of a group that we need to edit
	 * @param model
	 *            Container for an attributes
	 * @return URL of a page that shows edit form
	 */
	@RequestMapping(value = UserGroupControllerConst.USERGROUP_EDIT_MAPPING, method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") final Long id, final Model model) {
		model.addAttribute(UserGroupControllerConst.USERGROUP_MODEL_ATTR, userGroupService.getById(id));
		model.addAttribute("curators", userService.getAll());
		return UserGroupControllerConst.USERGROUP_EDIT_URL;
	}
}
