package com.softserve.edu.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserRole;
import com.softserve.edu.schedule.entity.UserStatus;
import com.softserve.edu.schedule.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/shedule/registration")
    public String newUserPage(Model model) {
        model.addAttribute("userFormCreate", new User());
        return "registration";
    }

    @RequestMapping(value = "/shedule/registration"
            , method = RequestMethod.POST)
    public String newUser(@ModelAttribute("userFormCreate") User user) {
        userService.create(user);
        return "redirect:/";
    }

    @RequestMapping("/shedule/users")
    public String allUserPage(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @RequestMapping("/shedule/users/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/shedule/users";
    }

    @RequestMapping("/shedule/users/updateUser/{id}")
    public String getEdit(@PathVariable Long id, Model model) {
        model.addAttribute("userFormUpdate", userService.getById(id));
        return "updateUser";
    }

    @RequestMapping(value = "/shedule/users/updateUser/saveUpdatedUser/{id}"
            , method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("userFormUpdate") User user) {
        userService.update(user);
        return "redirect:/shedule/users";
    }

    @RequestMapping("/shedule/users/updateUserPosition/{id}")
    public String getEditPosition(@PathVariable Long id, Model model) {
        model.addAttribute("userFormUpdatePosition", userService.getById(id));
        return "updateUserPosition";
    }

    @RequestMapping(value = "/shedule/users/updateUserPosition/saveUpdatedUserPosition/{id}"
            , method = RequestMethod.POST)
    public String updateUserPosition(@PathVariable Long id,
            @RequestParam String position) {
        userService.changePosition(id, position);
        return "redirect:/shedule/users";
    }

    @RequestMapping("/shedule/users/banUser/{id}")
    public String bunUser(@PathVariable Long id) {
        UserStatus userStatus = UserStatus.BLOCKED;
        userService.changeStatus(id, userStatus);
        return "redirect:/shedule/users";
    }

    @RequestMapping("/shedule/users/unBanUser/{id}")
    public String unBunUser(@PathVariable Long id) {
        UserStatus userStatus = UserStatus.ACTIVE;
        userService.changeStatus(id, userStatus);
        return "redirect:/shedule/users";
    }

    @RequestMapping("/shedule/users/changeRole/{id}")
    public String changeRole(@PathVariable Long id, Model model) {
        model.addAttribute("roles", UserRole.values());
        model.addAttribute("user", userService.getById(id));
        return "changeRole";
    }

    @RequestMapping(value = "/shedule/users/changeRole/saveChangedRole/{id}"
            , method = RequestMethod.POST)
    public String changeRole(@ModelAttribute User user, @PathVariable Long id
            , @RequestParam UserRole chooseRole) {
        userService.changeRole(id, chooseRole);
        return "redirect:/shedule/users";
    }

}
