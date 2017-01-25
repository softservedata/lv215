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
import com.softserve.edu.schedule.dto.MeetingDTO;
import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.dto.SubjectDTO;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserGroupDTO;
import com.softserve.edu.schedule.dto.filter.DateFilter;
import com.softserve.edu.schedule.entity.MeetingStatus;
import com.softserve.edu.schedule.service.MeetingService;
import com.softserve.edu.schedule.service.RoomService;
import com.softserve.edu.schedule.service.SubjectService;
import com.softserve.edu.schedule.service.UserGroupService;
import com.softserve.edu.schedule.service.UserService;
import com.softserve.edu.schedule.service.implementation.editor.DateEditor;
import com.softserve.edu.schedule.service.implementation.editor.RoomDTOEditor;
import com.softserve.edu.schedule.service.implementation.editor.SubjectDTOEditor;
import com.softserve.edu.schedule.service.implementation.editor.TimeEditor;
import com.softserve.edu.schedule.service.implementation.editor.UserDTOEditor;
import com.softserve.edu.schedule.service.implementation.editor.UserGroupDTOEditor;

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
   

    @InitBinder("meetingForm")
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(SubjectDTO.class, subjectDTOEditor);
        binder.registerCustomEditor(LocalDate.class, dateEditor);
        binder.registerCustomEditor(LocalTime.class, timeEditor);
        binder.registerCustomEditor(UserDTO.class, userDTOEditor);
        binder.registerCustomEditor(RoomDTO.class, roomDTOEditor);
        binder.registerCustomEditor(UserGroupDTO.class, userGroupDTOEditor);
        
    }
    
    @InitBinder("searchmeetingsbydate")
    protected void initBinderForDate(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, dateEditor);
        
    }

    @ModelAttribute("meetingForm")
    public MeetingDTO getMeeting() {
        return new MeetingDTO();
    }

    @ModelAttribute("searchmeetingsbydescription")
    public MeetingDTO searchMeetingByDescription() {
        return new MeetingDTO();
    }

    @ModelAttribute("searchmeetingsbysubject")
    public SubjectDTO searchMeetingBySugject() {
        return new SubjectDTO();
    }

    @ModelAttribute("searchmeetingsbyowner")
    public UserDTO searchMeetingByOwner() {
        return new UserDTO();
    }

    @ModelAttribute("searchmeetingsbyroom")
    public RoomDTO searchMeetingByRoom() {
        return new RoomDTO();
    }

    @ModelAttribute("searchmeetingsbydate")
    public DateFilter searchMeetingByDate() {
        return new DateFilter();
    }

    @ModelAttribute("searchmeetingsbygroup")
    public UserGroupDTO searchMeetingByGroup() {
        return new UserGroupDTO();
    }

    @ModelAttribute("searchmeetingsbylevel")
    public MeetingDTO searchMeetingsByLevel() {
        return new MeetingDTO();
    }

    @ModelAttribute("searchmeetingsbystatus")
    public MeetingDTO searchMeetingByStatus() {
        return new MeetingDTO();
    }

    /**Shows start page for Meetings.
     * @param model
     * @return
     */
    @RequestMapping
    public String showMeetingPage(Model model) {
        model.addAttribute("meetings", meetingService.getAll());
        return "meetings/list";
    }

    /**Sort all meetings by description order: ASC.
     * @param model
     * @return
     */
    @RequestMapping(value = "/sortbydescriptionasc", method = RequestMethod.GET)
    public String sortByDescriptionAcs(Model model) {
        model.addAttribute("meetings",
                meetingService.sortByDescription(Order.ASC));
        return "meetings/list";
    }

    /**Sort all meetings by description order: DESC.
     * @param model
     * @return
     */
    @RequestMapping(value = "/sortbydescriptiondesc",
            method = RequestMethod.GET)
    public String sortByDescriptionDesc(Model model) {
        model.addAttribute("meetings",
                meetingService.sortByDescription(Order.DESC));
        return "meetings/list";
    }

    /**Sort all meetings by level order: ASC.
     * @param model
     * @return
     */
    @RequestMapping(value = "/sortbylevelasc", method = RequestMethod.GET)
    public String sortByLevelAcs(Model model) {
        model.addAttribute("meetings", meetingService.sortByLevel(Order.ASC));
        return "meetings/list";
    }

    /**Sort all meetings by level order: DESC.
     * @param model
     * @return
     */
    @RequestMapping(value = "/sortbyleveldesc", method = RequestMethod.GET)
    public String sortByLevelDesc(Model model) {
        model.addAttribute("meetings", meetingService.sortByLevel(Order.DESC));
        return "meetings/list";
    }

    /**Sort all meetings by status order: ASC.
     * @param model
     * @return
     */
    @RequestMapping(value = "/sortbystatusasc", method = RequestMethod.GET)
    public String sortByStatusAcs(Model model) {
        model.addAttribute("meetings", meetingService.sortByStatus(Order.ASC));
        return "meetings/list";
    }

    /**Sort all meetings by status order: DESC.
     * @param model
     * @return
     */
    @RequestMapping(value = "/sortbystatusdesc", method = RequestMethod.GET)
    public String sortByStatusDesc(Model model) {
        model.addAttribute("meetings", meetingService.sortByStatus(Order.DESC));
        return "meetings/list";
    }

    /**Sort all meetings by subject name order: ASC.
     * @param model
     * @return
     */
    @RequestMapping(value = "/sortbysubjectasc", method = RequestMethod.GET)
    public String sortBySubjectAcs(Model model) {
        model.addAttribute("meetings", meetingService.sortBySubject(Order.ASC));
        return "meetings/list";
    }

    /**Sort all meetings by subject name order: DESC.
     * @param model
     * @return
     */
    @RequestMapping(value = "/sortbysubjectdesc", method = RequestMethod.GET)
    public String sortBySubjectDesc(Model model) {
        model.addAttribute("meetings",
                meetingService.sortBySubject(Order.DESC));
        return "meetings/list";
    }

    /**Sort all meetings by owner last name order: ASC.
     * @param model
     * @return
     */
    @RequestMapping(value = "/sortbyownerasc", method = RequestMethod.GET)
    public String sortByOwnerAcs(Model model) {
        model.addAttribute("meetings", meetingService.sortByOwner(Order.ASC));
        return "meetings/list";
    }

    /**Sort all meetings by owner last name order: DESC.
     * @param model
     * @return
     */
    @RequestMapping(value = "/sortbyownerdesc", method = RequestMethod.GET)
    public String sortByOwnerDesc(Model model) {
        model.addAttribute("meetings", meetingService.sortByOwner(Order.DESC));
        return "meetings/list";
    }

    /**Sort all meetings by room name order: ASC.
     * @param model
     * @return
     */
    @RequestMapping(value = "/sortbyroomasc", method = RequestMethod.GET)
    public String sortByRoomAcs(Model model) {
        model.addAttribute("meetings", meetingService.sortByRoom(Order.ASC));
        return "meetings/list";
    }

    /**Sort all meetings by room name order: DESC.
     * @param model
     * @return
     */
    @RequestMapping(value = "/sortbyroomdesc", method = RequestMethod.GET)
    public String sortByRoomDesc(Model model) {
        model.addAttribute("meetings", meetingService.sortByRoom(Order.DESC));
        return "meetings/list";
    }

    /**Creates new meetingDTO.
     * @param meetingDTO
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("meetingForm") MeetingDTO meetingDTO) {
        meetingService.create(meetingDTO);
        return "redirect:/meetings";
    }

    /**Creates new meetingDTO.
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

    /**Deletes meeting by given id.
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        meetingService.deleteById(id);
        return "redirect:/meetings";
    }

    /**Deletes meeting by given id.
     * @param meetingDTO
     * @return
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute("meetingForm") MeetingDTO meetingDTO) {
        meetingService.update(meetingDTO);
        return "redirect:/meetings";
    }

    /**Edits meeting by given id.
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

    /**Edits meeting by given id.
     * @param meetingDTO
     * @return
     */
    @RequestMapping(value = "/editStatus/{id}", method = RequestMethod.POST)
    public String editStatus(@ModelAttribute("meeting") MeetingDTO meetingDTO) {
        meetingService.changeMeetingStatus(meetingDTO.getId(),
                meetingDTO.getStatus());
        return "redirect:/meetings";
    }

    /**Edits meeting status by given id.
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/editStatus/{id}", method = RequestMethod.GET)
    public String editStatusForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("meetingForm", meetingService.getById(id));
        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("owners", userService.getAll());
        model.addAttribute("rooms", roomService.getAll());
        model.addAttribute("groups", userGroupService.getAll());
        model.addAttribute("meetingStatuses", MeetingStatus.values());
        return "meetings/editStatus";
    }

    /*
     * Search methods.
     */

    /**Search meeting by given description.
     * @param meetingDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/searchmeetingsbydescription",
            method = RequestMethod.POST)
    public String searchByDescription(
            @ModelAttribute("searchmeetingsbydescription") MeetingDTO meetingDTO,
            Model model) {
        model.addAttribute("meetings", meetingService
                .searchByDescription(meetingDTO.getDescription()));
        return "meetings/list";
    }

    /**Search meeting by given subjectDTO.
     * @param subjectDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/searchmeetingsbysubject",
            method = RequestMethod.POST)
    public String searchBySubject(
            @ModelAttribute("searchmeetingsbysubject") SubjectDTO subjectDTO,
            Model model) {
        model.addAttribute("meetings",
                meetingService.searchBySubject(subjectDTO.getName()));
        return "meetings/list";
    }

    /**Search meeting by given ownerDTO.
     * @param userDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/searchmeetingsbyowner",
            method = RequestMethod.POST)
    public String searchByOwnew(
            @ModelAttribute("searchmeetingsbyowner") UserDTO userDTO,
            Model model) {
        model.addAttribute("meetings",
                meetingService.searchByOwner(userDTO.getLastName()));
        return "meetings/list";
    }

    /**Search meeting by given roomDTO.
     * @param roomDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/searchmeetingsbyroom",
            method = RequestMethod.POST)
    public String searchByRoom(
            @ModelAttribute("searchmeetingsbyroom") RoomDTO roomDTO,
            Model model) {
        model.addAttribute("meetings",
                meetingService.searchByRoom(roomDTO.getName()));
        return "meetings/list";
    }

    /**Search meeting by given level.
     * @param meetingDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/searchmeetingsbylevel",
            method = RequestMethod.POST)
    public String searchByLevel(
            @ModelAttribute("searchmeetingsbylevel") MeetingDTO meetingDTO,
            Model model) {
        model.addAttribute("meetings",
                meetingService.searchByLevel(meetingDTO.getLevel().toString()));
        return "meetings/list";
    }

    /**Search meeting by given date.
     * @param dateFilter
     * @param model
     * @return
     */
    @RequestMapping(value = "/searchmeetingsbydate",
            method = RequestMethod.POST)
    public String searchByDate(
            @ModelAttribute("searchmeetingsbydate") DateFilter dateFilter,
            Model model) {
        model.addAttribute("meetings", meetingService
                .searchByDate(dateFilter.getDate()));
        return "meetings/list";
    }

    /**Search meeting by given userGroups.
     * @param userGroupDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/searchmeetingsbygroup",
            method = RequestMethod.POST)
    public String searchByGroups(
            @ModelAttribute("searchmeetingsbygroup") UserGroupDTO userGroupDTO,
            Model model) {
        model.addAttribute("meetings",
                meetingService.searchByUserGroup(userGroupDTO.getName()));
        return "meetings/list";
    }

    /**Search meeting by given status.
     * @param meetingDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/searchmeetingsbystatus",
            method = RequestMethod.POST)
    public String searchByStatus(
            @ModelAttribute("searchmeetingsbystatus") MeetingDTO meetingDTO,
            Model model) {
        model.addAttribute("meetings",
                meetingService.searchByStatus(meetingDTO.getStatus()));
        return "meetings/list";
    }
}
