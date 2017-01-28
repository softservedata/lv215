package com.softserve.edu.schedule.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
public class GlobalExceptionController {

    private Logger log = LoggerFactory
            .getLogger(GlobalExceptionController.class);

    @ExceptionHandler(Exception.class)
    @RequestMapping("/")
    public String showErrorPage(final Model model, Exception ex) {
        log.error("We have caught exception: " + ex.getMessage(), ex);
        model.addAttribute("exception", ex);
        System.out.println(ex.getClass());
        System.out.println(ex.getMessage());
        System.out.println(ex.getCause());
        System.out.println(ex.toString());
        return "error";
    }

}
