/* GlobalExceptionHandler 1.0 01/29/2017 */
package com.softserve.edu.schedule.exceptionhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softserve.edu.schedule.controller.constants.GeneralContrConst;

/**
 * A controller class for global exception handling.
 *
 * @version 1.0 29 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Logger example to provide logging of exceptions.
     */
    private Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Controls security exception handling.
     *
     * @param ex
     *            handled exception
     *
     * @return start page URL
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public String accessDenied(final Exception ex) {
        log.warn(ex.getMessage(), ex);
        return GeneralContrConst.LOGIN_FAILED_REDIRECT_URL;
    }

    /**
     * Controls global exception handling.
     *
     * @param model
     *            errors page view model.
     *
     * @param ex
     *            handled exception
     *
     * @return errors page URL
     */
    @ExceptionHandler(value = Exception.class)
    @RequestMapping(GeneralContrConst.ROOT_URL)
    public String showErrorPage(final Model model, final Exception ex) {
        log.error(ex.getMessage(), ex);
        model.addAttribute(GeneralContrConst.EXCEPTION_MODEL_ATTR, ex);
        return GeneralContrConst.ERROR_PAGE_URL;
    }

}
