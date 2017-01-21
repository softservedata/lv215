package com.softserve.edu.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.service.UserService;


/**
 * A controller class of registration pages.
 *
 * @version 1.0 20 January 2017
 *
 * @author Serhiy Dudynsky
 *
 * @since 1.8
 */
@Controller
public class RegistrationController implements ControllerConst {

    @Autowired
    private UserService userService;

    /**
     * Controls view of user registration page.
     *
     * @param model
     *            user registration page view model.
     *
     * @return start page URL
     */
    @RequestMapping(value = {
            RegistrationControllerConst.USER_REGIST_MAPPING_FROM_STARTPAGE,
            RegistrationControllerConst.USER_REGIST_MAPPING_FOR_ADMIN })
    public String newUserPage(Model model) {
        model.addAttribute(RegistrationControllerConst.USER_REGIST_MODEL_ATTR,
                new UserDTO());
        return RegistrationControllerConst.USER_REGIST_URL;
    }

    /**
     * Controls view of user create form.
     *
     * @param User
     *            user example which is built based on form data.
     *
     * @return start page URL
     */
    @RequestMapping(value = RegistrationControllerConst
            .USER_REGIST_MAPPING_FROM_STARTPAGE
            , method = RequestMethod.POST)
    public String newUserFromStartPage(
            @ModelAttribute(RegistrationControllerConst.USER_REGIST_MODEL_ATTR) UserDTO user) {
        userService.create(user);
        return RegistrationControllerConst.REDIRECT_STARTPAGE;
    }

    /**
     * Controls view of user create form for administrator.
     *
     * @param User
     *            user example which is built based on form data.
     *
     * @return users page URL
     */
    @RequestMapping(value = RegistrationControllerConst
            .USER_REGIST_MAPPING_FOR_ADMIN
            , method = RequestMethod.POST)
    public String newUserForAdmin(
            @ModelAttribute(RegistrationControllerConst.USER_REGIST_MODEL_ATTR) UserDTO user) {
        userService.create(user);
        return RegistrationControllerConst.REDIRECT_USERS_PAGE;
    }
}
