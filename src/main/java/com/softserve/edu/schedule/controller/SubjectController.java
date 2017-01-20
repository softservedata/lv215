package com.softserve.edu.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dto.SubjectDTO;
import com.softserve.edu.schedule.dto.UserForSubjectDTO;
import com.softserve.edu.schedule.service.SubjectService;
import com.softserve.edu.schedule.service.UserService;
import com.softserve.edu.schedule.service.implementation.editor.UserForSubjectDTOEditor;

@RequestMapping("/subjects")
@Controller
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;
    
    @InitBinder("subjectForm")
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(UserForSubjectDTO.class,
                new UserForSubjectDTOEditor(userService));
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String showSubjectPage(Model model) {
        model.addAttribute("subjects", subjectService.getAllWithDetails());
        return "subjects/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("subjectForm", new SubjectDTO());
        model.addAttribute("users", userService.getAllForSubject());
        return "subjects/create";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("subjectForm") SubjectDTO subject) {
        subjectService.create(subject);
        return "redirect:/subjects";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("subjectForm", subjectService.getByIdWhithDetails(id));
        model.addAttribute("users", userService.getAllForSubject());
        return "subjects/edit";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute("subjectForm") SubjectDTO subject) {
        subjectService.update(subject);
        return "redirect:/subjects";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        subjectService.deleteById(id);
        return "redirect:/subjects";
    }
    
    @RequestMapping(value = "/sortbynameasc", method = RequestMethod.GET)
    public String sortByNameAsc(Model model) {
        model.addAttribute("subjects", subjectService.sortByName(Order.ASC));
        return "subjects/list";
    }

    @RequestMapping(value = "/sortbynamedesc", method = RequestMethod.GET)
    public String sortByNameDesc(Model model) {
        model.addAttribute("subjects", subjectService.sortByName(Order.DESC));
        return "subjects/list";
    }

    @RequestMapping(value = "/sortbydescriptionasc", method = RequestMethod.GET)
    public String sortByDescrAsc(Model model) {
        model.addAttribute("subjects",
                subjectService.sortByDescription(Order.ASC));
        return "subjects/list";
    }

    @RequestMapping(value = "/sortbydescriptiondesc", method = RequestMethod.GET)
    public String sortByDescrDesc(Model model) {
        model.addAttribute("subjects",
                subjectService.sortByDescription(Order.DESC));
        return "subjects/list";
    }
}
