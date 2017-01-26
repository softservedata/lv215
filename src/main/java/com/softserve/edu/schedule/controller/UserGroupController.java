package com.softserve.edu.schedule.controller;

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
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserGroupDTO;
import com.softserve.edu.schedule.entity.UserGroup_;
import com.softserve.edu.schedule.service.UserGroupService;
import com.softserve.edu.schedule.service.UserService;
import com.softserve.edu.schedule.service.implementation.editor.UserDTOEditor;
import com.softserve.edu.schedule.service.implementation.editor.UserGroupDTOEditor;

/**
 * Controller class for a UserGroup
 * 
 * @author Andrew
 *
 */
@Controller
@RequestMapping("/usergroups")
public class UserGroupController implements ControllerConst.UserGroupControllerConst {

	/**
	 * Editor for a userGroup
	 */
	@Autowired
	private UserGroupDTOEditor userGroupDTOEditor;

	/**
	 * Editor for a user
	 */
	@Autowired
	private UserDTOEditor userDTOEditor;

	/**
	 * Service for a UserGroup
	 */
	@Autowired
	private UserGroupService userGroupService;

	/**
	 * Service for a User
	 */
	@Autowired
	private UserService userService;

	/**
	 * Creates an instance of a new UserGroupDTO.
	 *
	 * @return New instance of a UserGroupDTO
	 */
	@ModelAttribute(SEARCH_MODEL_ATTR)
	public UserGroupDTO getUserGroup() {
		return new UserGroupDTO();
	}

	/**
	 * Returns clear UserGroup DTO for writing searching by name results.
	 * 
	 * @return UserGroup DTO object
	 */
	@ModelAttribute("searchByName")
	public UserGroupDTO searchGroupByName() {
		return new UserGroupDTO();
	}

	/**
	 * Returns clear UserGroup DTO for writing searching by curator results.
	 * 
	 * @return UserGroup DTO object
	 */
	@ModelAttribute("searchByCurator")
	public UserGroupDTO searchGroupByCurator() {
		return new UserGroupDTO();
	}

	/**
	 * Binder for userGroup model.
	 * 
	 * @param binder
	 *            WebDataBinder object
	 */
	@InitBinder(USERGROUP_MODEL_ATTR)
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(UserDTO.class, userDTOEditor);
		binder.registerCustomEditor(UserGroupDTO.class, userGroupDTOEditor);
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
		model.addAttribute(USERGROUPS_MODEL_ATTR, userGroupService.getAll());
		return USERGROUP_LIST_URL;
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
		model.addAttribute(USERGROUPS_MODEL_ATTR, userGroupService.sortByFields(UserGroup_.name.getName(), Order.ASC));
		return USERGROUP_LIST_URL;
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
		model.addAttribute(USERGROUPS_MODEL_ATTR, userGroupService.sortByFields(UserGroup_.name.getName(), Order.DESC));
		return USERGROUP_LIST_URL;
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
		model.addAttribute(USERGROUPS_MODEL_ATTR, userGroupService.sortByFields(UserGroup_.level.getName(), Order.ASC));
		return USERGROUP_LIST_URL;
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
		model.addAttribute(USERGROUPS_MODEL_ATTR,
				userGroupService.sortByFields(UserGroup_.level.getName(), Order.DESC));
		return USERGROUP_LIST_URL;
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
		model.addAttribute(USERGROUPS_MODEL_ATTR, userGroupService.sortByCountMembers(Order.ASC));
		return USERGROUP_LIST_URL;
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
		model.addAttribute(USERGROUPS_MODEL_ATTR, userGroupService.sortByCountMembers(Order.DESC));
		return USERGROUP_LIST_URL;
	}

	/**
	 * Redirects to a creation view previously put attributes in a model
	 * 
	 * @param model
	 *            Container for an attributes
	 * @return URL of a page with a form for creation a new group
	 */
	@RequestMapping(USERGROUP_CREATE_MAPPING)
	public String createForm(final Model model) {
		model.addAttribute(USERGROUP_MODEL_ATTR, new UserGroupDTO());
		model.addAttribute(USERGROUP_CURATORS_ATTR, userService.getAll());
		model.addAttribute(USERGROUP_ALL_USERS_ATTR, userService.getAll());
		return USERGROUP_CREATE_URL;
	}

	/**
	 * Creates a new UserGroup with a curator in it
	 * 
	 * @param userGroup
	 *            A new UserGroup that will be created
	 * @return URL of a page that shows all UserGroups
	 */
	@RequestMapping(value = USERGROUP_CREATE_MAPPING, method = RequestMethod.POST)
	public String create(@ModelAttribute(USERGROUP_MODEL_ATTR) UserGroupDTO userGroupDTO) {
		userGroupService.create(userGroupService.addUserToGroup(userGroupDTO.getCurator(), userGroupDTO));
		return USERGROUP_REDIRECT_URL;
	}

	/**
	 * Deletes a UserGroup(only when it has no members)
	 * 
	 * @param userGroup
	 *            A UserGroup that will be deleted
	 * @return URL of a page that shows all UserGroups
	 */
	@RequestMapping(USERGROUP_DELETE_MAPPING)
	public String delete(@PathVariable final Long id) {
		userGroupService.delete(userGroupService.getById(id));
		return USERGROUP_REDIRECT_URL;
	}

	/**
	 * Updates a UserGroup
	 * 
	 * @param userGroup
	 *            A UserGroup that will be updated
	 * @return URL of a page that shows all UserGroups
	 */
	@RequestMapping(value = USERGROUP_EDIT_MAPPING, method = RequestMethod.POST)
	public String update(@ModelAttribute(USERGROUP_MODEL_ATTR) final UserGroupDTO userGroupDTO) {
		userGroupService.addUserToGroup(userGroupDTO.getCurator(), userGroupDTO);
		userGroupService.update(userGroupDTO);

		return USERGROUP_REDIRECT_URL;
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
	@RequestMapping(value = USERGROUP_EDIT_MAPPING, method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") final Long id, final Model model) {
		model.addAttribute(USERGROUP_MODEL_ATTR, userGroupService.getById(id));
		model.addAttribute(USERGROUP_CURATORS_ATTR, userService.getAll());
		model.addAttribute(USERGROUP_ALL_USERS_ATTR, userService.getAll());
		return USERGROUP_EDIT_URL;
	}

	/**
	 * Mapped method for searching by name.
	 * 
	 * @param userGroupDTO
	 *            Object with filled field
	 * @param model
	 *            Model with attributes
	 * @return List page url
	 */
	@RequestMapping(value = "/searchByName", method = RequestMethod.POST)
	public String searchByName(@ModelAttribute(SEARCH_MODEL_ATTR) UserGroupDTO userGroupDTO, Model model) {
		model.addAttribute(USERGROUPS_MODEL_ATTR, userGroupService.searchByName(userGroupDTO.getName()));
		return USERGROUP_LIST_URL;
	}

	// TODO
	/**
	 * Mapped method for searching by curator.
	 * 
	 * @param userGroupDTO
	 *            Object with filled field
	 * @param model
	 *            Model with attributes
	 * @return List page url
	 */
	@RequestMapping(value = "/searchByCurator", method = RequestMethod.POST)
	public String searchByCurator(@ModelAttribute(SEARCH_MODEL_ATTR) UserGroupDTO userGroupDTO, Model model) {
		model.addAttribute(USERGROUPS_MODEL_ATTR,
				userGroupService.searchGroupsByCurators(userGroupDTO.getCurator().getLastName()));
		return USERGROUP_LIST_URL;
	}
}
