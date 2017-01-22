package com.softserve.edu.schedule.controller;

import java.time.LocalDate;
import java.time.LocalTime;

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
import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.Meeting_;
import com.softserve.edu.schedule.entity.Room;
import com.softserve.edu.schedule.entity.Subject;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserGroup;
import com.softserve.edu.schedule.service.MeetingService;
import com.softserve.edu.schedule.service.RoomService;
import com.softserve.edu.schedule.service.SubjectService;
import com.softserve.edu.schedule.service.UserGroupService;
import com.softserve.edu.schedule.service.UserService;
import com.softserve.edu.schedule.service.implementation.editor.DateEditor;
import com.softserve.edu.schedule.service.implementation.editor.RoomEditor;
import com.softserve.edu.schedule.service.implementation.editor.SubjectEditor;
import com.softserve.edu.schedule.service.implementation.editor.TimeEditor;
import com.softserve.edu.schedule.service.implementation.editor.UserEditor;
import com.softserve.edu.schedule.service.implementation.editor.UserGroupEditor;

@RequestMapping("/meetings")
@Controller
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private SubjectEditor subjectEditor;

    @Autowired
    private DateEditor dateEditor;

    @Autowired
    private TimeEditor timeEditor;

    @Autowired
    private UserEditor userEditor;

    @Autowired
    private UserGroupEditor userGroupEditor;

    @Autowired
    private RoomEditor roomEditor;

    @InitBinder("meetingForm")
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Subject.class, subjectEditor);
        binder.registerCustomEditor(LocalDate.class, dateEditor);
        binder.registerCustomEditor(LocalTime.class, timeEditor);
        binder.registerCustomEditor(User.class, userEditor);
        binder.registerCustomEditor(Room.class, roomEditor);
        binder.registerCustomEditor(UserGroup.class, userGroupEditor);
    }

    @ModelAttribute("meetingForm")
    public Meeting getMeeting() {
        return new Meeting();
    }

    @RequestMapping
    public String showMeetingPage(Model model) {
        model.addAttribute("meetings", meetingService.getAll());
        return "meetings/list";
    }

    @RequestMapping(value = "/sortbydescriptionasc", method = RequestMethod.GET)
    public String sortByDescriptionAcs(Model model) {
        model.addAttribute("meetings",
                meetingService.sortByDescription(Order.ASC));
        return "meetings/list";
    }

    @RequestMapping(value = "/sortbydescriptiondesc",
            method = RequestMethod.GET)
    public String sortByDescriptionDesc(Model model) {
        model.addAttribute("meetings",
                meetingService.sortByDescription(Order.DESC));
        return "meetings/list";
    }

    @RequestMapping(value = "/sortbylevelasc", method = RequestMethod.GET)
    public String sortByLevelAcs(Model model) {
        model.addAttribute("meetings", meetingService.sortByLevel(Order.ASC));
        return "meetings/list";
    }

    @RequestMapping(value = "/sortbyleveldesc", method = RequestMethod.GET)
    public String sortByLevelDesc(Model model) {
        model.addAttribute("meetings", meetingService.sortByLevel(Order.DESC));
        return "meetings/list";
    }

    @RequestMapping(value = "/sortbystatusasc", method = RequestMethod.GET)
    public String sortByStatusAcs(Model model) {
        model.addAttribute("meetings", meetingService.sortByStatus(Order.ASC));
        return "meetings/list";
    }

    @RequestMapping(value = "/sortbystatusdesc", method = RequestMethod.GET)
    public String sortByStatusDesc(Model model) {
        model.addAttribute("meetings", meetingService.sortByStatus(Order.DESC));
        return "meetings/list";
    }

    @RequestMapping(value = "/sortbysubjectasc", method = RequestMethod.GET)
    public String sortBySubjectAcs(Model model) {
        model.addAttribute("meetings", meetingService.sortBySubject(Order.ASC));
        return "meetings/list";
    }

    @RequestMapping(value = "/sortbysubjectdesc", method = RequestMethod.GET)
    public String sortBySubjectDesc(Model model) {
        model.addAttribute("meetings",
                meetingService.sortBySubject(Order.DESC));
        return "meetings/list";
    }

    @RequestMapping(value = "/sortbyownerasc", method = RequestMethod.GET)
    public String sortByOwnerAcs(Model model) {
        model.addAttribute("meetings", meetingService.sortByOwner(Order.ASC));
        return "meetings/list";
    }

    @RequestMapping(value = "/sortbyownerdesc", method = RequestMethod.GET)
    public String sortByOwnerDesc(Model model) {
        model.addAttribute("meetings", meetingService.sortByOwner(Order.DESC));
        return "meetings/list";
    }

    @RequestMapping(value = "/sortbyroomasc", method = RequestMethod.GET)
    public String sortByRoomAcs(Model model) {
        model.addAttribute("meetings", meetingService.sortByRoom(Order.ASC));
        return "meetings/list";
    }

    @RequestMapping(value = "/sortbyroomdesc", method = RequestMethod.GET)
    public String sortByRoomDesc(Model model) {
        model.addAttribute("meetings", meetingService.sortByRoom(Order.DESC));
        return "meetings/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("meetingForm") Meeting meeting) {
        meetingService.create(meeting);
        return "redirect:/meetings";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("meetingForm", new Meeting());
        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("owners", userService.getAll());
        model.addAttribute("rooms", roomService.getAll());
        model.addAttribute("groups", userGroupService.getAll());
        return "meetings/create";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        meetingService.deleteById(id);

        return "redirect:/meetings";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute("meetingForm") Meeting meeting) {
        meetingService.update(meeting);

        return "redirect:/meetings";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("meetingForm", meetingService.getById(id));
       model.addAttribute("subjects", subjectService.getAll());
       model.addAttribute("owners", userService.getAll());
        return "meetings/edit";
    }

    @RequestMapping(value = "/editStatus/{id}", method = RequestMethod.POST)
    public String editStatus(@ModelAttribute("meeting") Meeting meeting) {
        // model.addAttribute("meetingForm", meetingService.getById(id));
        // TODO

        meetingService.update(meeting);

        return "redirect:/meetings";
    }

    @RequestMapping(value = "/editStatus/{id}", method = RequestMethod.GET)
    public String editStatusForm(@PathVariable("id") Long id, Model model) {
        // TODO
        model.addAttribute("meetingForm", meetingService.getById(id));
        model.addAttribute("meetingForm", new Meeting());
        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("owners", userService.getAll());
        model.addAttribute("rooms", roomService.getAll());
        model.addAttribute("groups", userGroupService.getAll());

        return "meetings/editStatus";
    }

}
