/* SignUpController 1.0 02/18/2017 */
package com.softserve.edu.schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * A controller class default sign up URL.
 *
 * @version 1.0 18 February 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Controller
public class SignUpController {

    /**
     * Redirect request from default sign up URL to custom URL.
     *
     * @return registration URL
     */
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String redirectRequestToRegistrationPage() {
        return "redirect:/registration";
    }

}
