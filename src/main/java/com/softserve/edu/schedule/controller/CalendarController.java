/* CalendarController 1.0 02/04/2017 */
package com.softserve.edu.schedule.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softserve.edu.schedule.controller.constants.GeneralContrConst;

/**
 * A controller class for calendar page.
 *
 * @version 1.0 15 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Controller
public class CalendarController {

    /**
     * Controls view of calendar page.
     *
     * @param model
     *            calendar page view model.
     *
     * @return calendar page URL
     */
    @PreAuthorize(GeneralContrConst.CALENDAR_SHOW_PERMISSIONS)
    @RequestMapping(GeneralContrConst.CALENDAR_URL_MAPPING)
    public String showIndex(final Model model) {
        return GeneralContrConst.CALENDAR_URL;
    }
}
