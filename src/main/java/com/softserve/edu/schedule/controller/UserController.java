package com.softserve.edu.schedule.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;

import com.softserve.edu.schedule.controller.constants.UserControllerConst;
import com.softserve.edu.schedule.controller.constants.RegistrationControllerConst;
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
@SessionAttributes({ UserControllerConst.FILTER_MODEL_ATTR,
        UserControllerConst.USER_PAGINATOR_MODEL_ATTR })
public class UserController {

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
    @ModelAttribute(UserControllerConst.FILTER_MODEL_ATTR)
    public UserFilter getFilter() {
        return new UserFilter();
    }

    /**
     * Provides pagination object for users list page.
     *
     * @return new Paginator object.
     */
    @ModelAttribute(UserControllerConst.USER_PAGINATOR_MODEL_ATTR)
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
    @PreAuthorize(UserControllerConst.HAS_ROLE_ADMIN)
    @RequestMapping(UserControllerConst.DELETE_USER_MAPPING + "{id}")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute(UserControllerConst.USER_MODEL_ATTR,
                userService.getById(id));

        if (userService.deleteById(id)) {
            return RegistrationControllerConst.REDIRECT_USERS_PAGE;
        } else {
            return UserControllerConst.DELETE_USER_URL;
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
    @PreAuthorize(UserControllerConst.IS_AUTHENTICATED)
    @RequestMapping(UserControllerConst.UPDATE_USER_MAPPING + "{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute(UserControllerConst.USER_UPDATE_ATTR,
                userService.getById(id));
        return UserControllerConst.UPDATE_PAGE_URL;
    }

    /**
     * Controls processing of save updated user URL.
     *
     * @param id
     *            an user id to update user in database.
     *
     * @return users list page redirect URL
     */
    @PreAuthorize(UserControllerConst.IS_AUTHENTICATED)
    @RequestMapping(value = UserControllerConst.SAVE_UPDATED_USER_MAPPING
            + "{id}", method = RequestMethod.POST)
    public String updateUser(
            @ModelAttribute(UserControllerConst.USER_UPDATE_ATTR) @Valid UserDTO user,
            BindingResult br) {
        if (br.hasErrors()) {
            return UserControllerConst.UPDATE_PAGE_URL;
        }
        userService.update(user);
        return RegistrationControllerConst.REDIRECT_USERS_PAGE;
    }

    /**
     * Controls processing of user change status (to BLOCKED) URL.
     *
     * @param id
     *            an user id to change status of user in database.
     *
     * @return users list page redirect URL
     */
    @PreAuthorize(UserControllerConst.HAS_ROLE_ADMIN_OR_ROLE_SUPERVISOR)
    @RequestMapping(UserControllerConst.BAN_USER_MAPPING + "{id}")
    public String bunUser(@PathVariable Long id) {
        UserStatus userStatus = UserStatus.BLOCKED;
        userService.changeStatus(id, userStatus);
        return RegistrationControllerConst.REDIRECT_USERS_PAGE;
    }

    /**
     * Controls processing of user change status (to ACTIVE) URL.
     *
     * @param id
     *            an user id to change status of user in database.
     *
     * @return users list page redirect URL
     */
    @PreAuthorize(UserControllerConst.HAS_ROLE_ADMIN_OR_ROLE_SUPERVISOR)
    @RequestMapping(UserControllerConst.UNBAN_USER_MAPPING + "{id}")
    public String unBunUser(@PathVariable Long id) {
        UserStatus userStatus = UserStatus.ACTIVE;
        userService.changeStatus(id, userStatus);
        return RegistrationControllerConst.REDIRECT_USERS_PAGE;
    }

    /**
     * Controls processing of user change role URL.
     *
     * @param id
     *            an user id to change role of user in database.
     *
     * @return users list page redirect URL
     */
    @PreAuthorize(UserControllerConst.HAS_ROLE_ADMIN_OR_ROLE_SUPERVISOR)
    @RequestMapping(UserControllerConst.CHANGE_ROLE_MAPPING + "{id}")
    public String changeRole(@PathVariable Long id, Model model) {
        model.addAttribute(UserControllerConst.USER_ROLE_ATTR,
                UserRole.values());
        model.addAttribute(UserControllerConst.USER_MODEL_ATTR,
                userService.getById(id));
        return UserControllerConst.CHANGE_ROLE_PAGE_URL;
    }

    /**
     * Controls processing user save changed role of URL.
     *
     * @param id
     *            an user id to change role of user in database.
     *
     * @return users list page redirect URL
     */
    @PreAuthorize(UserControllerConst.HAS_ROLE_ADMIN_OR_ROLE_SUPERVISOR)
    @RequestMapping(value = UserControllerConst.SAVE_CHANGED_ROLE_MAPPING
            + "{id}", method = RequestMethod.POST)
    public String changeRole(@PathVariable Long id,
            @RequestParam UserRole role) {
        userService.changeRole(id, role);
        return RegistrationControllerConst.REDIRECT_USERS_PAGE;
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
    @PreAuthorize(UserControllerConst.HAS_ANY_AUTHORIZED_ROLE)
    @RequestMapping(UserControllerConst.USER_PROFILE_MAPPING + "{id}")
    public String getProfile(
            @PathVariable(UserControllerConst.PATH_VAR_ID) Long id,
            Model model) {
        model.addAttribute(UserControllerConst.USER_MODEL_ATTR,
                userService.getById(id));
        model.addAttribute(UserControllerConst.USER_FILES,
                userService.showUserFile(id));
        return UserControllerConst.USER_PROFILE_URL;
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
    @PreAuthorize(UserControllerConst.IS_AUTHENTICATED)
    @RequestMapping(UserControllerConst.USER_DETAILS_MAPPING)
    public String getUserDetails(Model model, Principal principal) {
        UserDTO activeUser = (UserDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute(UserControllerConst.USER_MODEL_ATTR,
                userService.getById(activeUser.getId()));
        model.addAttribute(UserControllerConst.USER_FILES,
                userService.showUserFile(activeUser.getId()));
        return UserControllerConst.USER_DETAILS_URL;
    }

    /**
     * Download file.
     *
     * @param id
     *            from user id in database.
     *
     * @param fileName
     *            name of file in database.
     * 
     * @param response
     *            response.
     */
    @PreAuthorize(UserControllerConst.IS_AUTHENTICATED)
    @RequestMapping("/downloadFile/{fileName}/{id}")
    public void downloadFile(@PathVariable final Long id,
            @PathVariable final String fileName, HttpServletResponse response,
            Model model) throws IOException {
        userService.retrivePhotoById(id, fileName, response);
        model.addAttribute(UserControllerConst.USER_MODEL_ATTR,
                userService.getById(id));
    }

    /**
     * Controls processing of user update URL.
     *
     * @param id
     *            an user id to update user in database.
     *
     * @return users list page redirect URL
     */
    @PreAuthorize(UserControllerConst.HAS_ANY_AUTHORIZED_ROLE)
    @RequestMapping(UserControllerConst.CHANGE_PASSWORD_MAPPING + "{id}")
    public String changePassword(@PathVariable Long id, Model model) {
        model.addAttribute(UserControllerConst.USER_MODEL_ATTR,
                userService.getByIdForPassword(id));
        return UserControllerConst.CHANGE_PASSWORD_URL;
    }

    /**
     * Controls processing of save updated user URL.
     *
     * @param id
     *            an user id to update user in database.
     *
     * @return users list page redirect URL
     */
    @PreAuthorize(UserControllerConst.IS_AUTHENTICATED)
    @RequestMapping(value = UserControllerConst.SAVE_CHANGED_PASSWORD_MAPPING
            + "{id}", method = RequestMethod.POST)
    public String saveChangedPassword(
            @ModelAttribute(UserControllerConst.USER_MODEL_ATTR) @Valid UserDTOForChangePassword user,
            BindingResult br) {
        if (br.hasErrors()) {
            return UserControllerConst.CHANGE_PASSWORD_URL;
        }
        userService.changePassword(user);
        return RegistrationControllerConst.REDIRECT_USERS_PAGE;
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
    @PreAuthorize(UserControllerConst.HAS_ANY_AUTHORIZED_ROLE)
    @RequestMapping(UserControllerConst.USERS_MAPPING_FROM_HEADER)
    public String showUserPage(final Model model,
            @ModelAttribute(UserControllerConst.FILTER_MODEL_ATTR) final UserFilter filter,
            @ModelAttribute(UserControllerConst.USER_PAGINATOR_MODEL_ATTR) final Paginator paginator) {
        model.addAttribute(UserControllerConst.USERS_MODEL_ATTR,
                userService.getUsersPageWithFilter(filter, paginator));
        return UserControllerConst.USERS_PAGE_URL;
    }

    /**
     * Controls view for save image to profile.
     *
     * @param principal
     *            authorized user.
     *
     * @param image
     *            image from user.
     *
     * @return path of images
     */
    @PreAuthorize(UserControllerConst.HAS_ANY_AUTHORIZED_ROLE)
    @RequestMapping(value = UserControllerConst.SAVE_IMAGES, method = RequestMethod.POST)
    public String saveImage(Principal principal,
            @RequestParam MultipartFile image) throws IOException {
        UserDTO activeUser = (UserDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        userService.deleteUserPhotoById(activeUser.getId());
        userService.saveImage(principal, image);
        return UserControllerConst.REDIRECT_USER_DETAILS_URL;
    }

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
    @PreAuthorize(UserControllerConst.HAS_ANY_AUTHORIZED_ROLE)
    @RequestMapping(UserControllerConst.USER_MEETINGS_MAPPING + "{id}")
    public String showMeetings(@PathVariable Long id, final Model model) {
        model.addAttribute(UserControllerConst.USER_MODEL_ATTR,
                userService.getById(id));
        return UserControllerConst.USER_MEETINGS_URL;
    }

    /**
     * Method shows meetings of user
     * 
     * @param model
     *            model of user
     * 
     * @return meetings of users.
     */
    @RequestMapping(UserControllerConst.RESTORE_PASSWORD_MAPPING)
    public String changeForgottenPassord(final Model model) {
        model.addAttribute(UserControllerConst.USER_MODEL_ATTR,
                new UserDTOForRestorePassword());
        return UserControllerConst.RESTORE_PASSWORD_URL;
    }

    /**
     * Method shows meetings of user
     * 
     * @param model
     *            model of user
     * 
     * @return meetings of users.
     */
    @RequestMapping(value = UserControllerConst.RESTORE_PASSWORD_MAPPING, method = RequestMethod.POST)
    public String submitChangeForgottenPassord(final Model model,
            @ModelAttribute(UserControllerConst.USER_MODEL_ATTR) final UserDTOForRestorePassword userDTO) {

        if (userService.isUserWithMailExist(userDTO)) {
            model.addAttribute(UserControllerConst.USER_MODEL_ATTR,
                    userService.submitRestorePassword(userDTO));
            return UserControllerConst.PASSWORD_WAS_SENT;
        } else {
            return UserControllerConst.NO_SUCH_USER_URL;
        }
    }
}
