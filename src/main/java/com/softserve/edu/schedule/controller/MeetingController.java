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
import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.dto.RoomEquipmentDTO;
import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.Subject;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.service.LocationService;
import com.softserve.edu.schedule.service.MeetingService;
import com.softserve.edu.schedule.service.RoomEquipmentService;
import com.softserve.edu.schedule.service.RoomService;
import com.softserve.edu.schedule.service.SubjectService;
import com.softserve.edu.schedule.service.UserGroupService;
import com.softserve.edu.schedule.service.UserService;
import com.softserve.edu.schedule.service.implementation.editor.LocationDTOEditor;
import com.softserve.edu.schedule.service.implementation.editor.MeetingEditor;
import com.softserve.edu.schedule.service.implementation.editor.RoomEquipmentDTOEditor;
import com.softserve.edu.schedule.service.implementation.editor.UserEditor;

@RequestMapping("/meetings")
@Controller
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private LocationService locationService;
    

    @Autowired
    private SubjectService subjectService;

    
//    @InitBinder("meetingForm")
//    protected void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(Meeting.class,
//                new MeetingEditor(meetingService));
//    }
    
    @RequestMapping
    public String showMeetingPage(Model model) {
        model.addAttribute("meetings", meetingService.getAll());
        System.out.println(meetingService.getAll());
        return "meetings/list";
    }

//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public String create(@ModelAttribute("subjectForm") Subject subject) {
//        subjectService.create(subject);
//        return "redirect:/subjects";
//    }
//
//    @RequestMapping(value = "/create", method = RequestMethod.GET)
//    public String createForm(Model model) {
//        model.addAttribute("subjectForm", new Subject());
//        model.addAttribute("users", userService.getAll());
//        return "subjects/create";
//    }
//
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//    public String delete(@PathVariable Long id) {
//        subjectService.deleteById(id);
//        return "redirect:/subjects";
//    }
//    
//    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
//    public String edit(@ModelAttribute("subjectForm") Subject subject) {
//        subjectService.update(subject);
//        return "redirect:/subjects";
//    }
//    
//    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
//    public String editForm(@PathVariable Long id, Model model) {
//        model.addAttribute("subjectForm", subjectService.getById(id));
//        model.addAttribute("users", userService.getAll());
//        return "subjects/edit";
//    }
//    
//    @RequestMapping(value = "/sortbynameasc", method = RequestMethod.GET)
//    public String sortByNameAsc(Model model) {
//        model.addAttribute("subjects", subjectService.sortByName(Order.ASC));
//        model.addAttribute("users", userService.getAll());
//        return "subjects/list";
//    }
//
//    @RequestMapping(value = "/sortbynamedesc", method = RequestMethod.GET)
//    public String sortByNameDesc(Model model) {
//        model.addAttribute("subjects", subjectService.sortByName(Order.DESC));
//        model.addAttribute("users", userService.getAll());
//        return "subjects/list";
//    }
//
//    @RequestMapping(value = "/sortbydescriptionasc", method = RequestMethod.GET)
//    public String sortByDescrAsc(Model model) {
//        model.addAttribute("subjects",
//                subjectService.sortByDescription(Order.ASC));
//        model.addAttribute("users", userService.getAll());
//        return "subjects/list";
//    }
//
//    @RequestMapping(value = "/sortbydescriptiondesc", method = RequestMethod.GET)
//    public String sortByDescrDesc(Model model) {
//        model.addAttribute("subjects",
//                subjectService.sortByDescription(Order.DESC));
//        model.addAttribute("users", userService.getAll());
//        return "subjects/list";
//    }
}

