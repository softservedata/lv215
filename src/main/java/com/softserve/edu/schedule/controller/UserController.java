package com.softserve.edu.schedule.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserDTOForChangePassword;
import com.softserve.edu.schedule.dto.UserDTOForRestorePassword;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.UserFilter;
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
@SessionAttributes({ ControllerConst.UserControllerConst.FILTER_MODEL_ATTR,
        ControllerConst.UserControllerConst.USER_PAGINATOR_MODEL_ATTR })
public class UserController implements ControllerConst.UserControllerConst,
        ControllerConst.RegistrationControllerConst {

    /**
     * UserService example to provide business logic for user entity.
     */
    @Autowired
    private UserService userService;

    /**
     * Provides user filter.
     *
     * @return new UserFilter object.
     */
    @ModelAttribute(FILTER_MODEL_ATTR)
    public UserFilter getFilter() {
        return new UserFilter();
    }

    /**
     * Provides pagination object for users list page.
     *
     * @return new Paginator object.
     */
    @ModelAttribute(USER_PAGINATOR_MODEL_ATTR)
    public Paginator getPaginator() {
        return new Paginator();
    }

    /**
     * Controls processing of user delete URL.
     *
     * @param id
     *            an user id to delete user from database.
     *
     * @return users list page redirect URL
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(DELETE_USER_MAPPING + "{id}")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute(USER_MODEL_ATTR, userService.getById(id));

        if (userService.deleteById(id)) {
            return REDIRECT_USERS_PAGE;
        } else {
            return DELETE_USER_URL;
        }
    }

    /**
     * Controls processing of user update URL.
     *
     * @param id
     *            an user id to update user in database.
     *
     * @return users list page redirect URL
     */
    @RequestMapping(UPDATE_USER_MAPPING + "{id}")
    public String update(@PathVariable Long id, Model model) {
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

    /**
     * Controls processing of user change status (to BLOCKED) URL.
     *
     * @param id
     *            an user id to change status of user in database.
     *
     * @return users list page redirect URL
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
    @RequestMapping(value = SAVE_CHANGED_ROLE_MAPPING
            + "{id}", method = RequestMethod.POST)
    public String changeRole(@PathVariable Long id,
            @RequestParam UserRole role) {
        userService.changeRole(id, role);
        return REDIRECT_USERS_PAGE;
    }

    /**
     * Controls view for show profile of user.
     *
     * @param id
     *            from user id in database.
     *
     * @param user
     *            UserDTO example with required position.
     * 
     * @return users list page URL
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR', 'ROLE_USER')")
    @RequestMapping(USER_PROFILE_MAPPING + "{id}")
    public String getProfile(@PathVariable Long id, Model model) {
        model.addAttribute(USER_MODEL_ATTR, userService.getById(id));
        return USER_PROFILE_URL;
    }

    /**
     * Controls view for show profile of user.
     *
     * @param id
     *            from user id in database.
     *
     * @param user
     *            UserDTO example with required position.
     * 
     * @return users list page URL
     */
    @RequestMapping(USER_DETAILS_MAPPING)
    public String getUserDetails(Model model, Principal principal) {
        UserDTO activeUser = (UserDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute(USER_MODEL_ATTR,
                userService.getById(activeUser.getId()));
        return USER_DETAILS_URL;
    }

    /**
     * Controls processing of user update URL.
     *
     * @param id
     *            an user id to update user in database.
     *
     * @return users list page redirect URL
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR', 'ROLE_USER')")
    @RequestMapping(CHANGE_PASSWORD_MAPPING + "{id}")
    public String changePassword(@PathVariable Long id, Model model) {
        model.addAttribute(USER_MODEL_ATTR, userService.getByIdForPassword(id));
        return CHANGE_PASSWORD_URL;
    }

    /**
     * Controls processing of save updated user URL.
     *
     * @param id
     *            an user id to update user in database.
     *
     * @return users list page redirect URL
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = SAVE_CHANGED_PASSWORD_MAPPING
            + "{id}", method = RequestMethod.POST)
    public String saveChangedPassword(
            @ModelAttribute(USER_MODEL_ATTR) @Valid UserDTOForChangePassword user,
            BindingResult br) {
        if (br.hasErrors()) {
            return CHANGE_PASSWORD_URL;
        }
        userService.changePassword(user);
        return REDIRECT_USERS_PAGE;
    }

    /**
     * Controls view of users list page.
     *
     * @param model
     *            users list page view model.
     *
     * @param filter
     *            users list page view filter.
     *
     * @param paginator
     *            users list paginator.
     *
     * @return users list page URL
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR', 'ROLE_USER')")
    @RequestMapping(USERS_MAPPING_FROM_HEADER)
    public String showUserPage(final Model model,
            @ModelAttribute(FILTER_MODEL_ATTR) final UserFilter filter,
            @ModelAttribute(USER_PAGINATOR_MODEL_ATTR) final Paginator paginator) {
        model.addAttribute(USERS_MODEL_ATTR,
                userService.getUsersPageWithFilter(filter, paginator));
        return USERS_PAGE_URL;
    }

    // /**
    // * Controls view for save image to profile.
    // *
    // * @param user
    // * userDTO example of authorized user.
    // *
    // * @param principal
    // * authorized user.
    // *
    // * @param image
    // * image from user.
    // *
    // * @return path of images
    // */
    // @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR',
    // 'ROLE_MODERATOR', 'ROLE_USER')")
    // @RequestMapping(value = SAVE_IMAGES, method = RequestMethod.POST)
    // public String saveImage(@ModelAttribute(USER_MODEL_ATTR) UserDTO user,
    // Principal principal, @RequestParam MultipartFile image) {
    // userService.saveImage(principal, image);
    // return REDIRECT_USER_DETAILS_URL;
    // }

    /**
     * Method shows meetings of user
     * 
     * @param id
     *            id of user
     * 
     * @param model
     *            model of user
     * 
     * @return meetings of users.
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR', 'ROLE_USER')")
    @RequestMapping(USER_MEETINGS_MAPPING + "{id}")
    public String showMeetings(@PathVariable Long id, final Model model) {
        model.addAttribute(USER_MODEL_ATTR, userService.getById(id));
        return USER_MEETINGS_URL;
    }

    /**
     * Method shows meetings of user
     * 
     * @param model
     *            model of user
     * 
     * @return meetings of users.
     */
    @RequestMapping(RESTORE_PASSWORD_MAPPING)
    public String changeForgottenPassord(final Model model) {
        model.addAttribute(/*MAIL_MODEL_ATTR*/USER_MODEL_ATTR, new UserDTOForRestorePassword());
        return RESTORE_PASSWORD_URL;
    }
}
