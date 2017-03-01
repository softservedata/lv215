/* RegistrationController 1.0 01/20/2017 */
package com.softserve.edu.schedule.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.softserve.edu.schedule.controller.constants.RegistrationControllerConst;
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
public class RegistrationController {

    /**
     * UserService example to provide business logic for user entity.
     */
    @Autowired
    private UserService userService;

    /**
     * ProviderSignInUtils instance to provide social network registration.
     */
    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    /**
     * Controls view of user registration page.
     *
     * @param model
     *            user registration page view model.
     *
     * @param request
     *            WebRequest instance to get social connection
     *
     * @return start page URL
     */
    @RequestMapping(value = RegistrationControllerConst.USER_REGIST_MAPPING_FROM_STARTPAGE, method = RequestMethod.GET)
    public String newUserPage(final WebRequest request, final Model model) {
        Connection<?> connection = providerSignInUtils
                .getConnectionFromSession(request);
        if (connection != null) {
            UserDetails userDetails = userService.loadUserByUsername(
                    connection.fetchUserProfile().getEmail());
            if (userDetails != null) {
                return processExistedUser(request, connection, userDetails);
            }
        }
        model.addAttribute(RegistrationControllerConst.USER_REGIST_MODEL_ATTR,
                userService.createRegistrationDTO(connection));
        return RegistrationControllerConst.USER_REGIST_URL;
    }

    /**
     * Process login of existed user.
     *
     * @param request
     *            WebRequest instance to get social connection
     *
     * @param connection
     *            connection with social service
     * @param userDetails
     *            user details to process login
     *
     * @return calendar URL if user active or login URL with error if login
     *         inactive
     */
    private String processExistedUser(final WebRequest request,
            final Connection<?> connection, final UserDetails userDetails) {
        if (userDetails.isAccountNonLocked()) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
            providerSignInUtils.doPostSignUp(
                    connection.fetchUserProfile().getEmail(), request);
            return "calendar";
        } else {
            return "redirect:/login?acount_inactive=true";
        }
    }

    /**
     * Controls view of user registration page.
     *
     * @param model
     *            user registration page view model.
     *
     * @return start page URL
     */
    @PreAuthorize(RegistrationControllerConst.HAS_ROLE_ADMIN)
    @RequestMapping(value = RegistrationControllerConst.USER_REGIST_MAPPING_FOR_ADMIN)
    public String newUserPageForAdmin(final Model model) {
        model.addAttribute(RegistrationControllerConst.USER_REGIST_MODEL_ATTR,
                new UserDTO());
        return RegistrationControllerConst.USER_REGIST_URL;
    }

    /**
     * Controls view of user create form.
     *
     * @param userDTO
     *            user DTO example which is built based on form data.
     * @param br
     *            binding result to check validation errors
     * @param request
     *            WebRequest instance to get social connection
     *
     * @return start page URL
     *
     * @throws IOException
     *             if something happens while upload user image
     */
    @RequestMapping(value = RegistrationControllerConst.USER_REGIST_MAPPING_FROM_STARTPAGE, method = RequestMethod.POST)
    public String newUserFromStartPage(
            @ModelAttribute(RegistrationControllerConst.USER_REGIST_MODEL_ATTR) @Valid final UserDTO userDTO,
            final BindingResult br, final WebRequest request)
            throws IOException {
        if (br.hasErrors()) {
            return RegistrationControllerConst.USER_REGIST_URL;
        }
        userService.create(userDTO);
        userService.autoLoginUser(userDTO);
        providerSignInUtils.doPostSignUp(userDTO.getMail(), request);
        UserDTO activeUser = (UserDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        userService.saveDefoultImage(activeUser);
        return "redirect:/?new_account=true";
    }

    /**
     * Controls view of user create form for administrator.
     *
     * @param userDTO
     *            user DTO example which is built based on form data.
     * @param br
     *            binding result to check validation errors
     *
     * @return users page URL
     * @throws IOException
     *             if something happens while upload user image
     */
    @RequestMapping(value = RegistrationControllerConst.USER_REGIST_MAPPING_FOR_ADMIN, method = RequestMethod.POST)
    public String newUserForAdmin(
            @ModelAttribute(RegistrationControllerConst.USER_REGIST_MODEL_ATTR) @Valid final UserDTO userDTO,
            final BindingResult br) throws IOException {
        if (br.hasErrors()) {
            return RegistrationControllerConst.USER_REGIST_URL;
        }
        userService.create(userDTO);
        UserDTO activeUser = (UserDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        userService.saveDefoultImage(activeUser);
        return RegistrationControllerConst.REDIRECT_USERS_PAGE;
    }
}
