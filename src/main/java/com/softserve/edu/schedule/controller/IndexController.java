/* IndexController 1.0 01/15/2017 */
package com.softserve.edu.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.edu.schedule.controller.constants.GeneralContrConst;
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
public class IndexController {

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
    @RequestMapping(GeneralContrConst.ROOT_URL)
    public String showIndex(final Model model) {
        model.addAttribute(GeneralContrConst.LOCATION_MODEL_ATTR,
                locationService.getAll());
        model.addAttribute(GeneralContrConst.SUBJECT_MODEL_ATTR,
                subjectService.getAll());
        model.addAttribute(GeneralContrConst.GROUP_MODEL_ATTR,
                userGroupService.getAll());
        return GeneralContrConst.INDEX_URL;
    }

    /**
     * Controls view of login page.
     *
     * @return login page URL
     */
    @RequestMapping(GeneralContrConst.LOGIN_PAGE_URL_MAPPING)
    public String login() {
        return GeneralContrConst.LOGIN_PAGE_URL;
    }

    /**
     * Redirect request from default sign in URL to custom URL.
     *
     * @return registration URL
     */
    @RequestMapping(value = GeneralContrConst.SIGNIN_PAGE_URL_MAPPING,
            method = RequestMethod.GET)
    public String redirectRequestToLoginPage() {
        return GeneralContrConst.LOGIN_SOCIAL_FAILED_REDIRECT_URL;
    }

    /**
     * Redirect request from default sign up URL to custom URL.
     *
     * @return registration URL
     */
    @RequestMapping(value = GeneralContrConst.SIGNUP_PAGE_URL_MAPPING,
            method = RequestMethod.GET)
    public String redirectRequestToRegistrationPage() {
        return GeneralContrConst.REGISTRATION_REDIRECT_URL;
    }

}
