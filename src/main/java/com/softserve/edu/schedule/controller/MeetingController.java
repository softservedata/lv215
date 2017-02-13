/* 
 * Meetings controller class.
 */
package com.softserve.edu.schedule.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

/**
 * A controller class of meetings pages.
 *
 * @version 1.0 5 January 2017
 *
 * @author Bohdan Melnyk
 *
 */
@RequestMapping(ControllerConst.MeetingControllerConst.MEETINGS_URL)
@Controller
@SessionAttributes({ControllerConst.MeetingControllerConst.FILTER_MODEL_ATTR,
        ControllerConst.MeetingControllerConst.MEETING_PAGINATOR_MODEL_ATTR})
public class MeetingController
        implements ControllerConst.MeetingControllerConst {

    /**
     * MeetingService example to provide meetings list to the model.
     */
    @Autowired
    private MeetingService meetingService;

    /**
     * SubjectService example to provide subject list to the model.
     */
    @Autowired
    private SubjectService subjectService;

    /**
     * UserService example to provide users list to the model.
     */
    @Autowired
    private UserService userService;

    /**
     * RoomService example to provide rooms list to the model.
     */
    @Autowired
    private RoomService roomService;

    /**
     * UserGroupService example to provide UserGroups list to the model.
     */
    @Autowired
    private UserGroupService userGroupService;

    /**
     * SubjectDTOEditor example to provide conversions from form select fields
     * to DTO.
     */
    @Autowired
    private SubjectDTOEditor subjectDTOEditor;

    /**
     * DateEditor example to provide conversions from form select fields to date
     * format.
     */
    @Autowired
    private DateEditor dateEditor;

    /**
     * TimeEditor example to provide conversions from form select fields to time
     * format.
     */
    @Autowired
    private TimeEditor timeEditor;

    /**
     * UserDTOEditor example to provide conversions from form select fields to
     * DTO.
     */
    @Autowired
    private UserDTOEditor userDTOEditor;

    /**
     * UserGroupDTOEditor example to provide conversions from form select fields
     * to DTO.
     */
    @Autowired
    private UserGroupDTOEditor userGroupDTOEditor;

    /**
     * RoomDTOEditor example to provide conversions from form select fields to
     * DTO.
     */
    @Autowired
    private RoomDTOEditor roomDTOEditor;

    /**
     * MeetingDTOEditor example to provide conversions from form select fields
     * to DTO.
     */
    @Autowired
    private MeetingDTOEditor meetingDTOEditor;

    /**
     * MeetingStatusEditor example to provide conversions from form select
     * fields to MeetingStatus enum.
     */
    @Autowired
    MeetingStatusEditor meetingStatusEditor;

    /**
     * Initialize binder for meeting model.
     *
     * @param binder
     *            a WebDataBinder example to initialize.
     */
    @InitBinder(MEETING_MODEL_ATTR)
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(MeetingDTO.class, meetingDTOEditor);
        binder.registerCustomEditor(SubjectDTO.class, subjectDTOEditor);
        binder.registerCustomEditor(LocalDate.class, dateEditor);
        binder.registerCustomEditor(LocalTime.class, timeEditor);
        binder.registerCustomEditor(UserDTO.class, userDTOEditor);
        binder.registerCustomEditor(RoomDTO.class, roomDTOEditor);
        binder.registerCustomEditor(UserGroupDTO.class, userGroupDTOEditor);
    }

    /**
     * Initialize binder for filter model.
     *
     * @param binder
     *            a WebDataBinder example to initialize.
     */
    @InitBinder(FILTER_MODEL_ATTR)
    protected void initBinderFilter(final WebDataBinder binder) {
        binder.registerCustomEditor(UserGroupDTO.class, userGroupDTOEditor);
        binder.registerCustomEditor(LocalDate.class, dateEditor);
        binder.registerCustomEditor(LocalTime.class, timeEditor);
    }

    /**
     * Provides meetings filter.
     *
     * @return new MeetingFilter object.
     */
    @ModelAttribute(FILTER_MODEL_ATTR)
    public MeetingFilter getFilter() {
        return new MeetingFilter();
    }

    /**
     * Provides meetings paginator.
     *
     * @return new MeetingPaginator object.
     */
    @ModelAttribute(MEETING_PAGINATOR_MODEL_ATTR)
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
            @ModelAttribute(FILTER_MODEL_ATTR) final MeetingFilter meetingFilter,
            @ModelAttribute(MEETING_PAGINATOR_MODEL_ATTR) final Paginator paginator) {
        model.addAttribute(MEETINGS_MODEL_ATTR, meetingService
                .getMeetingPageWithFilter(meetingFilter, paginator));
        model.addAttribute(USERS_MODEL_ATTR,
                userService.getAllManagers(userService.getAllActiveUsers()));
        model.addAttribute(SUBJECTS_MODEL_ATTR, subjectService.getAll());
        model.addAttribute(ROOMS_MODEL_ATTR, roomService.getAll());
        model.addAttribute(USERGROUPS_MODEL_ATTR, userGroupService.getAll());
        model.addAttribute(FILTER_MODEL_ATTR, meetingFilter);
        model.addAttribute(MEETING_PAGINATOR_MODEL_ATTR, paginator);
        model.addAttribute(MEETINGSTATUSES_MODEL_ATTR, MeetingStatus.values());
        return MEETING_LIST_URL;
    }

    /**
     * Creates new meetingDTO.
     * 
     * @param model
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERVISOR', 'ROLE_MODERATOR')")
    @RequestMapping(value = MEETING_CREATE_MAPPING, method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute(MEETING_MODEL_ATTR, new MeetingDTO());
        model.addAttribute(SUBJECTS_MODEL_ATTR, subjectService.getAll());
        model.addAttribute(OWNERS_MODEL_ATTR,
                userService.getAllManagers(userService.getAllActiveUsers()));
        model.addAttribute(ROOMS_MODEL_ATTR, roomService.getAll());
        model.addAttribute(GROUPS_MODEL_ATTR, userGroupService.getAll());
        return MEETING_CREATE_URL;
    }

    /**
     * Creates new meetingDTO.
     * 
     * @param meetingDTO
     * @return
     */
    @RequestMapping(value = MEETING_CREATE_MAPPING, method = RequestMethod.POST)
    public String create(
            @Valid @ModelAttribute(MEETING_MODEL_ATTR) MeetingDTO meetingDTO,
            BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute(SUBJECTS_MODEL_ATTR, subjectService.getAll());
            model.addAttribute(OWNERS_MODEL_ATTR, userService
                    .getAllManagers(userService.getAllActiveUsers()));
            model.addAttribute(ROOMS_MODEL_ATTR, roomService.getAll());
            model.addAttribute(GROUPS_MODEL_ATTR, userGroupService.getAll());
            return MEETING_CREATE_URL;
        }
        meetingService.create(meetingDTO);
        return MEETING_REDIRECT_URL;
    }

    /**
     * Deletes meeting by given id.
     * 
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERVISOR', 'ROLE_MODERATOR')")
    @RequestMapping(value = MEETING_DELETE_MAPPING, method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        meetingService.deleteById(id);
        return MEETING_REDIRECT_URL;
    }

    /**
     * Edits meeting by given id.
     * 
     * @param id
     * @param model
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERVISOR', 'ROLE_MODERATOR')")
    @RequestMapping(value = MEETING_EDIT_MAPPING, method = RequestMethod.GET)
    public String editForm(@PathVariable(ID_URL) Long id, Model model) {
        model.addAttribute(MEETING_MODEL_ATTR, meetingService.getById(id));
        model.addAttribute(SUBJECTS_MODEL_ATTR, subjectService.getAll());
        model.addAttribute(OWNERS_MODEL_ATTR,
                userService.getAllManagers(userService.getAllActiveUsers()));
        model.addAttribute(ROOMS_MODEL_ATTR, roomService.getAll());
        model.addAttribute(GROUPS_MODEL_ATTR, userGroupService.getAll());
        model.addAttribute(MEETINGSTATUSES_MODEL_ATTR, MeetingStatus.values());
        return MEETING_EDIT_URL;
    }

    /**
     * Deletes meeting by given id.
     * 
     * @param meetingDTO
     * @return
     */
    @RequestMapping(value = MEETING_EDIT_MAPPING, method = RequestMethod.POST)
    public String edit(
            @ModelAttribute(MEETING_MODEL_ATTR) @Valid MeetingDTO meetingDTO,
            BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute(SUBJECTS_MODEL_ATTR, subjectService.getAll());
            model.addAttribute(OWNERS_MODEL_ATTR, userService
                    .getAllManagers(userService.getAllActiveUsers()));
            model.addAttribute(ROOMS_MODEL_ATTR, roomService.getAll());
            model.addAttribute(GROUPS_MODEL_ATTR, userGroupService.getAll());
            model.addAttribute(MEETINGSTATUSES_MODEL_ATTR,
                    MeetingStatus.values());
            return MEETING_EDIT_URL;
        }
        meetingService.update(meetingDTO);
        return MEETING_REDIRECT_URL;
    }

    /**
     * Shows selected meeting details.
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = MEETINGID_URL, method = RequestMethod.GET)
    public String showMeeting(@PathVariable(ID_URL) final Long id,
            final Model model) {
        model.addAttribute(MEETING_MODEL_ATTR, meetingService.getById(id));
        return MEETING_SHOWMEETING_MAPPING;
    }
}
