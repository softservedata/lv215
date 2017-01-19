package com.softserve.edu.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserRole;
import com.softserve.edu.schedule.entity.UserStatus;
import com.softserve.edu.schedule.service.UserService;

@RequestMapping("/users")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String allUserPage(Model model) {
        model.addAttribute("users", userService.getAllWithDetails());
        return "users/users";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        try{userService.deleteById(id);
        }catch (IllegalArgumentException e) {
            return "redirect:/curator";
        }
        return "redirect:/users";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "users/users/edit";
    }

    @RequestMapping("/edit/updateUser/{id}")
    public String getEdit(@PathVariable Long id, Model model) {
        model.addAttribute("userFormUpdate", userService.getById(id));
        return "users/users/edit/updateUser";
    }

    @RequestMapping(value = "/edit/updateUser/saveUpdatedUser/{id}"
            , method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("userFormUpdate") User user) {
        userService.update(user);
        return "redirect:/users";
    }

    @RequestMapping("/edit/updateUserPosition/{id}")
    public String getEditPosition(@PathVariable Long id, Model model) {
        model.addAttribute("userFormUpdatePosition", userService.getById(id));
        return "users/users/edit/updateUserPosition";
    }

    @RequestMapping(value = "/edit/updateUserPosition/saveUpdatedUserPosition/{id}"
            , method = RequestMethod.POST)
    public String updateUserPosition(@PathVariable Long id,
            @RequestParam String position) {
        userService.changePosition(id, position);
        return "redirect:/users";
    }

    @RequestMapping("/banUser/{id}")
    public String bunUser(@PathVariable Long id) {
        UserStatus userStatus = UserStatus.BLOCKED;
        userService.changeStatus(id, userStatus);
        return "redirect:/users";
    }

    @RequestMapping("/unBanUser/{id}")
    public String unBunUser(@PathVariable Long id) {
        UserStatus userStatus = UserStatus.ACTIVE;
        userService.changeStatus(id, userStatus);
        return "redirect:/users";
    }

    @RequestMapping({"/edit/changeRole/{id}"})
    public String changeRole(@PathVariable Long id, Model model) {
        model.addAttribute("roles", UserRole.values());
        model.addAttribute("user", userService.getById(id));
        return "users/users/edit/changeRole";
    }

    @RequestMapping(value = "/edit/changeRole/saveChangedRole/{id}"
            , method = RequestMethod.POST)
    public String changeRole(@ModelAttribute User user, @PathVariable Long id
            , @RequestParam UserRole chooseRole) {
        userService.changeRole(id, chooseRole);
        return "redirect:/users";
    }

    @RequestMapping("/sortbyfirstnameasc")
    public String sortByFirstNameAsc(Model model) {
        model.addAttribute("users", userService.sort("firstName", Order.ASC));
        return "users/users";
    }

    @RequestMapping("/sortbyfirstnamedesc")
    public String sortByFirstNameDesc(Model model) {
        model.addAttribute("users", userService.sort("firstName", Order.DESC));
        return "users/users";
    }

    @RequestMapping("/sortbylastnameasc")
    public String sortByLastNameAsc(Model model) {
        model.addAttribute("users", userService.sort("lastName", Order.ASC));
        return "users/users";
    }

    @RequestMapping("/sortbylastnamedesc")
    public String sortByLastNameDesc(Model model) {
        model.addAttribute("users", userService.sort("lastName", Order.DESC));
        return "users/users";
    }

    @RequestMapping("/sortbypositionasc")
    public String sortByPositionAsc(Model model) {
        model.addAttribute("users", userService.sort("position", Order.ASC));
        return "users/users";
    }

    @RequestMapping("/sortbypositiondesc")
    public String sortByPositionDesc(Model model) {
        model.addAttribute("users", userService.sort("position", Order.DESC));
        return "users/users";
    }
}
