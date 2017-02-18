/* SignInController 1.0 02/18/2017 */
package com.softserve.edu.schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * A controller class default sign in URL.
 *
 * @version 1.0 18 February 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Controller
public class SignInController {

    /**
     * Redirect request from default sign in URL to custom URL.
     *
     * @return registration URL
     */
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String redirectRequestToRegistrationPage() {
        return "redirect:/login?fail=true";
    }

}
