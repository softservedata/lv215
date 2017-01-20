package com.softserve.edu.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.service.UserService;

@Controller
public class RegistrationController {

    /**
     * Register new user mapping URL.
     */
    public static final String USER_REGIST_MAPPING_FROM_STARTPAGE = "registration";

    /**
     * Register new user mapping URL for administrator.
     */
    public static final String USER_REGIST_MAPPING_FOR_ADMIN = "users/registration";

    /**
     * Registration model attribute name.
     */
    public static final String USER_REGIST_MODEL_ATTR = "userFormCreate";

    /**
     * Registration model attribute name.
     */
    public static final String USER_REGIST_URL = "users/registration";

    /**
     * Registration model attribute name.
     */
    public static final String REDIRECT_STARTPAGE = "redirect:/";

    /**
     * Registration model attribute name.
     */
    public static final String REDIRECT_USERS_PAGE = "redirect:/users";

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
    @RequestMapping(value = { USER_REGIST_MAPPING_FROM_STARTPAGE,
            USER_REGIST_MAPPING_FOR_ADMIN })
    public String newUserPage(Model model) {
        model.addAttribute(USER_REGIST_MODEL_ATTR, new UserDTO());
        return USER_REGIST_URL;
    }

    /**
     * Controls view of user create form.
     *
     * @param User
     *            user example which is built based on form data.
     *
     * @return start page URL
     */
    @RequestMapping(value = USER_REGIST_MAPPING_FROM_STARTPAGE, method = RequestMethod.POST)
    public String newUserFromStartPage(
            @ModelAttribute(USER_REGIST_MODEL_ATTR) UserDTO user) {
        userService.create(user);
        return REDIRECT_STARTPAGE;
    }

    /**
     * Controls view of user create form for administrator.
     *
     * @param User
     *            user example which is built based on form data.
     *
     * @return users page URL
     */
    @RequestMapping(value = USER_REGIST_MAPPING_FOR_ADMIN, method = RequestMethod.POST)
    public String newUserForAdmin(
            @ModelAttribute(USER_REGIST_MODEL_ATTR) UserDTO user) {
        userService.create(user);
        return REDIRECT_USERS_PAGE;
    }
}
