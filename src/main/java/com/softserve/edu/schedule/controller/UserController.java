package com.softserve.edu.schedule.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.entity.UserRole;
import com.softserve.edu.schedule.entity.UserStatus;
import com.softserve.edu.schedule.service.UserService;
import com.softserve.edu.schedule.service.implementation.validators.UserValidator;

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

    /**
     * UserService example to provide business logic for user entity.
     */
    @Autowired
    private UserService userService;

    /**
     * UserValidator example to provide form validation operations.
     */
    @Autowired
    private UserValidator userValidator;

    /**
     * Initialize binder for user model.
     *
     * @param binder
     *            a WebDataBinder example to initialize.
     */
    @InitBinder(value = { USER_UPDATE_ATTR , USER_UPDATE_POSITION_ATTR })
    protected void initBinder(final WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    /**
     * Provides user model.
     * 
     * @return new UserDTO object.
     */
    @ModelAttribute(SEARCH_MODEL_ATTR)
    public UserDTO getUserDTO() {
        return new UserDTO();
    }

    /**
     * Controls view of users list page.
     *
     * @param model
     *            users list page view model.
     * 
     * @return users list page URL
     */
    @RequestMapping(USERS_MAPPING_FROM_HEADER)
    public String allUserPage(Model model) {
        model.addAttribute(USERS_MODEL_ATTR, userService.getAll());
        return USERS_PAGE_URL;
    }

    /**
     * Controls processing of user delete URL.
     *
     * @param id
     *            an user id to delete user from database.
     *
     * @return users list page redirect URL
     */
    @RequestMapping(DELETE_USER_MAPPING + "{id}")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute(USER_MODEL_ATTR, userService.getById(id));

        if (userService.deleteById(id)) {
            return REDIRECT_USERS_PAGE;
        } else {
            return DELETE_USER_URL;
        }
    }

//    /**
//     * Controls processing of user edit URL.
//     *
//     * @param id
//     *            an user id to edit user in database.
//     *
//     * @return users list page redirect URL
//     */
//    @RequestMapping(value = EDIT_USER_MAPPING + "{id}")
//    public String edit(@PathVariable Long id, Model model) {
//        model.addAttribute(USER_MODEL_ATTR, userService.getById(id));
//        return EDIT_PAGE_URL;
//    }

    /**
     * Controls processing of user update URL.
     *
     * @param id
     *            an user id to update user in database.
     *
     * @return users list page redirect URL
     */
    @RequestMapping(UPDATE_USER_MAPPING + "{id}")
    public String getEdit(@PathVariable Long id, Model model) {
        model.addAttribute(USER_UPDATE_ATTR, userService.getById(id));
        return UPDATE_PAGE_URL;
    }

    /**
     * Controls processing of save updated user URL.
     *
     * @param id
     *            an user id to update user in database.
     *
     * @return users list page redirect URL
     */
    @RequestMapping(value = SAVE_UPDATED_USER_MAPPING
            + "{id}", method = RequestMethod.POST)
    public String updateUser(
            @ModelAttribute(USER_UPDATE_ATTR) @Valid UserDTO user,
            BindingResult br) {
        if (br.hasErrors()) {
            return UPDATE_PAGE_URL;
        }
        userService.update(user);
        return REDIRECT_USERS_PAGE;
    }

//    /**
//     * Controls processing of user update position URL.
//     *
//     * @param id
//     *            an user id to update position of user in database.
//     *
//     * @return users list page redirect URL
//     */
//    @RequestMapping(UPDATE_POSITION_MAPPING + "{id}")
//    public String getEditPosition(@PathVariable Long id, Model model) {
//        model.addAttribute(USER_UPDATE_POSITION_ATTR, userService.getById(id));
//        return UPDATE_POSITION_PAGE_URL;
//    }
//
//    /**
//     * Controls processing save updated position of user URL.
//     *
//     * @param id
//     *            an user id to update position of user in database.
//     *
//     * @return users list page redirect URL
//     */
//    @RequestMapping(value = SAVE_UPDATED_POSITION_MAPPING
//            + "{id}", method = RequestMethod.POST)
//    public String updateUserPosition(
//            @ModelAttribute(USER_UPDATE_POSITION_ATTR) @Valid UserDTO user,
//            @PathVariable Long id, /*@RequestParam String position,*/
//            BindingResult br) {
//        if (br.hasErrors()) {
//            return UPDATE_POSITION_PAGE_URL;
//        }
//        userService.changePosition(id, position);
//        userService.update(user);
//        return REDIRECT_USERS_PAGE;
//    }

    /**
     * Controls processing of user change status (to BLOCKED) URL.
     *
     * @param id
     *            an user id to change status of user in database.
     *
     * @return users list page redirect URL
     */
    @RequestMapping(BAN_USER_MAPPING + "{id}")
    public String bunUser(@PathVariable Long id) {
        UserStatus userStatus = UserStatus.BLOCKED;
        userService.changeStatus(id, userStatus);
        return REDIRECT_USERS_PAGE;
    }

    /**
     * Controls processing of user change status (to ACTIVE) URL.
     *
     * @param id
     *            an user id to change status of user in database.
     *
     * @return users list page redirect URL
     */
    @RequestMapping(UNBAN_USER_MAPPING + "{id}")
    public String unBunUser(@PathVariable Long id) {
        UserStatus userStatus = UserStatus.ACTIVE;
        userService.changeStatus(id, userStatus);
        return REDIRECT_USERS_PAGE;
    }

    /**
     * Controls processing of user change role URL.
     *
     * @param id
     *            an user id to change role of user in database.
     *
     * @return users list page redirect URL
     */
    @RequestMapping(CHANGE_ROLE_MAPPING + "{id}")
    public String changeRole(@PathVariable Long id, Model model) {
        model.addAttribute(USER_ROLE_ATTR, UserRole.values());
        model.addAttribute(USER_MODEL_ATTR, userService.getById(id));
        return CHANGE_ROLE_PAGE_URL;
    }

    /**
     * Controls processing user save changed role of URL.
     *
     * @param id
     *            an user id to change role of user in database.
     *
     * @return users list page redirect URL
     */
    @RequestMapping(value = SAVE_CHANGED_ROLE_MAPPING
            + "{id}", method = RequestMethod.POST)
    public String changeRole(@PathVariable Long id,
            @RequestParam UserRole role) {
        userService.changeRole(id, role);
        return REDIRECT_USERS_PAGE;
    }

    /**
     * Controls view of users list page in ascending order by last name.
     *
     * @param model
     *            users list page view model.
     * 
     * @return users list page URL
     */
    @RequestMapping(SORT_BY_LASTNAME_ASC_MAPPING)
    public String sortByLastNameAsc(Model model) {
        model.addAttribute(USERS_MODEL_ATTR,
                userService.sortByLastName(Order.ASC));
        return USERS_PAGE_URL;
    }

    /**
     * Controls view of users list page in descending order by last name.
     *
     * @param model
     *            users list page view model.
     * 
     * @return users list page URL
     */
    @RequestMapping(SORT_BY_LASTNAME_DESC_MAPPING)
    public String sortByLastNameDesc(Model model) {
        model.addAttribute(USERS_MODEL_ATTR,
                userService.sortByLastName(Order.DESC));
        return USERS_PAGE_URL;
    }

    /**
     * Controls view of users list page in ascending order by position.
     *
     * @param model
     *            users list page view model.
     * 
     * @return users list page URL
     */
    @RequestMapping(SORT_BY_POSITION_ASC_MAPPING)
    public String sortByPositionAsc(Model model) {
        model.addAttribute(USERS_MODEL_ATTR,
                userService.sortByPosition(Order.ASC));
        return USERS_PAGE_URL;
    }

    /**
     * Controls view of users list page in descending order by position.
     *
     * @param model
     *            users list page view model.
     * 
     * @return users list page URL
     */
    @RequestMapping(SORT_BY_POSITION_DESC_MAPPING)
    public String sortByPositionDesc(Model model) {
        model.addAttribute(USERS_MODEL_ATTR,
                userService.sortByPosition(Order.DESC));
        return USERS_PAGE_URL;
    }

    /**
     * Controls view for search user by last name.
     *
     * @param model
     *            users list page view model.
     * 
     * @param user
     *            UserDTO example with required last name.
     * 
     * @return users list page URL
     */
    @RequestMapping(value = SEARCH_BY_LASTNANE_MAPPING, method = RequestMethod.POST)
    public String searchByName(@ModelAttribute(SEARCH_MODEL_ATTR) UserDTO user,
            Model model) {
        model.addAttribute(USERS_MODEL_ATTR,
                userService.searchByLastName(user.getLastName()));
        return USERS_PAGE_URL;
    }

    /**
     * Controls view for search user by position.
     *
     * @param model
     *            users list page view model.
     * 
     * @param user
     *            UserDTO example with required position.
     * 
     * @return users list page URL
     */
    @RequestMapping(value = SEARCH_BY_POSITION_MAPPING, method = RequestMethod.POST)
    public String searchByPosition(
            @ModelAttribute(SEARCH_MODEL_ATTR) UserDTO user, Model model) {
        model.addAttribute(USERS_MODEL_ATTR,
                userService.searchByPosition(user.getPosition()));
        return USERS_PAGE_URL;
    }
}
