/* IndexController 1.0 01/15/2017 */
package com.softserve.edu.schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * A controller class for start page.
 *
 * @version 1.0 15 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Controller
public class IndexController implements ControllerConst {

    /**
     * Controls view of start page.
     *
     * @param model
     *            start page view model.
     *
     * @return start page URL
     */
    @RequestMapping(ROOT_URL)
    public String showIndex(final Model model) {
        return INDEX_URL;
    }

    /**
     * Controls view of login page.
     *
     * @return login page URL
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
