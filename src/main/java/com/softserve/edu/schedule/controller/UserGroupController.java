package com.softserve.edu.schedule.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserGroupDTO;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.UserGroupFilter;
import com.softserve.edu.schedule.entity.UserGroupLevel;
import com.softserve.edu.schedule.service.UserGroupService;
import com.softserve.edu.schedule.service.UserService;
import com.softserve.edu.schedule.service.implementation.editor.UserDTOEditor;
import com.softserve.edu.schedule.service.implementation.editor.UserGroupDTOEditor;

/**
 * Controller class for a UserGroup
 * 
 * @author Andrew Zhidenko
 *
 */
@Controller
@RequestMapping(ControllerConst.UserGroupControllerConst.USERGROUP_MAPPING)
@SessionAttributes({ ControllerConst.UserGroupControllerConst.FILTER_MODEL_ATTR,
		ControllerConst.UserGroupControllerConst.USERGROUP_PAGINATOR_MODEL_ATTR })
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
	 * Binder filter for a userGroup model.
	 * 
	 * @param binder
	 *            Binder to register editors.
	 */
	@InitBinder(USERGROUP_MODEL_ATTR)
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, null, new CustomNumberEditor(Integer.class, true));
		binder.registerCustomEditor(UserDTO.class, userDTOEditor);
		binder.registerCustomEditor(UserGroupDTO.class, userGroupDTOEditor);
	}

	/**
	 * Binder filter for a userGroupFilter
	 * 
	 * @param binder
	 *            Binder to register editors.
	 */
	@InitBinder(FILTER_MODEL_ATTR)
	protected void initBinderFilter(final WebDataBinder binder) {
		binder.registerCustomEditor(UserDTO.class, userDTOEditor);
		binder.registerCustomEditor(Integer.class, null, new CustomNumberEditor(Integer.class, true));
	}

	/**
	 * UserGroup
	 * 
	 * @return
	 */
	@ModelAttribute(FILTER_MODEL_ATTR)
	public UserGroupFilter getFilter() {
		return new UserGroupFilter();
	}

	@ModelAttribute(USERGROUP_PAGINATOR_MODEL_ATTR)
	public Paginator getPaginator() {
		return new Paginator();
	}

	/**
	 * Shows view with all UserGroups
	 * 
	 * @param model
	 *            Container for an attributes
	 * @return URL of a page that shows all UserGroups
	 */
	@RequestMapping()
	public String showGroupsPage(final Model model,
			@ModelAttribute(FILTER_MODEL_ATTR) final UserGroupFilter userGroupFilter,
			@ModelAttribute(USERGROUP_PAGINATOR_MODEL_ATTR) final Paginator paginator) {
		List<UserDTO> activeUsers = userService.getAllActiveUsers();

		model.addAttribute(USERGROUPS_MODEL_ATTR,
				userGroupService.getUserGroupPageWithFilter(userGroupFilter, paginator));
		model.addAttribute(USERGROUP_CURATORS_ATTR, userService.getAllManagers(activeUsers));
		model.addAttribute(FILTER_MODEL_ATTR, userGroupFilter);
		model.addAttribute(USERGROUP_PAGINATOR_MODEL_ATTR, paginator);
		model.addAttribute(USERGROUP_LEVEL_ATTR, Arrays.asList(UserGroupLevel.values()));
		return USERGROUP_LIST_URL;
	}

	/**
	 * Controller method to show group details page.
	 *
	 * @param id
	 *            group id to show details.
	 * 
	 * @param model
	 *            group details show page view model.
	 * 
	 * @return group details page URL
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = USERGROUP_SHOW_MAPPING, method = RequestMethod.GET)
	public String showGroup(@PathVariable(PATH_VAR_ID) final Long id, final Model model) {
		model.addAttribute(USERGROUP_MODEL_ATTR, userGroupService.getById(id));
		model.addAttribute(USERGROUP_CURATOR_ATTR, userGroupService.getById(id).getCurator());
		model.addAttribute(USERGROUP_GROUP_MEMBERS, userGroupService.getById(id));

		return USERGROUP_SHOW_URL;
	}

	/**
	 * Redirects to a creation view previously put attributes in a model
	 * 
	 * @param model
	 *            Container for an attributes
	 * @return URL of a page with a form for creation a new group
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERVISOR', 'ROLE_MODERATOR')")
	@RequestMapping(USERGROUP_CREATE_MAPPING)
	public String createForm(final Model model) {
		List<UserDTO> activeUsers = userService.getAllActiveUsers();

		model.addAttribute(USERGROUP_MODEL_ATTR, new UserGroupDTO());
		model.addAttribute(USERGROUP_CURATORS_ATTR, userService.getAllManagers(activeUsers));
		model.addAttribute(USERGROUP_ALL_USERS_ATTR, activeUsers);
		model.addAttribute(USERGROUP_LEVEL_ATTR, Arrays.asList(UserGroupLevel.values()));
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
	public String create(@ModelAttribute(USERGROUP_MODEL_ATTR) @Valid UserGroupDTO userGroupDTO, BindingResult result,
			Model model) {
		List<UserDTO> activeUsers = userService.getAllActiveUsers();
		if (result.hasErrors()) {
			model.addAttribute(USERGROUP_CURATORS_ATTR, userService.getAllManagers(activeUsers));
			model.addAttribute(USERGROUP_ALL_USERS_ATTR, activeUsers);
			model.addAttribute(USERGROUP_LEVEL_ATTR, Arrays.asList(UserGroupLevel.values()));
			return USERGROUP_CREATE_URL;
		} else {
			userGroupService.create(userGroupService.addUserToGroup(userGroupDTO.getCurator(), userGroupDTO));
		}
		return USERGROUP_REDIRECT_URL;
	}

	/**
	 * Deletes a UserGroup(only when it has no members)
	 * 
	 * @param userGroup
	 *            A UserGroup that will be deleted
	 * @return URL of a page that shows all UserGroups
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERVISOR', 'ROLE_MODERATOR')")
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
	public String update(@ModelAttribute(USERGROUP_MODEL_ATTR) @Valid final UserGroupDTO userGroupDTO,
			BindingResult result, Model model) {
		List<UserDTO> activeUsers = userService.getAllActiveUsers();

		if (result.hasErrors()) {
			model.addAttribute(USERGROUP_CURATORS_ATTR, userService.getAllManagers(activeUsers));
			model.addAttribute(USERGROUP_ALL_USERS_ATTR, activeUsers);
			model.addAttribute(USERGROUP_LEVEL_ATTR, Arrays.asList(UserGroupLevel.values()));
			return USERGROUP_EDIT_URL;
		} else {
			userGroupService.addUserToGroup(userGroupDTO.getCurator(), userGroupDTO);
			userGroupService.update(userGroupDTO);
		}

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
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERVISOR', 'ROLE_MODERATOR')")
	@RequestMapping(value = USERGROUP_EDIT_MAPPING, method = RequestMethod.GET)
	public String updateForm(@PathVariable(PATH_VAR_ID) final Long id, final Model model) {
		List<UserDTO> activeUsers = userService.getAllActiveUsers();

		model.addAttribute(USERGROUP_MODEL_ATTR, userGroupService.getById(id));
		model.addAttribute(USERGROUP_CURATORS_ATTR, userService.getAllManagers(activeUsers));
		model.addAttribute(USERGROUP_ALL_USERS_ATTR, activeUsers);
		model.addAttribute(USERGROUP_LEVEL_ATTR, Arrays.asList(UserGroupLevel.values()));
		return USERGROUP_EDIT_URL;
	}
}
