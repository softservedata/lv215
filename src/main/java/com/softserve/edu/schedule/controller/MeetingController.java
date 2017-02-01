package com.softserve.edu.schedule.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.softserve.edu.schedule.dto.MeetingDTO;
import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.dto.SubjectDTO;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserGroupDTO;

import com.softserve.edu.schedule.dto.filter.MeetingFilter;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.entity.MeetingStatus;
import com.softserve.edu.schedule.service.MeetingService;
import com.softserve.edu.schedule.service.RoomService;
import com.softserve.edu.schedule.service.SubjectService;
import com.softserve.edu.schedule.service.UserGroupService;
import com.softserve.edu.schedule.service.UserService;
import com.softserve.edu.schedule.service.implementation.editor.DateEditor;
import com.softserve.edu.schedule.service.implementation.editor.MeetingDTOEditor;
import com.softserve.edu.schedule.service.implementation.editor.MeetingStatusEditor;
import com.softserve.edu.schedule.service.implementation.editor.RoomDTOEditor;
import com.softserve.edu.schedule.service.implementation.editor.SubjectDTOEditor;
import com.softserve.edu.schedule.service.implementation.editor.TimeEditor;
import com.softserve.edu.schedule.service.implementation.editor.UserDTOEditor;
import com.softserve.edu.schedule.service.implementation.editor.UserGroupDTOEditor;

@RequestMapping("/meetings")
@Controller
@SessionAttributes({ "meetingFilter", "meetingPaginator" })
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
    private SubjectDTOEditor subjectDTOEditor;

    @Autowired
    private DateEditor dateEditor;

    @Autowired
    private TimeEditor timeEditor;

    @Autowired
    private UserDTOEditor userDTOEditor;

    @Autowired
    private UserGroupDTOEditor userGroupDTOEditor;

    @Autowired
    private RoomDTOEditor roomDTOEditor;
    
    @Autowired
    private MeetingDTOEditor meetingDTOEditor;
    

    @Autowired
    MeetingStatusEditor meetingStatusEditor;

    @InitBinder("meetingForm")
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(MeetingDTO.class, meetingDTOEditor);
        binder.registerCustomEditor(SubjectDTO.class, subjectDTOEditor);
        binder.registerCustomEditor(LocalDate.class, dateEditor);
        binder.registerCustomEditor(LocalTime.class, timeEditor);
        binder.registerCustomEditor(UserDTO.class, userDTOEditor);
        binder.registerCustomEditor(RoomDTO.class, roomDTOEditor);
        binder.registerCustomEditor(UserGroupDTO.class, userGroupDTOEditor);

    }

    @InitBinder("meetingFilter")
    protected void initBinderFilter(final WebDataBinder binder) {
        binder.registerCustomEditor(UserGroupDTO.class, userGroupDTOEditor);
        binder.registerCustomEditor(LocalDate.class, dateEditor);
        binder.registerCustomEditor(LocalTime.class, timeEditor);
    }

    @ModelAttribute("meetingFilter")
    public MeetingFilter getFilter() {
        return new MeetingFilter();
    }

    @ModelAttribute("meetingPaginator")
    public Paginator getPaginator() {
        return new Paginator();
    }

    /**
     * Shows start page for Meetings.
     * 
     * @param model
     * @return
     */
    @RequestMapping
    public String showMeetingPage(Model model,
            @ModelAttribute("meetingFilter") final MeetingFilter meetingFilter,
            @ModelAttribute("meetingPaginator") final Paginator paginator) {
        model.addAttribute("meetings", meetingService
                .getMeetingPageWithFilter(meetingFilter, paginator));
        model.addAttribute("users", userService.getAll());
        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("rooms", roomService.getAll());
        model.addAttribute("userGroups", userGroupService.getAll());
        model.addAttribute("meetingFilter", meetingFilter);
        model.addAttribute("meetingPaginator", paginator);
        model.addAttribute("meetingStatuses", MeetingStatus.values());
        return "meetings/list";
    }

    /**
     * Creates new meetingDTO.
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("meetingForm", new MeetingDTO());
        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("owners", userService.getAll());
        model.addAttribute("rooms", roomService.getAll());
        model.addAttribute("groups", userGroupService.getAll());
        return "meetings/create";
    }

    /**
     * Creates new meetingDTO.
     * 
     * @param meetingDTO
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(
            @Valid @ModelAttribute("meetingForm") MeetingDTO meetingDTO,
            BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("subjects", subjectService.getAll());
            model.addAttribute("owners", userService.getAll());
            model.addAttribute("rooms", roomService.getAll());
            model.addAttribute("groups", userGroupService.getAll());
            return "meetings/create";
        }
        meetingService.create(meetingDTO);
        return "redirect:/meetings";
    }

    /**
     * Deletes meeting by given id.
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        meetingService.deleteById(id);
        return "redirect:/meetings";
    }

    /**
     * Edits meeting by given id.
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("meetingForm", meetingService.getById(id));
        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("owners", userService.getAll());
        model.addAttribute("rooms", roomService.getAll());
        model.addAttribute("groups", userGroupService.getAll());
        model.addAttribute("meetingStatuses", MeetingStatus.values());
        return "meetings/edit";
    }

    /**
     * Deletes meeting by given id.
     * 
     * @param meetingDTO
     * @return
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(
            @ModelAttribute("meetingForm") @Valid MeetingDTO meetingDTO,
            BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("subjects", subjectService.getAll());
            model.addAttribute("owners", userService.getAll());
            model.addAttribute("rooms", roomService.getAll());
            model.addAttribute("groups", userGroupService.getAll());
            model.addAttribute("meetingStatuses", MeetingStatus.values());
            return "meetings/edit";
        }
        meetingService.update(meetingDTO);
        return "redirect:/meetings";
    }

    
    /** Shows selected meeting details.
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showMeeting(@PathVariable("id") final Long id,
            final Model model) {
        model.addAttribute("meetingForm", meetingService.getById(id));
       
        return "meetings/showmeeting";
    }

}
