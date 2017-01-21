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


@Controller
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    @ModelAttribute("search")
    public SubjectDTO getSubjectDTO() {
        return new SubjectDTO();
    }

    @ModelAttribute("searchTutor")
    public UserForSubjectDTO getUserForSubjectDTO() {
        return new UserForSubjectDTO();
    }

    @InitBinder("subjectForm")
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(UserForSubjectDTO.class,
                new UserForSubjectDTOEditor(userService));
    }

    @RequestMapping("/subjects")
    public String showSubjectPage(Model model) {
        model.addAttribute("subjects", subjectService.getAll());
        return "subjects/list";
    }

    @RequestMapping("/subjects/create")
    public String createForm(Model model) {
        model.addAttribute("subjectForm", new SubjectDTO());
        model.addAttribute("users", userService.getAllForSubject());
        return "subjects/create";
    }

    @RequestMapping(value = "/subjects/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("subjectForm") SubjectDTO subject) {
        subjectService.create(subject);
        return "redirect:/subjects";
    }

    @RequestMapping("/subjects/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("subjectForm", subjectService.getById(id));
        model.addAttribute("users", userService.getAllForSubject());
        return "subjects/edit";
    }

    @RequestMapping(value = "/subjects/edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute("subjectForm") SubjectDTO subject) {
        subjectService.update(subject);
        return "redirect:/subjects";
    }

    @RequestMapping("/subjects/delete/{id}")
    public String delete(@PathVariable Long id) {
        subjectService.deleteById(id);
        return "redirect:/subjects";
    }

    @RequestMapping("/subjects/sortbynameasc")
    public String sortByNameAsc(Model model) {
        model.addAttribute("subjects", subjectService.sortByName(Order.ASC));
        return "subjects/list";
    }

    @RequestMapping("/subjects/sortbynamedesc")
    public String sortByNameDesc(Model model) {
        model.addAttribute("subjects", subjectService.sortByName(Order.DESC));
        return "subjects/list";
    }

    @RequestMapping("/subjects/sortbydescriptionasc")
    public String sortByDescrAsc(Model model) {
        model.addAttribute("subjects",
                subjectService.sortByDescription(Order.ASC));
        return "subjects/list";
    }

    @RequestMapping("/subjects/sortbydescriptiondesc")
    public String sortByDescrDesc(Model model) {
        model.addAttribute("subjects",
                subjectService.sortByDescription(Order.DESC));
        return "subjects/list";
    }

    @RequestMapping(value = "/subjects/searchByName", method = RequestMethod.POST)
    public String searchByName(@ModelAttribute("search") SubjectDTO subject,
            Model model) {
        model.addAttribute("subjects",
                subjectService.searchByName(subject.getName()));
        return "subjects/list";
    }

    @RequestMapping(value = "/subjects/searchByDescription", method = RequestMethod.POST)
    public String searchByDescription(
            @ModelAttribute("search") SubjectDTO subject, Model model) {
        model.addAttribute("subjects",
                subjectService.searchByDescription(subject.getDescription()));
        System.out.println("p" + subject.getName() + "p");
        System.out.println("p" + subject.getDescription() + "p");
        System.out.println(subject.getId());
        return "subjects/list";
    }

    @RequestMapping(value = "/subjects/searchByTutor", method = RequestMethod.POST)
    public String searchByTutor(
            @ModelAttribute("searchTutor") UserForSubjectDTO user,
            Model model) {
            model.addAttribute("subjects",
                    subjectService.searchByTutors(user.getLastName()));
        return "subjects/list";
    }
}
