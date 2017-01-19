package com.softserve.edu.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.service.UserService;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = { "/registration", "users/registration"})
    public String newUserPage(Model model) {
        model.addAttribute("userFormCreate", new User());
        return "users/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String newUserFromStartPage(@ModelAttribute("userFormCreate") User user) {
        userService.create(user);
        return "redirect:/";
    }

    @RequestMapping(value = "users/registration"
            , method = RequestMethod.POST)
    public String newUserForAdmin(@ModelAttribute("userFormCreate") User user) {
        userService.create(user);
        return "redirect:/users";
    }
}
