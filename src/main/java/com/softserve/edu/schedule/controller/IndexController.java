/* IndexController 1.0 01/15/2017 */
package com.softserve.edu.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softserve.edu.schedule.service.LocationService;
import com.softserve.edu.schedule.service.SubjectService;
import com.softserve.edu.schedule.service.UserGroupService;

/**
 * A controller class for start page.
 *
 * @version 1.0 15 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Controller
public class IndexController implements ControllerConst {
	/**
	 * LocationService example to provide locations list to the model.
	 */
	@Autowired
	private LocationService locationService;

	/**
	 * LocationService example to provide locations list to the model.
	 */
	@Autowired
	private SubjectService subjectService;
	
	/**
	 * LocationService example to provide locations list to the model.
	 */
	@Autowired
	private UserGroupService userGroupService;
	
	/**
	 * Controls view of start page.
	 *
	 * @param model
	 *            start page view model.
	 *
	 * @return start page URL
	 */
	@RequestMapping(ROOT_URL)
	public String showIndex(final Model model) {
		model.addAttribute("locationI", locationService.getAll());
		model.addAttribute("subjectI", subjectService.getAll());
		model.addAttribute("groupI", userGroupService.getAll());
		return INDEX_URL;
	}

	/**
	 * Controls view of login page.
	 *
	 * @return login page URL
	 */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

}
