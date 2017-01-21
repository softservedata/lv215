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
public class UserController implements ControllerConst {

    @Autowired
    private UserService userService;

    @RequestMapping(UserControllerConst.USERS_MAPPING_FROM_HEADER)
    public String allUserPage(Model model) {
        model.addAttribute(UserControllerConst.USERS_MODEL_ATTR,
                userService.getAll());
        return "users/users";// 3.1------String USERS_PAGE_URL = "users/users";
    }

    @RequestMapping(UserControllerConst.DELETE_USER_MAPPING)
    public String delete(@PathVariable Long id) {
        try {
            userService.deleteById(id);
        } catch (Exception e) {
            return RegistrationControllerConst.REDIRECT_USERS_PAGE;
        }
        return RegistrationControllerConst.REDIRECT_USERS_PAGE;
    }

    @RequestMapping(value = UserControllerConst.EDIT_USER_MAPPING)
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute(UserControllerConst.USER_MODEL_ATTR,
                userService.getById(id));
        return "users/users/edit";
    }

    @RequestMapping(UserControllerConst.UPDATE_USER_MAPPING)
    public String getEdit(@PathVariable Long id, Model model) {
        model.addAttribute(UserControllerConst.USER_UPDATE_ATTR,
                userService.getById(id));
        return "users/users/edit/updateUser";
    }

    @RequestMapping(value = UserControllerConst.SAVE_UPDATED_USER_MAPPING
            , method = RequestMethod.POST)
    public String updateUser(
            @ModelAttribute(UserControllerConst.USER_UPDATE_ATTR) UserDTO user) {
        userService.update(user);
        return RegistrationControllerConst.REDIRECT_USERS_PAGE;
    }

    @RequestMapping(UserControllerConst.UPDATE_POSITION_MAPPING)
    public String getEditPosition(@PathVariable Long id, Model model) {
        model.addAttribute(UserControllerConst.USER_UPDATE_POSITION_ATTR,
                userService.getById(id));
        return "users/users/edit/updateUserPosition";
    }

    @RequestMapping(value = UserControllerConst.SAVE_UPDATED_POSITION_MAPPING
            , method = RequestMethod.POST)
    public String updateUserPosition(@PathVariable Long id,
            @RequestParam String position) {
        userService.changePosition(id, position);
        return RegistrationControllerConst.REDIRECT_USERS_PAGE;
    }

    @RequestMapping(UserControllerConst.BAN_USER_MAPPING)
    public String bunUser(@PathVariable Long id) {
        UserStatus userStatus = UserStatus.BLOCKED;
        userService.changeStatus(id, userStatus);
        return RegistrationControllerConst.REDIRECT_USERS_PAGE;
    }

    @RequestMapping(UserControllerConst.UNBAN_USER_MAPPING)
    public String unBunUser(@PathVariable Long id) {
        UserStatus userStatus = UserStatus.ACTIVE;
        userService.changeStatus(id, userStatus);
        return RegistrationControllerConst.REDIRECT_USERS_PAGE;
    }

    @RequestMapping({ UserControllerConst.CHANGE_ROLE_MAPPING })
    public String changeRole(@PathVariable Long id, Model model) {
        model.addAttribute(UserControllerConst.USER_ROLE_ATTR,
                UserRole.values());
        model.addAttribute(UserControllerConst.USER_MODEL_ATTR,
                userService.getById(id));
        return "users/users/edit/changeRole";
    }

    @RequestMapping(value = UserControllerConst.SAVE_CHANGED_ROLE_MAPPING
            , method = RequestMethod.POST)
    public String changeRole(@ModelAttribute User user, @PathVariable Long id,
            @RequestParam UserRole chooseRole) {
        userService.changeRole(id, chooseRole);
        return RegistrationControllerConst.REDIRECT_USERS_PAGE;
    }

    @RequestMapping(UserControllerConst.SORT_BY_LASTNAME_ASC_MAPPING)
    public String sortByLastNameAsc(Model model) {
        model.addAttribute(UserControllerConst.USERS_MODEL_ATTR,
                userService.sort("lastName", Order.ASC));
        return "users/users";// 3.1------String USERS_PAGE_URL = "users/users";
    }

    @RequestMapping(UserControllerConst.SORT_BY_LASTNAME_DESC_MAPPING)
    public String sortByLastNameDesc(Model model) {
        model.addAttribute(UserControllerConst.USERS_MODEL_ATTR,
                userService.sort("lastName", Order.DESC));
        return "users/users";// 3.1------String USERS_PAGE_URL = "users/users";
    }

    @RequestMapping(UserControllerConst.SORT_BY_POSITION_ASC_MAPPING)
    public String sortByPositionAsc(Model model) {
        model.addAttribute(UserControllerConst.USERS_MODEL_ATTR,
                userService.sort("position", Order.ASC));
        return "users/users";// 3.1------String USERS_PAGE_URL = "users/users";
    }

    @RequestMapping(UserControllerConst.SORT_BY_POSITION_DESC_MAPPING)
    public String sortByPositionDesc(Model model) {
        model.addAttribute(UserControllerConst.USERS_MODEL_ATTR,
                userService.sort("position", Order.DESC));
        return "users/users";// 3.1------String USERS_PAGE_URL = "users/users";
    }
}
