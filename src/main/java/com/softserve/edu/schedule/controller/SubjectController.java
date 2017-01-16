package com.softserve.edu.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.entity.Subject;
import com.softserve.edu.schedule.service.SubjectService;
import com.softserve.edu.schedule.service.UserService;

@Controller
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    @RequestMapping("/subject")
    public String showSubjectPage(Model model) {
        model.addAttribute("subjectForm", new Subject());
        model.addAttribute("subjects", subjectService.getAllWithDetails());
        model.addAttribute("users", userService.getAll());
        return "subject";
    }

    @RequestMapping(value = "/subject", method = RequestMethod.POST)
    public String postSubject(@ModelAttribute("subjectForm") Subject subject) {
        if(subject.getId()!=null){
            subjectService.update(subject); 
        } else{
            subjectService.create(subject);
        }    
        return "redirect:/subject";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        subjectService.deleteById(id);
        return "redirect:/subject";
    }
    
    @RequestMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("subjectForm", new Subject());
        model.addAttribute("users", userService.getAll());
        return "update";
    }

    @RequestMapping(value = "/update/{id}")
    public String Edit(@PathVariable Long id, Model model) {
        model.addAttribute("subjectForm", subjectService.getById(id));
        return "update";
    }
    
    @RequestMapping("/sortbynameasc")
    public String sortByNameAsc(Model model) {
        model.addAttribute("subjectForm", new Subject());
        model.addAttribute("subjects", subjectService.sortByName(Order.ASC));
        model.addAttribute("users", userService.getAll());
        return "subject";
    }

    @RequestMapping("/sortbynamedesc")
    public String sortByNameDesc(Model model) {
        model.addAttribute("subjectForm", new Subject());
        model.addAttribute("subjects", subjectService.sortByName(Order.DESC));
        model.addAttribute("users", userService.getAll());
        return "subject";
    }
    
    @RequestMapping("/sortbydescriptionasc")
    public String sortByDescrAsc(Model model) {
        model.addAttribute("subjectForm", new Subject());
        model.addAttribute("subjects", subjectService.sortByDescription(Order.ASC));
        model.addAttribute("users", userService.getAll());
        return "subject";
    }

    @RequestMapping("/sortbydescriptiondesc")
    public String sortByDescrDesc(Model model) {
        model.addAttribute("subjectForm", new Subject());
        model.addAttribute("subjects", subjectService.sortByDescription(Order.DESC));
        model.addAttribute("users", userService.getAll());
        return "subject";
    }
}
