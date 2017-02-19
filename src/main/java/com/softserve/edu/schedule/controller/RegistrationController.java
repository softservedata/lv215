/* RegistrationController 1.0 01/20/2017 */
package com.softserve.edu.schedule.controller;

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
public class RegistrationController
        implements ControllerConst.RegistrationControllerConst {

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
    @RequestMapping(value = USER_REGIST_MAPPING_FROM_STARTPAGE,
            method = RequestMethod.GET)
    public String newUserPage(final WebRequest request, final Model model) {
        Connection<?> connection = providerSignInUtils
                .getConnectionFromSession(request);
        UserDetails userDetails = userService
                .loadUserByUsername(connection.fetchUserProfile().getEmail());
        if (userDetails != null) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
            return "calendar";
        }
        model.addAttribute(USER_REGIST_MODEL_ATTR,
                userService.createRegistrationDTO(connection));
        return USER_REGIST_URL;
    }

    /**
     * Controls view of user registration page.
     *
     * @param model
     *            user registration page view model.
     *
     * @return start page URL
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = USER_REGIST_MAPPING_FOR_ADMIN)
    public String newUserPageForAdmin(final Model model) {
        model.addAttribute(USER_REGIST_MODEL_ATTR, new UserDTO());
        return USER_REGIST_URL;
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
     */
    @RequestMapping(value = USER_REGIST_MAPPING_FROM_STARTPAGE,
            method = RequestMethod.POST)
    public String newUserFromStartPage(
            @ModelAttribute(USER_REGIST_MODEL_ATTR) @Valid final UserDTO userDTO,
            final BindingResult br, final WebRequest request) {
        if (br.hasErrors()) {
            return USER_REGIST_URL;
        }
        userService.create(userDTO);
        providerSignInUtils.doPostSignUp(userDTO.getMail(), request);
        return REDIRECT_STARTPAGE;
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
     */
    @RequestMapping(value = USER_REGIST_MAPPING_FOR_ADMIN,
            method = RequestMethod.POST)
    public String newUserForAdmin(
            @ModelAttribute(USER_REGIST_MODEL_ATTR) @Valid final UserDTO userDTO,
            final BindingResult br) {
        if (br.hasErrors()) {
            return USER_REGIST_URL;
        }
        userService.create(userDTO);
        return REDIRECT_USERS_PAGE;
    }
}
