package com.softserve.edu.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserRole;
import com.softserve.edu.schedule.entity.UserStatus;
import com.softserve.edu.schedule.service.UserService;

/**
 * A controller class of user pages.
 *
 * @version 1.0 20 January 2017
 *
 * @author Serhiy Dudynsky
 *
 * @since 1.8
 */
@Controller
public class UserController implements ControllerConst.UserControllerConst,
        ControllerConst.RegistrationControllerConst {

    @Autowired
    private UserService userService;

    @RequestMapping(USERS_MAPPING_FROM_HEADER)
    public String allUserPage(Model model) {
        model.addAttribute(USERS_MODEL_ATTR, userService.getAll());
        return USERS_PAGE_URL;
    }

    @RequestMapping(DELETE_USER_MAPPING+"{id}")
    public String delete(@PathVariable Long id) {
        try {
            userService.deleteById(id);
        } catch (Exception e) {
            return REDIRECT_USERS_PAGE;
        }
        return REDIRECT_USERS_PAGE;
    }

    @RequestMapping(value = EDIT_USER_MAPPING+"{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute(USER_MODEL_ATTR,
                userService.getById(id));
        return EDIT_PAGE_URL;
    }

    @RequestMapping(UPDATE_USER_MAPPING+"{id}")
    public String getEdit(@PathVariable Long id, Model model) {
        model.addAttribute(USER_UPDATE_ATTR,
                userService.getById(id));
        return UPDATE_PAGE_URL;
    }

    @RequestMapping(value = SAVE_UPDATED_USER_MAPPING+"{id}"
            , method = RequestMethod.POST)
    public String updateUser(
            @ModelAttribute(USER_UPDATE_ATTR) UserDTO user) {
        userService.update(user);
        return REDIRECT_USERS_PAGE;
    }

    @RequestMapping(UPDATE_POSITION_MAPPING+"{id}")
    public String getEditPosition(@PathVariable Long id, Model model) {
        model.addAttribute(USER_UPDATE_POSITION_ATTR,
                userService.getById(id));
        return UPDATE_POSITION_PAGE_URL;
    }

    @RequestMapping(value = SAVE_UPDATED_POSITION_MAPPING+"{id}"
            , method = RequestMethod.POST)
    public String updateUserPosition(@PathVariable Long id,
            @RequestParam String position) {
        userService.changePosition(id, position);
        return REDIRECT_USERS_PAGE;
    }

    @RequestMapping(BAN_USER_MAPPING+"{id}")
    public String bunUser(@PathVariable Long id) {
        UserStatus userStatus = UserStatus.BLOCKED;
        userService.changeStatus(id, userStatus);
        return REDIRECT_USERS_PAGE;
    }

    @RequestMapping(UNBAN_USER_MAPPING+"{id}")
    public String unBunUser(@PathVariable Long id) {
        UserStatus userStatus = UserStatus.ACTIVE;
        userService.changeStatus(id, userStatus);
        return REDIRECT_USERS_PAGE;
    }

    @RequestMapping(CHANGE_ROLE_MAPPING+"{id}")
    public String changeRole(@PathVariable Long id, Model model) {
        model.addAttribute(USER_ROLE_ATTR,
                UserRole.values());
        model.addAttribute(USER_MODEL_ATTR,
                userService.getById(id));
        return CHANGE_ROLE_PAGE_URL;
    }

    @RequestMapping(value = SAVE_CHANGED_ROLE_MAPPING+"{id}"
            , method = RequestMethod.POST)
    public String changeRole(@PathVariable Long id,
            @RequestParam UserRole chooseRole) {
        userService.changeRole(id, chooseRole);
        return REDIRECT_USERS_PAGE;
    }

    @RequestMapping(SORT_BY_LASTNAME_ASC_MAPPING)
    public String sortByLastNameAsc(Model model) {
        model.addAttribute(USERS_MODEL_ATTR,
                userService.sortByLastName(Order.ASC));
        return USERS_PAGE_URL;
    }

    @RequestMapping(SORT_BY_LASTNAME_DESC_MAPPING)
    public String sortByLastNameDesc(Model model) {
        model.addAttribute(USERS_MODEL_ATTR,
                userService.sortByLastName(Order.DESC));
        return USERS_PAGE_URL;
    }

    @RequestMapping(SORT_BY_POSITION_ASC_MAPPING)
    public String sortByPositionAsc(Model model) {
        model.addAttribute(USERS_MODEL_ATTR,
                userService.sortByPosition(Order.ASC));
        return USERS_PAGE_URL;
    }

    @RequestMapping(SORT_BY_POSITION_DESC_MAPPING)
    public String sortByPositionDesc(Model model) {
        model.addAttribute(USERS_MODEL_ATTR,
                userService.sortByPosition(Order.DESC));
        return USERS_PAGE_URL;
    }
}
